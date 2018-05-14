package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.SubmissionMapper;
import com.coderfamily.lamj.domain.SubmissionInfo;
import com.coderfamily.lamj.intef.IDictionaryService;
import com.coderfamily.lamj.intef.IFileService;
import com.coderfamily.lamj.intef.ISubmissionAuditService;
import com.coderfamily.lamj.intef.ISubmissionService;
import com.coderfamily.lamj.model.DictionaryEntity;
import com.coderfamily.lamj.model.SubmissionAuditEntity;
import com.coderfamily.lamj.model.SubmissionEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/14 15:31
 */
@Service
@Transactional
public class SubmissionServiceImpl implements ISubmissionService {
    @Autowired
    private SubmissionMapper submissionMapper;
    @Autowired
    private ISubmissionAuditService submissionAuditService;
    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private IFileService fileService;

    @Override
    public PageInfo<SubmissionInfo> getSubmissionList(int ComapnyId,
                                                      String ThemeName,
                                                      String Title,
                                                      Integer UserId,
                                                      Integer ProcessId,
                                                      Integer LevelId,
                                                      Boolean Status,
                                                      int PageSize,
                                                      int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<SubmissionInfo> mData = submissionMapper.select(ComapnyId, ThemeName, Title, UserId, ProcessId, LevelId, Status);
        return new PageInfo<>(mData);
    }

    @Override
    public SubmissionInfo getSubmissionById(int Id) {
        return submissionMapper.selectById(Id);
    }

    @Override
    public Result insert(SubmissionEntity entity) {
        DictionaryEntity dict = dictionaryService.DictInfo("Process", "0001");
        entity.setCreateTime(TimeUtils.getCurrentDate());
        entity.setProcessId(dict.getId());
        entity.setProcessCode(dict.getCode());
        entity.setProcessName(dict.getLabel());
        if (submissionMapper.insert(entity) > 0) {
            insertSubmissionAudit(entity, dict);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(SubmissionEntity entity) {
        SubmissionEntity submissionEntity = getSubmissionById(entity.getId());
        DictionaryEntity dict = dictionaryService.DictInfo("Process", "0005");
        entity.setProcessId(dict.getId());
        entity.setProcessCode(dict.getCode());
        entity.setProcessName(dict.getLabel());
        if (NullUtil.isNull(submissionEntity)) {
            return Result.error("该网评投稿不存在");
        } else if (submissionMapper.update(entity) > 0) {
            insertSubmissionAudit(entity, dict);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int updateStatus(int Id, int ProcessId, String ProcessCode, String ProcessName, boolean Status) {
        SubmissionEntity entity = new SubmissionEntity();
        entity.setId(Id);
        entity.setStatus(Status);
        entity.setProcessName(ProcessName);
        entity.setProcessCode(ProcessCode);
        entity.setProcessId(ProcessId);
        return submissionMapper.update(entity);
    }

    @Override
    public Result delete(int Id) {
        SubmissionEntity submissionEntity = getSubmissionById(Id);
        if (NullUtil.isNull(submissionEntity)) {
            return Result.error("该网评投稿不存在");
        } else if (submissionMapper.delete(Id) > 0) {
            submissionAuditService.deleteBySubmissionId(Id);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int deleteByCompanyId(int CompanyId) {
        return submissionMapper.deleteByCompanyId(CompanyId);
    }

    @Override
    public int deleteByUserId(int UserId) {
        return submissionMapper.deleteByUserId(UserId);
    }

    /**
     * 添加一条投稿审核记录
     */
    private void insertSubmissionAudit(SubmissionEntity entity, DictionaryEntity dict) {
        SubmissionAuditEntity auditEntity = new SubmissionAuditEntity();
        auditEntity.setSubmissionId(entity.getId());
        auditEntity.setOperationTime(TimeUtils.getCurrentDate());
        auditEntity.setOperationUserId(entity.getUserId());
        auditEntity.setOperationUserName(entity.getUserName());
        auditEntity.setProcessId(dict.getId());
        auditEntity.setProcessName(dict.getLabel());
        auditEntity.setProcessCode(dict.getCode());
        submissionAuditService.insert(auditEntity);
    }

}
