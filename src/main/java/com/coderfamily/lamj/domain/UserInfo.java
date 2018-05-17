package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.CompanyEntity;

/**
 * @author ZhangDL
 * @date 2018/1/26 14:48
 */
public class UserInfo {
    private UserDetail user;
    private CompanyEntity company;
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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
