package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.SubmissionAuditMapper;
import com.coderfamily.lamj.intef.*;
import com.coderfamily.lamj.model.*;
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
    @Autowired
    private IUserService userService;

    @Override
    public Result adopt(int SubmissionId, int UserId, String UserName, String Comment) {
        DictionaryEntity dict = dictionaryService.DictInfo("Process", "0004");//已通过
        return insertHis(dict, SubmissionId, UserId, UserName, Comment, true);
    }

    @Override
    public Result back(int SubmissionId, int UserId, String UserName, String Comment) {
        DictionaryEntity dict = dictionaryService.DictInfo("Process", "0003");//已退回
        return insertHis(dict, SubmissionId, UserId, UserName, Comment, false);
    }

    @Override
    public int insert(SubmissionAuditEntity entity) {
        entity.setOrderNum(getMaxNum(entity.getSubmissionId()));
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
            submissionService.updateStatus(SubmissionId, dict.getId(), dict.getCode(), dict.getLabel(), Status);
            //如果审核通过需要新增积分
            if (Status) {
                insertIntegral(UserId, SubmissionId);
            }
            return Result.success();
        } else {
            return Result.error();
        }
    }

    /**
     * 网评投稿新增积分
     */
    private void insertIntegral(int UserId, int SubmissionId) {
        DictionaryEntity dict = dictionaryService.DictInfo("Source", "0002");
        SubmissionEntity submissionEntity = submissionService.getSubmissionById(SubmissionId);
        UserEntity userEntity = userService.selectUserById(UserId);
        IntegralRecordEntity entity = new IntegralRecordEntity();
        entity.setUserId(UserId);
        entity.setCompanyId(submissionEntity.getCompanyId());
        entity.setUserName(userEntity.getNickName());
        entity.setIntegral(5);
        entity.setSourceId(dict.getId());
        entity.setSourceCode(dict.getCode());
        entity.setSourceName(dict.getLabel());
        entity.setSourceSubmissionId(SubmissionId);
        entity.setCreateTime(TimeUtils.getCurrentDate());
        integralRecordService.insert(entity);
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
