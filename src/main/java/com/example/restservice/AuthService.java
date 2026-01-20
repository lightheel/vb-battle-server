package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private static final long TOKEN_CACHE_TTL_MS = 300_000; // 5 minutes cache TTL
    
    private final RestTemplate restTemplate;
    private final ConcurrentHashMap<String, CachedAuthResponse> tokenCache = new ConcurrentHashMap<>();
    private final ScheduledExecutorService cacheCleanupScheduler = Executors.newScheduledThreadPool(1);
    
    @Value("${auth.nacatech.url:https://auth.nacatech.es/userinfo}")
    private String authUrl;
    
    @Value("${auth.nacatech.token:}")
    private String nacaAuthToken;
    
    @Value("${auth.application.id:443654920}")
    private String applicationId;
    
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        // Schedule periodic cleanup of expired tokens
        cacheCleanupScheduler.scheduleAtFixedRate(this::cleanupExpiredTokens, 1, 1, TimeUnit.MINUTES);
    }
    
    /**
     * Cleanup expired tokens from cache
     */
    private void cleanupExpiredTokens() {
        long now = System.currentTimeMillis();
        tokenCache.entrySet().removeIf(entry -> {
            if (now - entry.getValue().cachedAt > TOKEN_CACHE_TTL_MS) {
                logger.debug("Removed expired token from cache");
                return true;
            }
            return false;
        });
    }
    
    /**
     * Cached authentication response with timestamp
     */
    private static class CachedAuthResponse {
        final AuthResponse response;
        final long cachedAt;
        
        CachedAuthResponse(AuthResponse response) {
            this.response = response;
            this.cachedAt = System.currentTimeMillis();
        }
    }
    
    @PostConstruct
    public void init() {
        // Warn if token is not set (should be in secrets.properties)
        if (nacaAuthToken == null || nacaAuthToken.isEmpty()) {
            logger.warn("WARNING: auth.nacatech.token is not set! Please create secrets.properties file.");
        }
    }
    
    public AuthResponse validateUserToken(String userToken) {
        if (userToken == null || userToken.isEmpty()) {
            return new AuthResponse(false, "Token is required", null);
        }
        
        // Check cache first
        CachedAuthResponse cached = tokenCache.get(userToken);
        if (cached != null) {
            long age = System.currentTimeMillis() - cached.cachedAt;
            if (age < TOKEN_CACHE_TTL_MS) {
                logger.debug("Token found in cache (age: {}ms)", age);
                return cached.response;
            } else {
                // Expired, remove from cache
                tokenCache.remove(userToken);
                logger.debug("Cached token expired, re-validating");
            }
        }
        
        // Token not in cache or expired, validate with nacatech
        try {
            // Log token info (first/last few chars for debugging, not full token)
            String tokenPreview = userToken.length() > 10 
                ? userToken.substring(0, 4) + "..." + userToken.substring(userToken.length() - 4)
                : userToken;
            logger.debug("Validating token (preview: {}) with nacatech at: {}", tokenPreview, authUrl);
            
            // Check if nacatech token is configured
            if (nacaAuthToken == null || nacaAuthToken.isEmpty()) {
                logger.error("Nacatech auth token is not configured! Cannot validate user token.");
                return new AuthResponse(false, "Server configuration error: Auth token not set", null);
            }
            
            // Prepare request body
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("user_nonce", userToken);
            
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("NacaAuth-Token", nacaAuthToken);
            headers.set("Application-ID", applicationId);
            
            logger.info("Sending auth request to nacatech:");
            logger.info("  URL: {}", authUrl);
            logger.info("  Application-ID: {}", applicationId);
            logger.info("  user_nonce length: {}", userToken.length());
            logger.info("  user_nonce (preview): {}", userToken.length() > 10 ? userToken.substring(0, 4) + "..." + userToken.substring(userToken.length() - 4) : userToken);
            logger.info("  user_nonce (full): {}", userToken);
            logger.debug("  Request body: {}", requestBody);
            
            // Create request entity
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
            
            // Make POST request to external auth service
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                authUrl,
                HttpMethod.POST,
                requestEntity,
                new org.springframework.core.ParameterizedTypeReference<Map<String, Object>>() {}
            );
            
            logger.debug("Nacatech response status: {}", response.getStatusCode());
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Parse user info from response
                Map<String, Object> userData = response.getBody();
                
                logger.debug("Nacatech response body keys: {}", userData.keySet());
                
                if (userData == null) {
                    logger.warn("Nacatech returned empty response body");
                    return new AuthResponse(false, "Authentication failed: Empty response from auth service", null);
                }
                
                // Check if the response indicates an error
                Object statusObj = userData.get("status");
                logger.debug("Nacatech response status field: {}", statusObj);
                logger.debug("Full nacatech response: {}", userData);
                
                if (statusObj != null && "error".equals(statusObj.toString())) {
                    Object errorInfo = userData.get("info");
                    String errorMessage = errorInfo != null ? errorInfo.toString() : "Authentication failed";
                    logger.warn("Nacatech returned error status. Error info: {}", errorInfo);
                    logger.warn("Full nacatech error response: {}", userData);
                    // Don't cache failed authentications
                    return new AuthResponse(false, errorMessage, null);
                }
                
                // Check if we have a user id (successful authentication)
                // API returns "id" not "user_id"
                Object idObj = userData.get("id");
                if (idObj == null) {
                    // Fallback to user_id if id is not present
                    idObj = userData.get("user_id");
                }
                
                if (idObj == null) {
                    return new AuthResponse(false, "Authentication failed: No user information returned", null);
                }
                
                String userId = idObj.toString();
                UserInfo userInfo = new UserInfo(userId, userData);
                logger.info("Authentication successful for user ID: {}", userId);
                
                // Cache successful authentication
                AuthResponse successResponse = new AuthResponse(true, "Authentication successful", userInfo);
                tokenCache.put(userToken, new CachedAuthResponse(successResponse));
                logger.debug("Token cached for future requests");
                
                return successResponse;
            } else {
                logger.warn("Authentication failed: Invalid response from auth service");
                return new AuthResponse(false, "Authentication failed: Invalid response from auth service", null);
            }
            
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            logger.warn("HTTP Error during authentication - Status: {}, Message: {}", e.getStatusCode(), e.getMessage());
            if (e.getResponseBodyAsString() != null) {
                logger.debug("Nacatech error response body: {}", e.getResponseBodyAsString());
            }
            return new AuthResponse(false, "Authentication failed: " + e.getMessage(), null);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            logger.error("Network error connecting to nacatech service: {}", e.getMessage());
            return new AuthResponse(false, "Authentication service unavailable", null);
        } catch (Exception e) {
            logger.error("Unexpected exception during authentication - {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
            return new AuthResponse(false, "Authentication error: " + e.getMessage(), null);
        }
    }
}

