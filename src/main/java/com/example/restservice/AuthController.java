package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    /**
     * Helper method to extract token from request - tries multiple field names
     */
    private String extractTokenFromMap(Map<String, Object> rawMap) {
        if (rawMap == null) {
            return null;
        }
        
        // Log all keys in the map to see what the client is actually sending
        System.out.println("DEBUG: Available fields in request map: " + rawMap.keySet());
        System.out.println("DEBUG: Full request map contents: " + rawMap);
        
        // Try different possible field names
        String[] possibleFieldNames = {"user_nonce", "userNonce", "user_token", "userToken", "token", "nonce"};
        for (String fieldName : possibleFieldNames) {
            Object value = rawMap.get(fieldName);
            if (value != null) {
                String token = value.toString();
                if (!token.isEmpty()) {
                    System.out.println("Found token in field: " + fieldName + " = " + token);
                    return token;
                }
            }
        }
        
        return null;
    }
    
    /**
     * Login endpoint - receives user token and validates it with external auth service
     * POST /api/auth/login
     * Body: {"user_nonce": "token_from_user"}
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody Map<String, Object> rawRequest) {
        System.out.println("=== Auth Login Request received ===");
        System.out.println("Raw request map: " + rawRequest);
        System.out.println("Request map keys: " + (rawRequest != null ? rawRequest.keySet() : "null"));
        
        if (rawRequest == null || rawRequest.isEmpty()) {
            System.out.println("ERROR: Request body is null or empty");
            return new AuthResponse(false, "Request body is required", null);
        }
        
        // Extract token using helper method
        String token = extractTokenFromMap(rawRequest);
        
        if (token == null || token.isEmpty()) {
            System.out.println("ERROR: User token is missing or empty");
            System.out.println("Available fields were: " + rawRequest.keySet());
            return new AuthResponse(false, "User token is required. Expected field: user_nonce", null);
        }
        
        System.out.println("Validating user token: " + token);
        AuthResponse response = authService.validateUserToken(token);
        System.out.println("Auth validation result - Success: " + response.success() + ", Message: " + response.message());
        
        return response;
    }
    
    /**
     * Validate endpoint - validates an existing user token
     * POST /api/auth/validate
     * Body: {"user_nonce": "token_from_user"}
     */
    @PostMapping("/validate")
    public AuthResponse validate(@RequestBody Map<String, Object> rawRequest) {
        System.out.println("=== Auth Validate Request received ===");
        System.out.println("Raw request map: " + rawRequest);
        System.out.println("Request map keys: " + (rawRequest != null ? rawRequest.keySet() : "null"));
        
        if (rawRequest == null || rawRequest.isEmpty()) {
            System.out.println("ERROR: Request body is null or empty");
            return new AuthResponse(false, "Request body is required", null);
        }
        
        // Extract token using helper method
        String token = extractTokenFromMap(rawRequest);
        
        if (token == null || token.isEmpty()) {
            System.out.println("ERROR: User token is missing or empty");
            System.out.println("Available fields were: " + rawRequest.keySet());
            return new AuthResponse(false, "User token is required. Expected field: user_nonce", null);
        }
        
        System.out.println("Validating user token: " + token);
        AuthResponse response = authService.validateUserToken(token);
        System.out.println("Auth validation result - Success: " + response.success() + ", Message: " + response.message());
        
        return response;
    }
}

