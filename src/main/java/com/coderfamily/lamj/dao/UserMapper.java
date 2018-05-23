package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.UserDetail;
import com.coderfamily.lamj.domain.UserStatisticsInfo;
import com.coderfamily.lamj.model.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 根据条件查询用户列表信息
     * @param entity
     * @return
     */
    List<UserDetail> selectUserListByCondition(UserDetail entity);

    /**
     * 根据账号获取用户信息
     *
     * @param userAccount
     * @return
     */
    UserDetail selectUserByUserAccount(@Param("userAccount") String userAccount);

    /**
     * 根据ID查询用户信息
     * @param Id
     * @return
     */
    UserEntity selectUserById(@Param("id")int Id);
    /**
     * 登陆
     *
     * @param userAccount
     * @param password
     * @return
     */
    UserEntity login(@Param("userAccount") String userAccount, @Param("password") String password);

    /**
     * 获取用户统计信息
     * @param Id
     * @return
     */
    UserStatisticsInfo selectUserByStatistics(@Param("id") int Id);

    /**
     * 获取平台用户总数
     * @return
     */
    int selectCount();

    /**
     * 判断当前账号是否已存在
     *
     * @param userAccount
     * @return
     */
    boolean existsUserByUserAccount(@Param("userAccount") String userAccount);

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
}