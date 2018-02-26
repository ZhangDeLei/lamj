package com.coderfamily.lamj.service;

import com.coderfamily.lamj.model.UserEntity;

/**
 * @author ZhangDL
 * @date 2018/1/25 16:13
 */
public interface IUserService {
    /**
     * 根据用户名获取用户信息
     *
     * @param UserAccount
     * @return
     */
    UserEntity selectUserByUserAccount(String UserAccount);

    /**
     * 根据用户名密码查询用户信息
     *
     * @param UserAccount
     * @param Password
     * @return
     */
    UserEntity login(String UserAccount, String Password);

    /**
     * 判断是否已经有该分组
     *
     * @param UserId
     * @param GroupId
     * @return
     */
    boolean existsUserRelationGroup(int UserId, int GroupId);

    /**
     * 判断是否已经有该权限
     *
     * @param UserId
     * @param PermissionId
     * @return
     */
    boolean existsUserRelationPermission(int UserId, int PermissionId);

    /**
     * 判断当前是否有同名的用户
     *
     * @param UserAccount
     * @return
     */
    boolean existsUserByUserAccount(String UserAccount);

    /**
     * 新增用户信息
     *
     * @param userEntity
     * @return
     */
    int insert(UserEntity userEntity);

    /**
     * 新增用于与分组的关联关系
     *
     * @param UserId
     * @param GroupId
     * @return
     */
    int insertGroupRelation(int UserId, int GroupId);

    /**
     * 新增用户与权限的关联关系
     *
     * @param UserId
     * @param PermissionId
     * @return
     */
    int insertPermissionRelation(int UserId, int PermissionId);

    /**
     * 更新账号信息
     *
     * @param userEntity
     * @return
     */
    int update(UserEntity userEntity);

    /**
     * 根据ID删除用户信息
     *
     * @param Id
     * @return
     */
    int delete(int Id);

    /**
     * 删除用户与分组的关联关系
     *
     * @param UserId
     * @param GroupId
     * @return
     */
    int deleteGroupRelation(int UserId, int GroupId);

    /**
     * 删除用户与权限的关联关系
     *
     * @param UserId
     * @param PermissionId
     * @return
     */
    int deletePermissionRelation(int UserId, int PermissionId);
}
