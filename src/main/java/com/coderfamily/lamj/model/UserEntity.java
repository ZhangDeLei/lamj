package com.coderfamily.lamj.model;

public class UserEntity {
    private Integer id;

    private String userAccount;

    private String password;

    private String nickName;

    private String tel;

    private Integer sex;

    private String photoUrl;

    private Integer status;

    private Integer typeId;

    private String typeCode;

    private String typeName;

    private String email;

    private Integer starLevelId;

    private String starLevelCode;

    private String starLevelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getStarLevelId() {
        return starLevelId;
    }

    public void setStarLevelId(Integer starLevelId) {
        this.starLevelId = starLevelId;
    }

    public String getStarLevelCode() {
        return starLevelCode;
    }

    public void setStarLevelCode(String starLevelCode) {
        this.starLevelCode = starLevelCode;
    }

    public String getStarLevelName() {
        return starLevelName;
    }

    public void setStarLevelName(String starLevelName) {
        this.starLevelName = starLevelName;
    }
}