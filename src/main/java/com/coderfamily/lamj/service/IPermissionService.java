package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.GroupPermissionEntity;
import com.coderfamily.lamj.model.PermissionEntity;
import com.coderfamily.lamj.model.UserPermissionEntity;

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
    Result insert(PermissionEntity permissionEntity);

    /**
     * 新增用户与权限的关联关系
     *
     * @param Id
     * @param mIds
     * @return
     */
    Result insertUserRelation(int Id,List<Integer> mIds);

    /**
     * 新增分组与权限的关联关系
     *
     * @param Id
     * @param mIds
     * @return
     */
    Result insertGroupRelation(int Id,List<Integer> mIds);

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
    Result delete(int Id);

    /**
     * 删除用户与权限的关联关系
     *
     * @param UserId
     * @return
     */
    int deleteUserRelation(int UserId);

    /**
     * 删除分组与权限的关联关系
     *
     * @param GroupId
     * @return
     */
    int deleteGroupRelation(int GroupId);

}
