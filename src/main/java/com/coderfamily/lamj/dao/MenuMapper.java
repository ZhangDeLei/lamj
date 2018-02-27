package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.MenuInfo;
import com.coderfamily.lamj.model.MenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    /**
     * 根据用户ID获取用户的菜单列表
     *
     * @param UserId
     * @return
     */
    List<MenuInfo> selectMenuByUserId(@Param("userId") int UserId);

    /**
     * 获取树形的menu列表
     *
     * @return
     */
    List<MenuInfo> selectMenuByTree();

    /**
     * 根据ParentId获取菜单列表
     *
     * @param ParentId
     * @return
     */
    List<MenuEntity> selectMenuByParentId(@Param("id") int ParentId);

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
    List<MenuEntity> selectMenuByPermissionId(@Param("permissionId") int PermissionId);

    /**
     * 根据ID查询菜单的详细信息
     *
     * @param Id
     * @return
     */
    MenuEntity selectMenuById(@Param("id") int Id);

    /**
     * 根据parentid获取当前子菜单的最大编码
     *
     * @param ParentId
     * @return
     */
    String selectMenuCodeToMaxByParentId(@Param("parentId") int ParentId);

    /**
     * 判断是否存在子菜单
     * @param Id
     * @return
     */
    boolean selectHasChildMenu(@Param("Id")int Id);

    /**
     * 判断是否已经存在该菜单名称
     *
     * @param Name
     * @return
     */
    boolean existsMenuByName(@Param("name") String Name);

    /**
     * 新增菜单
     *
     * @param menuEntity
     * @return
     */
    int insert(MenuEntity menuEntity);

    /**
     * 更新菜单
     *
     * @param menuEntity
     * @return
     */
    int update(MenuEntity menuEntity);

    /**
     * 根据ID删除菜单
     *
     * @param Id
     * @return
     */
    int delete(@Param("id") int Id);
}