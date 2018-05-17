package com.coderfamily.lamj.model;

/**
 * @author ZhangDL
 * @date 2018/3/1 10:20
 */
public class UserPermissionEntity {
    private int userId;
    private int permissionId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
