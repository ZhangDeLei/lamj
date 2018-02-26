package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.MenuInfo;
import com.coderfamily.lamj.model.MenuEntity;
import com.coderfamily.lamj.model.PermissionMenuEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 13:51
 */
public interface IMenuService {
    /**
     * 根据用户ID获取用户的菜单列表
     *
     * @param UserId
     * @return
     */
    List<MenuInfo> selectMenuByUserId(int UserId);

    /**
     * 根据条件查询菜单信息
     *
     * @param menuEntity
     * @return
     */
    List<MenuEntity> selectMenuByCondition(MenuEntity menuEntity);

    /**
     * 根据权限ID获取菜单列表
     *
     * @param PermissionId
     * @return
     */
    List<MenuEntity> selectMenuByPermissionId(int PermissionId);

    /**
     * 获取树形的menu列表
     * @return
     */
    List<MenuInfo> selectMenuByTree();

    /**
     * 根据ParentId获取菜单列表
     * @param ParentId
     * @return
     */
    List<MenuEntity> selectMenuByParentId(int ParentId);


    /**
     * 新增菜单
     *
     * @param menuEntity
     * @return
     */
    Result insert(MenuEntity menuEntity);

    /**
     * 新增权限菜单关联关系
     *
     * @param permissionMenuEntity
     * @return
     */
    Result insertPermissionRelation(PermissionMenuEntity permissionMenuEntity);

    /**
     * 更新菜单
     *
     * @param menuEntity
     * @return
     */
    Result update(MenuEntity menuEntity);

    /**
     * 根据ID删除菜单
     *
     * @param Id
     * @return
     */
    Result delete(int Id);

    /**
     * 删除权限与菜单的关联关系
     *
     * @param PermissionId
     * @param MenuId
     * @return
     */
    Result deletePermissionRelation(int PermissionId, int MenuId);
}
