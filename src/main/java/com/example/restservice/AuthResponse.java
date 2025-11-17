package com.example.restservice;

public record AuthResponse(boolean success, String message, UserInfo userInfo) { }

