package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.UserEntity;

/**
 * @author ZhangDL
 * @date 2018/3/7 10:32
 */
public class UserDetail extends UserEntity {
    private int companyId;
    private int teamId;
    private int permissionId;
    private String teamName;
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
