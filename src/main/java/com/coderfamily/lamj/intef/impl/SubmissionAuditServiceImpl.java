package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.SubmissionAuditMapper;
import com.coderfamily.lamj.intef.IDictionaryService;
import com.coderfamily.lamj.intef.IIntegralRecordService;
import com.coderfamily.lamj.intef.ISubmissionAuditService;
import com.coderfamily.lamj.intef.ISubmissionService;
import com.coderfamily.lamj.model.DictionaryEntity;
import com.coderfamily.lamj.model.SubmissionAuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangDL
 * @date 2018/3/14 16:15
 */
@Service
@Transactional
public class SubmissionAuditServiceImpl implements ISubmissionAuditService {
    @Autowired
    private SubmissionAuditMapper submissionAuditMapper;
    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private ISubmissionService submissionService;
    @Autowired
    private IIntegralRecordService integralRecordService;

    @Override
    public Result adopt(int SubmissionId, int UserId, String UserName, String Comment) {
        DictionaryEntity dict = dictionaryService.DictInfo("", "");//已通过
        return insertHis(dict, SubmissionId, UserId, UserName, Comment, true);
    }

    @Override
    public Result back(int SubmissionId, int UserId, String UserName, String Comment) {
        DictionaryEntity dict = dictionaryService.DictInfo("", "");//已退回
        return insertHis(dict, SubmissionId, UserId, UserName, Comment, false);
    }

    @Override
    public int insert(SubmissionAuditEntity entity) {
        return submissionAuditMapper.insert(entity);
    }

    @Override
    public int deleteBySubmissionId(int SubmissionId) {
        return submissionAuditMapper.deleteBySubmissionId(SubmissionId);
    }

    /**
     * 新增审核记录
     *
     * @param dict
     * @param SubmissionId
     * @param UserId
     * @param UserName
     * @param Comment
     * @return
     */
    private Result insertHis(DictionaryEntity dict, int SubmissionId, int UserId, String UserName, String Comment, boolean Status) {
        SubmissionAuditEntity entity = new SubmissionAuditEntity();
        entity.setSubmissionId(SubmissionId);
        entity.setOperationUserId(UserId);
        entity.setOperationUserName(UserName);
        entity.setComment(Comment);
        entity.setOperationTime(TimeUtils.getCurrentDate());
        entity.setOrderNum(getMaxNum(SubmissionId));
        entity.setProcessId(dict.getId());
        entity.setProcessCode(dict.getCode());
        entity.setProcessName(dict.getLabel());
        if (submissionAuditMapper.insert(entity) > 0) {
            submissionService.updateStatus(SubmissionId, Status);
            //如果审核通过需要新增积分
            if (Status) {

            }
            return Result.success();
        } else {
            return Result.error();
        }
    }

    /**
     * 获取最大的排序
     *
     * @param SubmissionId
     * @return
     */
    private int getMaxNum(int SubmissionId) {
        int num = submissionAuditMapper.selectMaxOrderNumBySubmissionId(SubmissionId);
        return num + 1;
    }

}
