package com.example.restservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Global exception handler to catch and handle common exceptions gracefully
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * Handle malformed multipart requests from bots/scanners
     * Returns 404 to obscure server details and reduce log noise
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Void> handleMultipartException(
            MultipartException ex, 
            HttpServletRequest request) {
        // Log at DEBUG level - these are expected from bots/scanners
        logger.debug("Malformed multipart request rejected - Path: {}, Method: {}, RemoteAddr: {}", 
            request.getRequestURI(), request.getMethod(), request.getRemoteAddr());
        
        // Return 404 to obscure that this is a multipart parsing error
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
