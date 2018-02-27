package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.PermissionEntity;
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
     * 根据分组ID获取权限列表
     *
     * @param GroupId
     * @return
     */
    List<PermissionEntity> selectPermissionByGroupId(@Param("groupId") int GroupId);

    /**
     * 根据条件查询权限列表信息
     *
     * @param permissionEntity
     * @return
     */
    List<PermissionEntity> selectPermissionByCondition(PermissionEntity permissionEntity);

    /**
     * 获取权限最大的编码
     * @return
     */
    String selectPermissionCodeForMax();

    /**
     * 判断当前用户权限名称是否已经存在
     * @param Name
     * @return
     */
    boolean existsPermissionByName(@Param("name") String Name);

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
    int insertUserRelation(@Param("userId") int UserId, @Param("permissionId") int PermissionId);

    /**
     * 新增分组与权限的关联关系
     *
     * @param GroupId
     * @param PermissionId
     * @return
     */
    int insertGroupRelation(@Param("groupId") int GroupId, @Param("permissionId") int PermissionId);

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
     * @param PermissionId
     * @return
     */
    int deleteUserRelation(@Param("userId") int UserId, @Param("permissionId") int PermissionId);

    /**
     * 删除分组与权限的关联关系
     *
     * @param GroupId
     * @param PermissionId
     * @return
     */
    int deleteGroupRelation(@Param("groupId") int GroupId, @Param("permissionId") int PermissionId);
}