package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.TeamEntity;
import com.coderfamily.lamj.model.TeamUserEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/6 18:42
 */
public interface ITeamService {

    /**
     * 分页查询队伍列表
     * @param Name
     * @param CompanyId
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<TeamEntity> getTeamList(String Name,int CompanyId,int PageSize,int CurPage);

    /**
     * 根据条件查询队伍列表
     * @param entity
     * @return
     */
    List<TeamEntity> getTeamListByCondition(TeamEntity entity);

    /**
     * 新增队伍
     * @param entity
     * @return
     */
    Result insert(TeamEntity entity);

    /**
     * 新增队伍与用户的关联关系
     * @param entity
     * @return
     */
    int insertTeamUser(TeamUserEntity entity);
    /**
     * 更新队伍
     * @param entity
     * @return
     */
    Result update(TeamEntity entity);

    /**
     * 删除队伍
     * @param Id
     * @return
     */
    Result delete(int Id);

    /**
     * 根据队伍ID和用户ID删除用户与队伍的关联关系
     * @param TeamId
     * @param UserId
     * @return
     */
    int deleteTeamUser(int TeamId,int UserId);

    /**
     * 根据用户ID删除用户与队伍的关联关系
     * @param UserId
     * @return
     */
    int deleteTeamUserByUser(int UserId);
}
