package com.example.restservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * Simple rate limiting filter to prevent DoS attacks
 * Limits requests per IP address
 */
public class RateLimitingFilter extends OncePerRequestFilter {
    
    private static final int MAX_REQUESTS_PER_MINUTE = 60;
    private static final long TIME_WINDOW_MS = 60_000; // 1 minute
    private static final Pattern IP_PATTERN = Pattern.compile(
        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
    );
    
    @Value("${rate.limit.trust-proxy:false}")
    private boolean trustProxy;
    
    private final Map<String, RequestCounter> requestCounts = new ConcurrentHashMap<>();
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // Skip rate limiting for auth endpoints
        String path = request.getRequestURI();
        if (path.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String clientId = getClientId(request);
        RequestCounter counter = requestCounts.computeIfAbsent(clientId, k -> new RequestCounter());
        
        long now = System.currentTimeMillis();
        
        synchronized (counter) {
            // Reset if time window has passed
            if (now - counter.windowStart > TIME_WINDOW_MS) {
                counter.count.set(0);
                counter.windowStart = now;
            }
            
            // Check rate limit
            if (counter.count.incrementAndGet() > MAX_REQUESTS_PER_MINUTE) {
                response.setStatus(429); // HTTP 429 Too Many Requests
                response.getWriter().write("{\"error\":\"Rate limit exceeded. Please try again later.\"}");
                response.setContentType("application/json");
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String getClientId(HttpServletRequest request) {
        // For rate limiting before authentication, use IP address
        // This filter runs before authentication, so we can't use user ID yet
        String ip = request.getRemoteAddr();
        
        // Only trust X-Forwarded-For if configured to trust proxy (e.g., behind CloudFlare)
        if (trustProxy) {
            String forwardedFor = request.getHeader("X-Forwarded-For");
            if (forwardedFor != null && !forwardedFor.isEmpty()) {
                // Extract first IP from comma-separated list
                String firstIp = forwardedFor.split(",")[0].trim();
                // Validate IP format to prevent spoofing
                if (isValidIpAddress(firstIp)) {
                    ip = firstIp;
                }
            }
        }
        
        return ip;
    }
    
    /**
     * Validate IP address format to prevent header injection/spoofing
     */
    private boolean isValidIpAddress(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        return IP_PATTERN.matcher(ip).matches();
    }
    
    private static class RequestCounter {
        final AtomicInteger count = new AtomicInteger(0);
        long windowStart = System.currentTimeMillis();
    }
}
