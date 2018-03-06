package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.GroupPermissionInfo;
import com.coderfamily.lamj.model.GroupEntity;
import com.coderfamily.lamj.model.UserGroupEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    /**
     * 根据条件查询分组信息
     *
     * @param groupEntity
     * @return
     */
    List<GroupEntity> selectGroupByCondition(GroupEntity groupEntity);

    /**
     * 获取所有用户组信息
     * @return
     */
    List<GroupEntity> selectGroupList(@Param("name") String Name);
    /**
     * 根据用户ID获取当前用户所属分组信息
     *
     * @param UserId
     * @return
     */
    List<GroupEntity> selectGroupByUserId(@Param("userId") int UserId);

    /**
     * 获取最大的Code
     * @return
     */
    String selectMaxCode();

    /**
     * 根据用户组ID查询用户组权限信息
     * @param GroupId
     * @return
     */
    List<GroupPermissionInfo> selectGroupPermissionByGroupId(@Param("id") int GroupId);

    /**
     * 判断是否有相同名称的分组
     *
     * @param Name
     * @return
     */
    boolean existsGroupByName(@Param("name") String Name);

    /**
     * 根据ID判断当前用户组是否已经包含权限信息
     * @param Id
     * @return
     */
    boolean existsGroupHasPermissionById(@Param("id") int Id);

    /**
     * 新增分组
     *
     * @param groupEntity
     * @return
     */
    int insert(GroupEntity groupEntity);

    /**
     * 新增用户与用户组的关联关系
     * @param mList
     * @return
     */
    int insertUserGroupRelat(@Param("list") List<UserGroupEntity> mList);

    /**
     * 更新分组
     *
     * @param groupEntity
     * @return
     */
    int update(GroupEntity groupEntity);

    /**
     * 删除分组
     *
     * @param Id
     * @return
     */
    int delete(@Param("id") int Id);

    /**
     * 删除用户与分组的关联关系
     * @param UserId
     * @return
     */
    int deleteGroupRelation(@Param("userId")int UserId);
}