package com.coderfamily.lamj.model;

public class NewOperatorEntity {
    private Integer newId;

    private String newName;

    private Integer oprTypeId;

    private String oprTypeCode;

    private String oprTypeName;

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

    public Integer getOprTypeId() {
        return oprTypeId;
    }

    public void setOprTypeId(Integer oprTypeId) {
        this.oprTypeId = oprTypeId;
    }

    public String getOprTypeCode() {
        return oprTypeCode;
    }

    public void setOprTypeCode(String oprTypeCode) {
        this.oprTypeCode = oprTypeCode == null ? null : oprTypeCode.trim();
    }

    public String getOprTypeName() {
        return oprTypeName;
    }

    public void setOprTypeName(String oprTypeName) {
        this.oprTypeName = oprTypeName == null ? null : oprTypeName.trim();
    }
}