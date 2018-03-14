package com.coderfamily.lamj.domain;

/**
 * @author ZhangDL
 * @date 2018/3/14 09:26
 */
public class CompanyNewInfo extends NewAuthInfo {
    private int newId;
    private String newName;
    private int companyId;
    private String companyName;

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
