package com.coderfamily.lamj.model;

public class UserNewAuthEntity {
    private Integer id;

    private Integer userId;

    private Integer newId;

    private String newName;

    private String newAccount;

    private Boolean isAuth;

    private String authToken;

    private String authTokenExpire;

    private String refreshToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName == null ? null : newName.trim();
    }

    public String getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(String newAccount) {
        this.newAccount = newAccount == null ? null : newAccount.trim();
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        this.isAuth = isAuth;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken == null ? null : authToken.trim();
    }

    public String getAuthTokenExpire() {
        return authTokenExpire;
    }

    public void setAuthTokenExpire(String authTokenExpire) {
        this.authTokenExpire = authTokenExpire == null ? null : authTokenExpire.trim();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}