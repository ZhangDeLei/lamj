package com.coderfamily.lamj.common.security.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ZhangDL
 * @date 2018/1/26 13:42
 */
public class AuthcToken implements AuthenticationToken {
    private String token;

    public AuthcToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
