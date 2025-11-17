package com.example.restservice;

import java.util.Map;

public record UserInfo(String userId, Map<String, Object> additionalInfo) { }

