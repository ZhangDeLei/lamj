package com.coderfamily.lamj.model;

import java.util.Date;

public class CompanyEntity {
    private Integer id;

    private String name;

    private String shortName;

    private String concatUserName;

    private String concatUserPhone;

    private String address;

    private String createDate;

    private String expiredDate;

    private int maxNumUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getConcatUserName() {
        return concatUserName;
    }

    public void setConcatUserName(String concatUserName) {
        this.concatUserName = concatUserName == null ? null : concatUserName.trim();
    }

    public String getConcatUserPhone() {
        return concatUserPhone;
    }

    public void setConcatUserPhone(String concatUserPhone) {
        this.concatUserPhone = concatUserPhone == null ? null : concatUserPhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getMaxNumUser() {
        return maxNumUser;
    }

    public void setMaxNumUser(int maxNumUser) {
        this.maxNumUser = maxNumUser;
    }
}