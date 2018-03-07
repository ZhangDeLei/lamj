package com.coderfamily.lamj.domain;

/**
 * @author ZhangDL
 * @date 2018/1/26 14:48
 */
public class UserInfo {
    private UserDetail user;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }
}
