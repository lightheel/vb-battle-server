package com.example.restservice.security;

import com.example.restservice.UserInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Custom authentication token that holds nacatech UserInfo
 */
public class NacatechAuthenticationToken extends AbstractAuthenticationToken {
    
    private final UserInfo userInfo;
    private final String credentials;
    
    public NacatechAuthenticationToken(UserInfo userInfo, String credentials, String principal) {
        super(Collections.emptyList());
        this.userInfo = userInfo;
        this.credentials = credentials;
        setAuthenticated(true);
    }
    
    @Override
    public Object getCredentials() {
        return credentials;
    }
    
    @Override
    public Object getPrincipal() {
        return userInfo != null ? userInfo.userId() : null;
    }
    
    public UserInfo getUserInfo() {
        return userInfo;
    }
}
