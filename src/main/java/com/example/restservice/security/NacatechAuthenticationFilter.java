package com.example.restservice.security;

import com.example.restservice.AuthService;
import com.example.restservice.AuthResponse;
import com.example.restservice.UserInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * Custom authentication filter that validates nacatech tokens from Authorization header
 * or X-User-Token header and sets up Spring Security authentication context.
 */
public class NacatechAuthenticationFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(NacatechAuthenticationFilter.class);
    
    // Common bot/scraper probe paths that don't have endpoints - skip authentication
    private static final Set<String> BOT_PROBE_PATHS = Set.of(
        "/",                    // Root path
        "/favicon.ico",         // Browser favicon requests
        "/robots.txt",          // Search engine crawler file
        "/json",                // Common API probe path
        "/json/",               // Common API probe path (with trailing slash)
        "/api",                 // API root (no endpoint)
        "/api/",                // API root with trailing slash
        "/version",             // Version/info endpoint probe
        "/info",                // Info endpoint probe
        "/health",              // Health check probe
        "/status",              // Status endpoint probe
        "/wp-admin",            // WordPress admin probe
        "/wp-admin/",           // WordPress admin probe (with trailing slash)
        "/admin",               // Admin panel probe
        "/admin/",              // Admin panel probe (with trailing slash)
        "/.well-known",         // Well-known paths (Let's Encrypt, etc.)
        "/.well-known/"         // Well-known paths with trailing slash
    );
    
    private final AuthService authService;
    private final SessionTokenService sessionTokenService;
    
    public NacatechAuthenticationFilter(AuthService authService, SessionTokenService sessionTokenService) {
        this.authService = authService;
        this.sessionTokenService = sessionTokenService;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String path = request.getRequestURI();
        String method = request.getMethod();
        
        logger.debug("Authentication filter: {} {}", method, path);
        
        // Skip authentication for auth endpoints, OPTIONS (CORS preflight), actuator endpoints, and common bot/scraper probe paths
        if (path.startsWith("/api/auth") || "OPTIONS".equals(method) || path.startsWith("/actuator") || BOT_PROBE_PATHS.contains(path)) {
            logger.debug("Skipping authentication for: {} {}", method, path);
            filterChain.doFilter(request, response);
            return;
        }
        
        // Extract token - try session token first, then nacatech token
        String sessionToken = extractSessionToken(request);
        String nacatechToken = extractToken(request);
        
        // Log what headers we received (for debugging) - always log when no valid auth
        String xSessionHeader = request.getHeader("X-Session-Token");
        String xUserTokenHeader = request.getHeader("X-User-Token");
        String authHeader = request.getHeader("Authorization");
        
        if (sessionToken == null && nacatechToken == null) {
            logger.info("No authentication headers found for {} {}. Headers received: X-Session-Token={}, X-User-Token={}, Authorization={}", 
                method, path,
                xSessionHeader != null ? ("present: " + (xSessionHeader.length() > 0 ? "non-empty" : "empty")) : "missing",
                xUserTokenHeader != null ? ("present: " + (xUserTokenHeader.length() > 0 ? "non-empty" : "empty")) : "missing",
                authHeader != null ? ("present: " + (authHeader.length() > 0 ? "non-empty" : "empty")) : "missing");
        }
        
        UserInfo userInfo = null;
        
        // Try session token first (faster, no nacatech call)
        if (sessionToken != null && !sessionToken.isEmpty()) {
            logger.debug("Session token found, validating...");
            userInfo = sessionTokenService.validateSession(sessionToken);
            if (userInfo != null) {
                logger.debug("Session token validated for user: {}", userInfo.userId());
            } else {
                logger.debug("Session token validation failed (expired or invalid)");
            }
        }
        
        // Fallback to nacatech token if session token not found/invalid
        if (userInfo == null && nacatechToken != null && !nacatechToken.isEmpty()) {
            logger.debug("No valid session token, trying nacatech token...");
            AuthResponse authResponse = authService.validateUserToken(nacatechToken);
            
            if (authResponse.success() && authResponse.userInfo() != null) {
                userInfo = authResponse.userInfo();
                // Create session token for future requests
                String newSessionToken = sessionTokenService.createSession(userInfo);
                // Optionally return session token in response header for client to use
                response.setHeader("X-Session-Token", newSessionToken);
                logger.debug("Nacatech token validated, session created for user: {}", userInfo.userId());
            } else {
                logger.warn("Nacatech token validation failed: {}", 
                    authResponse != null ? authResponse.message() : "null response");
            }
        }
        
        // Set authentication if we have valid user info
        if (userInfo != null) {
            NacatechAuthenticationToken authentication = new NacatechAuthenticationToken(
                userInfo,
                null,
                userInfo.userId()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Authentication successful for user: {}", userInfo.userId());
        } else {
            // No valid authentication - clear context
            SecurityContextHolder.clearContext();
            logger.warn("No valid authentication found for request: {} {}. Client must send X-Session-Token header (from /api/auth/login) or X-User-Token header (nacatech token).", method, path);
        }
        
        filterChain.doFilter(request, response);
    }
    
    /**
     * Extract session token from headers
     */
    private String extractSessionToken(HttpServletRequest request) {
        // Try X-Session-Token header first
        String sessionToken = request.getHeader("X-Session-Token");
        if (sessionToken != null && !sessionToken.isEmpty()) {
            return sessionToken.trim();
        }
        
        // Also check Authorization header for session token (Bearer format)
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7).trim();
            // Session tokens are longer (Base64 encoded 32 bytes = ~43 chars)
            // Nacatech tokens appear to be shorter (22 chars)
            if (token.length() > 30) {
                return token; // Likely a session token
            }
        }
        
        return null;
    }
    
    /**
     * Extract nacatech token from headers
     */
    private String extractToken(HttpServletRequest request) {
        // Try X-User-Token header (nacatech token)
        String userToken = request.getHeader("X-User-Token");
        if (userToken != null && !userToken.isEmpty()) {
            return userToken.trim();
        }
        
        // Try Authorization header if it looks like a nacatech token (shorter)
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7).trim();
            // Nacatech tokens are shorter (~22 chars)
            if (token.length() <= 30) {
                return token;
            }
        }
        
        return null;
    }
}
