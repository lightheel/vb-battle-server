package com.example.restservice;

import com.example.restservice.security.SessionTokenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private SessionTokenService sessionTokenService;
    
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
     * After validation, creates a server-side session token for subsequent requests
     * POST /api/auth/login
     * Body: {"user_nonce": "token_from_user"}
     * Response includes sessionToken field for use in subsequent requests
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> rawRequest, HttpServletResponse httpResponse) {
        Map<String, Object> response = new HashMap<>();
        
        if (rawRequest == null || rawRequest.isEmpty()) {
            response.put("success", false);
            response.put("message", "Request body is required");
            return response;
        }
        
        // Extract token using helper method
        String token = extractTokenFromMap(rawRequest);
        
        if (token == null || token.isEmpty()) {
            response.put("success", false);
            response.put("message", "User token is required. Expected field: user_nonce");
            return response;
        }
        
        // Validate with nacatech
        AuthResponse authResponse = authService.validateUserToken(token);
        
        if (authResponse.success() && authResponse.userInfo() != null) {
            // Create server-side session token
            String sessionToken = sessionTokenService.createSession(authResponse.userInfo());
            
            // Return session token in both response body and header (for convenience)
            httpResponse.setHeader("X-Session-Token", sessionToken);
            
            response.put("success", true);
            response.put("message", "Authentication successful");
            response.put("userInfo", Map.of("userId", authResponse.userInfo().userId()));
            response.put("sessionToken", sessionToken); // Return session token to client
            return response;
        } else {
            response.put("success", false);
            response.put("message", authResponse.message());
            return response;
        }
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

