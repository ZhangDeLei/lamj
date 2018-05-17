package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.UserPermissionEntity;

/**
 * @author ZhangDL
 * @date 2018/3/1 11:31
 */
public class UserPermissionInfo extends UserPermissionEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
