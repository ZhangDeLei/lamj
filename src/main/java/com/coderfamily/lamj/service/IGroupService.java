package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.GroupPermissionInfo;
import com.coderfamily.lamj.model.GroupEntity;
import com.coderfamily.lamj.model.UserGroupEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 13:51
 */
public interface IGroupService {
    /**
     * 根据条件查询分组信息
     *
     * @param groupEntity
     * @return
     */
    List<GroupEntity> selectGroupByCondition(GroupEntity groupEntity);

    /**
     * 获取所有的用户组信息
     *
     * @return
     */
    List<GroupEntity> selectGroupList();

    /**
     * 根据用户ID获取当前用户所属分组信息
     *
     * @param UserId
     * @return
     */
    List<GroupEntity> selectGroupByUserId(int UserId);

    /**
     * 根据用户组id获取用户组权限信息
     *
     * @param GroupId
     * @return
     */
    List<GroupPermissionInfo> selectGroupPermissionByGroupId(int GroupId);

    /**
     * 判断是否有相同名称的分组
     *
     * @param Name
     * @return
     */
    boolean existsGroupByName(String Name);

    /**
     * 新增分组
     *
     * @param groupEntity
     * @return
     */
    Result insert(GroupEntity groupEntity);

    /**
     * 新增用户与用户组的关联关系
     * @param UserId
     * @param mIds
     * @return
     */
    Result insertUserGroup(int UserId,List<Integer> mIds);

    /**
     * 新增用户与用户组的关联关系
     * @param mList
     * @return
     */
    boolean insertUserGroup(List<UserGroupEntity> mList);

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
    Result delete(int Id);

    /**
     * 删除用户与分组的关联关系
     * @param UserId
     * @return
     */
    int deleteGroupRelation(int UserId);
}
