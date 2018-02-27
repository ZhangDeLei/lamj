package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.PermissionMenuEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/3 14:37
 */
public interface IPermissionMenuService {
    /**
     * 新增权限与菜单的关联关系
     *
     * @param permis
     * @return
     */
    Result insert(List<PermissionMenuEntity> permis);

    /**
     * 删除权限与菜单的关联关系
     *
     * @param MenuId
     * @param PermissionId
     * @return
     */
    Result delete(int MenuId, int PermissionId);

    /**
     * 根据菜单ID删除菜单与权限的关联关系
     *
     * @param MenuId
     * @return
     */
    int deleteByMenuId(int MenuId);

    /**
     * 判断是否已经存在权限与菜单的关联关系
     *
     * @param MenuId
     * @param PermissionId
     * @return
     */
    boolean existsRelat(int MenuId, int PermissionId);

    /**
     * 根据菜单ID查询是否已经关联权限
     *
     * @param MenuId
     * @return
     */
    boolean checkDeleteByMenuId(int MenuId);
}
