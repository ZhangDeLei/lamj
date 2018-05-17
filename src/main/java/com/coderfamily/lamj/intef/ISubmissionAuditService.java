package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.SubmissionAuditEntity;

/**
 * @author ZhangDL
 * @date 2018/3/14 16:15
 */
public interface ISubmissionAuditService {

    /**
     * 审核通过
     * @param SubmissionId
     * @param UserId
     * @param UserName
     * @param Comment
     * @return
     */
    Result adopt(int SubmissionId,int UserId,String UserName,String Comment);

    /**
     * 退回
     *
     * @param SubmissionId
     * @param UserId
     * @param UserName
     * @param Comment
     * @return
     */
    Result back(int SubmissionId,int UserId,String UserName,String Comment);

    /**
     * 新增网评投稿审核流程
     *
     * @param entity
     * @return
     */
    int insert(SubmissionAuditEntity entity);

    /**
     * 根据投稿ID删除审核记录
     * @param SubmissionId
     * @return
     */
    int deleteBySubmissionId(int SubmissionId);
}
