package com.coderfamily.lamj.service;

import com.coderfamily.lamj.model.GroupEntity;

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
     * 根据用户ID获取当前用户所属分组信息
     *
     * @param UserId
     * @return
     */
    List<GroupEntity> selectGroupByUserId(int UserId);

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
    int insert(GroupEntity groupEntity);

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
    int delete(int Id);
}
