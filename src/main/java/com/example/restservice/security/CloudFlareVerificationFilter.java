package com.example.restservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter to verify requests are coming through CloudFlare proxy.
 * When enabled, rejects direct access to the server.
 * 
 * CloudFlare sets the CF-Ray header on all proxied requests, which is
 * difficult to spoof without being on CloudFlare's network.
 * 
 * This filter runs first in the security filter chain to reject
 * non-CloudFlare traffic as early as possible.
 */
public class CloudFlareVerificationFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(CloudFlareVerificationFilter.class);
    
    @Value("${cloudflare.verify:false}")
    private boolean verifyCloudFlare;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // Skip verification if disabled (for local development)
        if (!verifyCloudFlare) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // Check for CloudFlare header - CF-Ray is set by CloudFlare on all proxied requests
        String cfRay = request.getHeader("CF-Ray");
        if (cfRay == null || cfRay.isEmpty()) {
            // Request not coming through CloudFlare - return 404 to avoid information disclosure
            String method = request.getMethod();
            String path = request.getRequestURI();
            String remoteAddr = request.getRemoteAddr();
            
            logger.debug("Request rejected - not coming through CloudFlare: {} {} from {} (returning 404)", 
                method, path, remoteAddr);
            
            response.setStatus(404);
            return;
        }
        
        // Request is coming through CloudFlare - continue
        filterChain.doFilter(request, response);
    }
}
