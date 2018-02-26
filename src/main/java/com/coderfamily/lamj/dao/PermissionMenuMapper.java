package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.PermissionMenuEntity;
import org.apache.ibatis.annotations.Param;

public interface PermissionMenuMapper {
    /**
     * 新增权限菜单关联关系
     *
     * @param permissionMenuEntity
     * @return
     */
    int insert(PermissionMenuEntity permissionMenuEntity);

    /**
     * 删除菜单与权限的关联关系
     *
     * @param MenuId
     * @param PermissionId
     * @return
     */
    int delete(@Param("menuId") int MenuId, @Param("permissionId") int PermissionId);

    /**
     * 根据菜单ID删除菜单与权限的关联关系
     * @param MenuId
     * @return
     */
    int deleteByMenuId(@Param("menuId")int MenuId);

    /**
     * 判断是否已经存在该菜单与权限的关联关系
     *
     * @param MenuId
     * @param PermissionId
     * @return
     */
    boolean existsRelat(@Param("menuId") int MenuId, @Param("permissionId") int PermissionId);

    /**
     * 根据菜单ID查询是否已经关联权限
     *
     * @param MenuId
     * @return
     */
    boolean checkDeleteByMenuId(@Param("menuId") int MenuId);
}