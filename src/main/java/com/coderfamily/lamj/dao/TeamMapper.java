package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.TeamEntity;
import com.coderfamily.lamj.model.TeamUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamMapper {
    List<TeamEntity> selectTeamListByName(@Param("name") String Name);

    List<TeamEntity> selectTeamListByCondition(TeamEntity entity);

    TeamEntity selectTeamById(@Param("id") int Id);

    boolean existsTeamByName(@Param("name") String Name, @Param("companyId") int CompanyId);

    int insert(TeamEntity entity);

    int insertTeamUser(@Param("list") List<TeamUserEntity> list);

    int update(TeamEntity entity);

    int delete(@Param("id") int Id);

    int deleteUserRelat(@Param("id") int Id);
}