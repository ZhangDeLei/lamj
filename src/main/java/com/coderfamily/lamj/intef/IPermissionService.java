package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.PermissionEntity;
import com.coderfamily.lamj.model.UserPermissionEntity;
import com.github.pagehelper.PageInfo;

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
     * 根据条件查询权限列表信息
     *
     * @param Name
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<PermissionEntity> selectPermissionByPage(String Name,int PageSize,int CurPage);

    /**
     * 根据条件查询权限列表
     * @param entity
     * @return
     */
    List<PermissionEntity> selectPermissionByCondition(PermissionEntity entity);
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
     * 新增用户与权限的关联关系
     * @param mList
     * @return
     */
    boolean insertUserRelation(List<UserPermissionEntity> mList);

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

}
