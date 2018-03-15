package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.SubmissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubmissionMapper {
    List<SubmissionEntity> select(@Param("companyId") int ComapnyId, @Param("themeId") Integer ThemeId, @Param("title") String Title, @Param("userId") Integer UserId, @Param("processId") Integer ProcessId);

    SubmissionEntity selectById(@Param("id") int Id);

    int insert(SubmissionEntity entity);

    int update(SubmissionEntity entity);

    int delete(@Param("id") int Id);

    int deleteByCompanyId(@Param("companyId") int CompanyId);

    int deleteByUserId(@Param("userId") int UserId);
}