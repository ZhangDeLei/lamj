package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.MenuInfo;
import com.coderfamily.lamj.model.MenuEntity;

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
     * 根据权限ID获取菜单树形列表
     * @param PermissionId
     * @return
     */
    List<MenuInfo> selectPermissionMenuByTree(int PermissionId);

    /**
     * 根据ParentId获取菜单列表
     * @param ParentId
     * @return
     */
    List<MenuEntity> selectMenuByParentId(int ParentId);

    /**
     * 判断是否存在子菜单
     * @param Id
     * @return
     */
    boolean selectHasChildMenu(int Id);

    /**
     * 根据id获取菜单详细信息
     * @param Id
     * @return
     */
    MenuEntity selectMenubyId(int Id);
    /**
     * 新增菜单
     *
     * @param menuEntity
     * @return
     */
    Result insert(MenuEntity menuEntity);

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
