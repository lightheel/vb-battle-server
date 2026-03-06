package com.example.restservice;

import java.util.Map;

public record UserInfo(String userId, Map<String, Object> additionalInfo, String displayName) {

    /** Backward-friendly constructor when displayName is not yet available (e.g. from cache). */
    public UserInfo(String userId, Map<String, Object> additionalInfo) {
        this(userId, additionalInfo, null);
    }
}

