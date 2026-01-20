package com.example.restservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Security filter to log and handle suspicious requests
 */
@Component
@Order(0) // Run before other filters
public class SecurityRequestFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(SecurityRequestFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String method = request.getMethod();
        String path = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        
        // Log suspicious requests
        if (isSuspiciousRequest(request)) {
            logger.warn("Suspicious request detected - Method: {}, Path: {}, RemoteAddr: {}, User-Agent: {}", 
                method, path, remoteAddr, request.getHeader("User-Agent"));
        }
        
        filterChain.doFilter(request, response);
    }
    
    /**
     * Check if request looks suspicious
     */
    private boolean isSuspiciousRequest(HttpServletRequest request) {
        String path = request.getRequestURI().toLowerCase();
        String userAgent = request.getHeader("User-Agent");
        
        // Check for common attack patterns
        if (path.contains("..") || path.contains("//")) {
            return true; // Path traversal attempt
        }
        
        if (path.contains("<script") || path.contains("javascript:")) {
            return true; // XSS attempt
        }
        
        // Check for common scanner user agents
        if (userAgent != null) {
            String ua = userAgent.toLowerCase();
            if (ua.contains("sqlmap") || ua.contains("nikto") || ua.contains("nmap") 
                || ua.contains("masscan") || ua.contains("zap") || ua.contains("burp")) {
                return true;
            }
        }
        
        return false;
    }
}
