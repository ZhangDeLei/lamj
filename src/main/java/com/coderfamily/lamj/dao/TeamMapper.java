package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.TeamEntity;
import com.coderfamily.lamj.model.TeamTaskEntity;
import com.coderfamily.lamj.model.TeamUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamMapper {
    List<TeamEntity> selectTeamListByName(@Param("name") String Name, @Param("companyId") int CompanyId);

    List<TeamEntity> selectTeamListByCondition(TeamEntity entity);

    List<TeamEntity> selectByTaskId(@Param("taskId") int TaskId, @Param("companyId") int CompanyId);

    List<TeamEntity> selectByUserId(@Param("userId") int UserId);

    TeamEntity selectTeamById(@Param("id") int Id);

    boolean existsTeamByName(@Param("name") String Name, @Param("companyId") int CompanyId);

    int insert(TeamEntity entity);

    int insertTeamUser(TeamUserEntity entity);

    int insertTeamTask(@Param("list") List<TeamTaskEntity> entities);

    int update(TeamEntity entity);

    int delete(@Param("id") int Id);

    int deleteUserRelat(@Param("id") int Id);

    int deleteTeamUser(@Param("teamId") int TeamId, @Param("userId") int UserId);

    int deleteTeamUserByUserId(@Param("userId") int UserId);

    int deleteTeamTaskByTaskId(@Param("taskId") int TaskId);

    int deleteTeamTaskByTeamId(@Param("teamId") int TeamId);

    int deleteTeamTaskByTaskIds(@Param("list") List<Integer> TaskIds);
}