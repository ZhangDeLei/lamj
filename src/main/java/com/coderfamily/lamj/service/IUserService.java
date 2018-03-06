package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.UserEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/1/25 16:13
 */
public interface IUserService {
    /**
     * 根据条件查询用户列表信息
     *
     * @param Name
     * @param UserAccount
     * @param Tel
     * @param StarLevelId
     * @param TypeId
     * @param Status
     * @param Sex
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<UserEntity> selectUserListByCondition(String Name, String UserAccount, String Tel, int StarLevelId, int TypeId, int Status, int Sex, int PageSize, int CurPage);

    /**
     * 获取所有用户信息
     * @return
     */
    List<UserEntity> selectAllUser();

    /**
     * 根据用户名获取用户信息
     *
     * @param UserAccount
     * @return
     */
    UserEntity selectUserByUserAccount(String UserAccount);

    /**
     * 根据id查询用户信息
     *
     * @param Id
     * @return
     */
    UserEntity selectUserById(int Id);

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
     * 新增用户组、权限与用户的关联关系
     * @param UserId
     * @param GroupIds
     * @param PerIds
     * @return
     */
    Result insertGroupAndPermission(int UserId, List<Integer> GroupIds, List<Integer> PerIds);

    /**
     * 更新账号信息
     *
     * @param userEntity
     * @return
     */
    Result update(UserEntity userEntity);

    /**
     * 更新密码
     *
     * @param params
     * @return
     */
    Result updatePassword(Map<String, String> params);

    /**
     * 更新头像
     *
     * @param params
     * @return
     */
    Result updatePhoto(Map<String, String> params);

    /**
     * 根据ID删除用户信息
     *
     * @param Id
     * @return
     */
    Result delete(int Id);

}
