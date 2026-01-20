package com.example.restservice.security;

import com.example.restservice.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Service for managing server-side session tokens
 * After initial nacatech validation, clients use session tokens for subsequent requests
 */
@Service
public class SessionTokenService {
    
    private static final Logger logger = LoggerFactory.getLogger(SessionTokenService.class);
    private static final long SESSION_TTL_MS = 3_600_000; // 1 hour session TTL
    private static final int TOKEN_LENGTH = 32;
    
    private final SecureRandom secureRandom = new SecureRandom();
    private final ConcurrentHashMap<String, SessionInfo> sessions = new ConcurrentHashMap<>();
    private final ScheduledExecutorService cleanupScheduler = Executors.newScheduledThreadPool(1);
    
    public SessionTokenService() {
        // Schedule periodic cleanup of expired sessions
        cleanupScheduler.scheduleAtFixedRate(this::cleanupExpiredSessions, 1, 1, TimeUnit.MINUTES);
    }
    
    /**
     * Create a new session token after successful nacatech authentication
     */
    public String createSession(UserInfo userInfo) {
        String sessionToken = generateSecureToken();
        SessionInfo sessionInfo = new SessionInfo(userInfo, System.currentTimeMillis());
        sessions.put(sessionToken, sessionInfo);
        logger.debug("Created session token for user: {}", userInfo.userId());
        return sessionToken;
    }
    
    /**
     * Validate a session token and return user info if valid
     */
    public UserInfo validateSession(String sessionToken) {
        if (sessionToken == null || sessionToken.isEmpty()) {
            return null;
        }
        
        SessionInfo sessionInfo = sessions.get(sessionToken);
        if (sessionInfo == null) {
            logger.debug("Session token not found in cache");
            return null;
        }
        
        long age = System.currentTimeMillis() - sessionInfo.createdAt;
        if (age > SESSION_TTL_MS) {
            logger.debug("Session token expired (age: {}ms)", age);
            sessions.remove(sessionToken);
            return null;
        }
        
        // Update last access time
        sessionInfo.lastAccess = System.currentTimeMillis();
        logger.debug("Session token validated for user: {}", sessionInfo.userInfo.userId());
        return sessionInfo.userInfo;
    }
    
    /**
     * Invalidate a session token (logout)
     */
    public void invalidateSession(String sessionToken) {
        if (sessionToken != null) {
            sessions.remove(sessionToken);
            logger.debug("Session token invalidated");
        }
    }
    
    /**
     * Generate a secure random token
     */
    private String generateSecureToken() {
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
    
    /**
     * Cleanup expired sessions
     */
    private void cleanupExpiredSessions() {
        long now = System.currentTimeMillis();
        sessions.entrySet().removeIf(entry -> {
            long age = now - entry.getValue().createdAt;
            if (age > SESSION_TTL_MS) {
                logger.debug("Cleaned up expired session for user: {}", entry.getValue().userInfo.userId());
                return true;
            }
            return false;
        });
    }
    
    /**
     * Session information stored in memory
     */
    private static class SessionInfo {
        final UserInfo userInfo;
        final long createdAt;
        long lastAccess;
        
        SessionInfo(UserInfo userInfo, long createdAt) {
            this.userInfo = userInfo;
            this.createdAt = createdAt;
            this.lastAccess = createdAt;
        }
    }
}
