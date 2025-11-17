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
        
        // Try different possible field names
        String[] possibleFieldNames = {"user_nonce", "userNonce", "user_token", "userToken", "token", "nonce"};
        for (String fieldName : possibleFieldNames) {
            Object value = rawMap.get(fieldName);
            if (value != null) {
                String token = value.toString();
                if (!token.isEmpty()) {
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
        if (rawRequest == null || rawRequest.isEmpty()) {
            return new AuthResponse(false, "Request body is required", null);
        }
        
        // Extract token using helper method
        String token = extractTokenFromMap(rawRequest);
        
        if (token == null || token.isEmpty()) {
            return new AuthResponse(false, "User token is required. Expected field: user_nonce", null);
        }
        
        return authService.validateUserToken(token);
    }
    
    /**
     * Validate endpoint - validates an existing user token
     * POST /api/auth/validate
     * Body: {"user_nonce": "token_from_user"}
     */
    @PostMapping("/validate")
    public AuthResponse validate(@RequestBody Map<String, Object> rawRequest) {
        if (rawRequest == null || rawRequest.isEmpty()) {
            return new AuthResponse(false, "Request body is required", null);
        }
        
        // Extract token using helper method
        String token = extractTokenFromMap(rawRequest);
        
        if (token == null || token.isEmpty()) {
            return new AuthResponse(false, "User token is required. Expected field: user_nonce", null);
        }
        
        return authService.validateUserToken(token);
    }
}

