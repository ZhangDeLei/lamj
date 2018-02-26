package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.UserEntity;

/**
 * @author ZhangDL
 * @date 2018/1/26 14:48
 */
public class UserInfo {
    private UserEntity user;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
