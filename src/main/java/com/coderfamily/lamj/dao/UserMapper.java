package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 根据账号获取用户信息
     *
     * @param userAccount
     * @return
     */
    UserEntity selectUserByUserAccount(@Param("userAccount") String userAccount);

    /**
     * 登陆
     *
     * @param userAccount
     * @param password
     * @return
     */
    UserEntity login(@Param("userAccount") String userAccount, @Param("password") String password);

    /**
     * 判断当前账号是否已存在
     *
     * @param userAccount
     * @return
     */
    boolean existsUserByUserAccount(@Param("userAccount") String userAccount);

    /**
     * 判断当前账号是否已经关联了该分组
     *
     * @param UserId
     * @param GroupId
     * @return
     */
    boolean existsUserRelationGroup(@Param("userId") int UserId, @Param("groupId") int GroupId);

    /**
     * 判断当前账号是否已经关联了该权限
     *
     * @param UserId
     * @param PermissionId
     * @return
     */
    boolean existsUserRelationPermission(@Param("userId") int UserId, @Param("permissionId") int PermissionId);

    /**
     * 新增账号
     *
     * @param userEntity
     * @return
     */
    int insert(UserEntity userEntity);

    /**
     * 新增账号与分组的关联关系
     *
     * @param UserId
     * @param GroupId
     * @return
     */
    int insertGroupRelation(@Param("userId") int UserId, @Param("groupId") int GroupId);

    /**
     * 新增账号与权限的关联关系
     *
     * @param UserId
     * @param PermissionId
     * @return
     */
    int insertPermissionRelation(@Param("userId") int UserId, @Param("permissionId") int PermissionId);

    /**
     * 更新账号信息
     *
     * @param userEntity
     * @return
     */
    int update(UserEntity userEntity);

    /**
     * 删除账号
     *
     * @param Id
     * @return
     */
    int delete(@Param("id") int Id);

    /**
     * 删除账号与分组的关联关系
     *
     * @param UserId
     * @param GroupId
     * @return
     */
    int deleteGroupRelation(@Param("userId") int UserId, @Param("groupId") int GroupId);

    /**
     * 删除账号与权限的关联关系
     *
     * @param UserId
     * @param PermissionId
     * @return
     */
    int deletePermissionRelation(@Param("userId") int UserId, @Param("permissionId") int PermissionId);
}