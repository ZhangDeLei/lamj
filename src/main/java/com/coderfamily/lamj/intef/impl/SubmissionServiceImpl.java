package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.dao.SubmissionMapper;
import com.coderfamily.lamj.intef.ISubmissionService;
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

    @Override
    public PageInfo<SubmissionEntity> getSubmissionList(int ComapnyId, Integer ThemeId, String Title, Integer UserId, Integer ProcessId, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<SubmissionEntity> mData = submissionMapper.select(ComapnyId, ThemeId, Title, UserId, ProcessId);
        return new PageInfo<>(mData);
    }

    @Override
    public SubmissionEntity getSubmissionById(int Id) {
        return submissionMapper.selectById(Id);
    }

    @Override
    public Result insert(SubmissionEntity entity) {
        if (submissionMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(SubmissionEntity entity) {
        SubmissionEntity submissionEntity = getSubmissionById(entity.getId());
        if (NullUtil.isNull(submissionEntity)) {
            return Result.error("该网评投稿不存在");
        } else if (submissionMapper.update(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int updateStatus(int Id, boolean Status) {
        SubmissionEntity entity = new SubmissionEntity();
        entity.setId(Id);
        entity.setStatus(Status);
        return submissionMapper.update(entity);
    }

    @Override
    public Result delete(int Id) {
        SubmissionEntity submissionEntity = getSubmissionById(Id);
        if (NullUtil.isNull(submissionEntity)) {
            return Result.error("该网评投稿不存在");
        } else if (submissionMapper.delete(Id) > 0) {
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
}
