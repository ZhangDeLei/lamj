package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.PermissionMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMenuMapper {
    /**
     * 新增权限菜单关联关系
     *
     * @param permis
     * @return
     */
    int insert(@Param("list") List<PermissionMenuEntity> permis);

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
     * 根据权限ID删除权限所有与菜单的关联关系
     * @param PermissionId
     * @return
     */
    int deleteByPermissionId(@Param("permissionId")int PermissionId);

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