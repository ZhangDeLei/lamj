package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.SubmissionInfo;
import com.coderfamily.lamj.model.SubmissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubmissionMapper {
    List<SubmissionInfo> select(@Param("companyId") int ComapnyId,
                                @Param("themeName") String ThemeName,
                                @Param("title") String Title,
                                @Param("userId") Integer UserId,
                                @Param("processId") Integer ProcessId,
                                @Param("status") Boolean Status);

    SubmissionInfo selectById(@Param("id") int Id);

    int insert(SubmissionEntity entity);

    int update(SubmissionEntity entity);

    int delete(@Param("id") int Id);

    int deleteByCompanyId(@Param("companyId") int CompanyId);

    int deleteByUserId(@Param("userId") int UserId);
}