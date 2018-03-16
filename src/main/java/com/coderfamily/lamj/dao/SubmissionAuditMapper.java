package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.SubmissionAuditEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubmissionAuditMapper {

    int insert(SubmissionAuditEntity entity);

    List<SubmissionAuditEntity> selectBySubmissionId(@Param("id") int SubmissionId);

    SubmissionAuditEntity selectByNew(@Param("id") int SubmissionId);

    Integer selectMaxOrderNumBySubmissionId(@Param("id") int SubmissionId);

    int deleteBySubmissionId(@Param("submissionId") int SubmissionId);

}