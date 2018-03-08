package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.TaskInfo;
import com.coderfamily.lamj.model.TaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {

    List<TaskEntity> select(TaskEntity entity);

    TaskInfo selectById(@Param("id") int Id);

    int insert(TaskEntity entity);

    int update(TaskEntity entity);

    int delete(@Param("id") int Id);

    int deleteByIds(@Param("list") List<Integer> Ids);
}