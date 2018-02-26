package com.coderfamily.lamj.service;

import com.coderfamily.lamj.model.PermissionEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 13:51
 */
public interface IPermissionService {
    /**
     * 根据用户获取用户权限列表
     *
     * @param UserId
     * @return
     */
    List<PermissionEntity> selectPermissionByUserId(int UserId);

    /**
     * 根据分组ID获取权限列表
     *
     * @param GroupId
     * @return
     */
    List<PermissionEntity> selectPermissionByGroupId(int GroupId);

    /**
     * 根据条件查询权限列表信息
     *
     * @param permissionEntity
     * @return
     */
    List<PermissionEntity> selectPermissionByCondition(PermissionEntity permissionEntity);

    /**
     * 判断当前用户权限名称是否已经存在
     *
     * @param Name
     * @return
     */
    boolean existsPermissionByName(String Name);

    /**
     * 新增权限
     *
     * @param permissionEntity
     * @return
     */
    int insert(PermissionEntity permissionEntity);

    /**
     * 新增用户与权限的关联关系
     *
     * @param UserId
     * @param PermissionId
     * @return
     */
    int insertUserRelation(int UserId, int PermissionId);

    /**
     * 新增分组与权限的关联关系
     *
     * @param GroupId
     * @param PermissionId
     * @return
     */
    int insertGroupRelation(int GroupId, int PermissionId);

    /**
     * 更新权限
     *
     * @param permissionEntity
     * @return
     */
    int update(PermissionEntity permissionEntity);

    /**
     * 根据ID删除权限信息
     *
     * @param Id
     * @return
     */
    int delete(int Id);

    /**
     * 删除用户与权限的关联关系
     *
     * @param UserId
     * @param PermissionId
     * @return
     */
    int deleteUserRelation(int UserId, int PermissionId);

    /**
     * 删除分组与权限的关联关系
     *
     * @param GroupId
     * @param PermissionId
     * @return
     */
    int deleteGroupRelation(int GroupId, int PermissionId);
}
