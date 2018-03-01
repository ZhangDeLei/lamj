package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.GroupPermissionEntity;

/**
 * @author ZhangDL
 * @date 2018/3/1 11:31
 */
public class GroupPermissionInfo extends GroupPermissionEntity{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
