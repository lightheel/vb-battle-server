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
        // Debug: Check if token is loaded
        System.out.println("AuthService: Initializing - Token loaded: " + (nacaAuthToken != null && !nacaAuthToken.isEmpty() ? "YES (length: " + nacaAuthToken.length() + ")" : "NO"));
        System.out.println("AuthService: Current working directory: " + System.getProperty("user.dir"));
        
        // Warn if token is not set (should be in secrets.properties)
        if (nacaAuthToken == null || nacaAuthToken.isEmpty()) {
            System.err.println("WARNING: auth.nacatech.token is not set!");
            System.err.println("Please create secrets.properties file in the project root directory");
            System.err.println("Expected location: " + System.getProperty("user.dir") + "/secrets.properties");
            System.err.println("The application may not function correctly without authentication token.");
        }
    }
    
    public AuthResponse validateUserToken(String userToken) {
        System.out.println("AuthService: Starting token validation");
        System.out.println("AuthService: Token received: " + userToken);
        System.out.println("AuthService: Auth URL: " + authUrl);
        System.out.println("AuthService: Application ID: " + applicationId);
        
        try {
            // Prepare request body
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("user_nonce", userToken);
            System.out.println("AuthService: Request body prepared: " + requestBody);
            
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("NacaAuth-Token", nacaAuthToken);
            headers.set("Application-ID", applicationId);
            
            // Log header details (mask token for security)
            String maskedToken = nacaAuthToken != null && nacaAuthToken.length() > 4 
                ? nacaAuthToken.substring(0, 4) + "..." + nacaAuthToken.substring(nacaAuthToken.length() - 4)
                : "null";
            System.out.println("AuthService: Headers prepared:");
            System.out.println("  - NacaAuth-Token: " + maskedToken + " (length: " + (nacaAuthToken != null ? nacaAuthToken.length() : 0) + ")");
            System.out.println("  - Application-ID: " + applicationId);
            System.out.println("  - Content-Type: " + headers.getContentType());
            System.out.println("  - All headers: " + headers);
            
            // Create request entity
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
            
            // Make POST request to external auth service
            System.out.println("AuthService: Sending POST request to: " + authUrl);
            System.out.println("AuthService: Request body JSON: " + requestBody);
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                authUrl,
                HttpMethod.POST,
                requestEntity,
                new org.springframework.core.ParameterizedTypeReference<Map<String, Object>>() {}
            );
            
            System.out.println("AuthService: Response status: " + response.getStatusCode());
            System.out.println("AuthService: Response body: " + response.getBody());
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Parse user info from response
                Map<String, Object> userData = response.getBody();
                
                if (userData == null) {
                    System.out.println("AuthService: Authentication failed - Response body is null");
                    return new AuthResponse(false, "Authentication failed: Empty response from auth service", null);
                }
                
                // Check if the response indicates an error
                Object statusObj = userData.get("status");
                if (statusObj != null && "error".equals(statusObj.toString())) {
                    Object errorInfo = userData.get("info");
                    String errorMessage = errorInfo != null ? errorInfo.toString() : "Authentication failed";
                    System.out.println("AuthService: Authentication failed - " + errorMessage);
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
                    System.out.println("AuthService: Authentication failed - No id or user_id in response");
                    System.out.println("AuthService: Available fields in response: " + userData.keySet());
                    return new AuthResponse(false, "Authentication failed: No user information returned", null);
                }
                
                String userId = idObj.toString();
                System.out.println("AuthService: Authentication successful for user ID: " + userId);
                UserInfo userInfo = new UserInfo(userId, userData);
                return new AuthResponse(true, "Authentication successful", userInfo);
            } else {
                System.out.println("AuthService: Authentication failed - Invalid response from auth service");
                return new AuthResponse(false, "Authentication failed: Invalid response from auth service", null);
            }
            
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            System.out.println("AuthService: HTTP Client Error - Status: " + e.getStatusCode() + ", Message: " + e.getMessage());
            System.out.println("AuthService: Response body: " + e.getResponseBodyAsString());
            return new AuthResponse(false, "Authentication failed: " + e.getMessage(), null);
        } catch (Exception e) {
            System.out.println("AuthService: Exception occurred - " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return new AuthResponse(false, "Authentication error: " + e.getMessage(), null);
        }
    }
}

