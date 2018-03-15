package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.IntegralRecordEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/15 08:35
 */
public interface IIntegralRecordService {
    /**
     * 新增积分记录
     *
     * @param entity
     * @return
     */
    int insert(IntegralRecordEntity entity);

    /**
     * 核减积分
     *
     * @param entity
     * @return
     */
    Result reduce(IntegralRecordEntity entity);

    /**
     * 获取用户的积分记录列表
     *
     * @return
     */
    PageInfo<IntegralRecordEntity> getIntegralRecordByUserId(int UserId, Integer SourceId, int PageSize, int CurPage);

    /**
     * 获取企业用户所有的积分记录
     *
     * @return
     */
    PageInfo<IntegralRecordEntity> getIntegralRecordByCompany(int CompanyId, Integer UserId, Integer SourceId, Integer SourceUserId, int PageSize, int CurPage);

    /**
     * 根据任务ID获取积分记录
     *
     * @param TaskId
     * @return
     */
    List<IntegralRecordEntity> getIntegralRecordByTaskId(int TaskId);

    /**
     * 根据任务ID和用户ID查询积分记录
     *
     * @param TaskId
     * @param UserId
     * @return
     */
    List<IntegralRecordEntity> getIntegralRecordByTaskIdAndUserId(int TaskId, int UserId);

    /**
     * 根据投稿ID查询积分记录
     *
     * @param SubmissionId
     * @return
     */
    List<IntegralRecordEntity> getIntegralRecordBySubmissionId(int SubmissionId);
}
