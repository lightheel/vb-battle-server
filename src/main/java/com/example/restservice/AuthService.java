package com.example.restservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    
    private final RestTemplate restTemplate;
    
    @Value("${auth.nacatech.url:https://auth.nacatech.es/userinfo}")
    private String authUrl;
    
    @Value("${auth.nacatech.token:}")
    private String nacaAuthToken;
    
    @Value("${auth.application.id:443654920}")
    private String applicationId;
    
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @PostConstruct
    public void init() {
        // Warn if token is not set (should be in secrets.properties)
        if (nacaAuthToken == null || nacaAuthToken.isEmpty()) {
            System.err.println("WARNING: auth.nacatech.token is not set! Please create secrets.properties file.");
        }
    }
    
    public AuthResponse validateUserToken(String userToken) {
        try {
            // Prepare request body
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("user_nonce", userToken);
            
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("NacaAuth-Token", nacaAuthToken);
            headers.set("Application-ID", applicationId);
            
            // Create request entity
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
            
            // Make POST request to external auth service
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                authUrl,
                HttpMethod.POST,
                requestEntity,
                new org.springframework.core.ParameterizedTypeReference<Map<String, Object>>() {}
            );
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Parse user info from response
                Map<String, Object> userData = response.getBody();
                
                if (userData == null) {
                    return new AuthResponse(false, "Authentication failed: Empty response from auth service", null);
                }
                
                // Check if the response indicates an error
                Object statusObj = userData.get("status");
                if (statusObj != null && "error".equals(statusObj.toString())) {
                    Object errorInfo = userData.get("info");
                    String errorMessage = errorInfo != null ? errorInfo.toString() : "Authentication failed";
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
                System.out.println("AuthService: Authentication successful for user ID: " + userId);
                return new AuthResponse(true, "Authentication successful", userInfo);
            } else {
                return new AuthResponse(false, "Authentication failed: Invalid response from auth service", null);
            }
            
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            System.err.println("AuthService: HTTP Error - " + e.getStatusCode() + ": " + e.getMessage());
            return new AuthResponse(false, "Authentication failed: " + e.getMessage(), null);
        } catch (Exception e) {
            System.err.println("AuthService: Exception - " + e.getClass().getSimpleName() + ": " + e.getMessage());
            return new AuthResponse(false, "Authentication error: " + e.getMessage(), null);
        }
    }
}

