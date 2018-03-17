package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.UserDetail;
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
    PageInfo<UserDetail> selectUserListByCondition(String Name, String UserAccount, String Tel, int StarLevelId,
                                                 int TypeId, Boolean Status, int Sex, int CompanyId, int TeamId, int PageSize, int CurPage);

    /**
     * 获取所有用户信息
     *
     * @return
     */
    List<UserDetail> selectAllUser();

    /**
     * 获取企业的所有用户列表
     * @return
     */
    List<UserDetail> selectAllUserByCompanyId(int CompanyId);

    /**
     * 根据用户名获取用户信息
     *
     * @param UserAccount
     * @return
     */
    UserDetail selectUserByUserAccount(String UserAccount);

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
    Result insert(UserDetail userEntity, boolean isCustom);

    /**
     * 更新账号信息
     *
     * @param userEntity
     * @param isCustome
     * @return
     */
    Result update(UserDetail userEntity,boolean isCustome);

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
     * 更新最后一次登录时间
     * @param UserId
     * @param LastLoginTime
     * @return
     */
    int updateLastLoginTime(int UserId);

    /**
     * 根据ID删除用户信息
     *
     * @param Id
     * @return
     */
    Result delete(int Id);

}
