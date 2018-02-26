package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.MenuEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 14:00
 */
public class MenuInfo extends MenuEntity {
    private List<MenuEntity> childs;

    public List<MenuEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuEntity> childs) {
        this.childs = childs;
    }
}
