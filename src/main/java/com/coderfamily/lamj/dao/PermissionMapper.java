package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.PermissionEntity;
import com.coderfamily.lamj.model.UserPermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {

    /**
     * 根据用户获取用户权限列表
     *
     * @param UserId
     * @return
     */
    List<PermissionEntity> selectPermissionByUserId(@Param("userId") int UserId);

    /**
     * 根据条件查询权限列表信息
     *
     * @param permissionEntity
     * @return
     */
    List<PermissionEntity> selectPermissionByCondition(PermissionEntity permissionEntity);

    /**
     * 获取权限最大的编码
     *
     * @return
     */
    String selectPermissionCodeForMax();

    /**
     * 判断当前用户权限名称是否已经存在
     *
     * @param Name
     * @return
     */
    boolean existsPermissionByName(@Param("name") String Name);

    /**
     * 判断当前权限是否已被关联用户
     *
     * @param Id
     * @return
     */
    boolean existsPermissionUserRelat(@Param("id") int Id);

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
     * @param mList
     * @return
     */
    int insertUserRelation(@Param("list") List<UserPermissionEntity> mList);

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
    int delete(@Param("id") int Id);

    /**
     * 删除用户与权限的关联关系
     *
     * @param UserId
     * @return
     */
    int deleteUserRelation(@Param("userId") int UserId);
}