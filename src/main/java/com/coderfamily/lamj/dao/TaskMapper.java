package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.TaskDetail;
import com.coderfamily.lamj.domain.TaskInfo;
import com.coderfamily.lamj.model.TaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {

    List<TaskEntity> select(@Param("companyId") Integer CompanyId,
                            @Param("title") String Title,
                            @Param("stageId") Integer StageId,
                            @Param("newId") Integer NewId,
                            @Param("begDate") String BegDate,
                            @Param("endDate") String EndDate);

    List<TaskDetail> selectByTeams(@Param("userId") Integer UserId,
                                   @Param("title") String Title,
                                   @Param("stageId") Integer StageId,
                                   @Param("newId") Integer NewId,
                                   @Param("begDate") String BegDate,
                                   @Param("endDate") String EndDate);

    List<TaskDetail> selectByNew(@Param("companyId") int CompanyId);

    TaskInfo selectById(@Param("id") int Id);

    int insert(TaskEntity entity);

    int update(TaskEntity entity);

    int delete(@Param("id") int Id);

    int deleteByIds(@Param("list") List<Integer> Ids);
}