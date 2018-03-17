package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.IntegralRecordMapper;
import com.coderfamily.lamj.intef.IIntegralRecordService;
import com.coderfamily.lamj.model.DictionaryEntity;
import com.coderfamily.lamj.model.IntegralRecordEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/15 08:35
 */
@Service
@Transactional
public class IntegralRecordServiceImpl implements IIntegralRecordService {
    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public int insert(IntegralRecordEntity entity) {
        entity.setCreateTime(TimeUtils.getCurrentDate());
        return integralRecordMapper.insert(entity);
    }

    @Override
    public Result reduce(IntegralRecordEntity entity) {
        if (integralRecordMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public PageInfo<IntegralRecordEntity> getIntegralRecordByUserId(int UserId, Integer SourceId, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        IntegralRecordEntity entity = new IntegralRecordEntity();
        entity.setUserId(UserId);
        entity.setSourceId(SourceId);
        return new PageInfo<>(integralRecordMapper.select(entity));
    }

    @Override
    public PageInfo<IntegralRecordEntity> getIntegralRecordByCompany(int CompanyId, Integer UserId, Integer SourceId, Integer SourceUserId, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        IntegralRecordEntity entity = new IntegralRecordEntity();
        entity.setCompanyId(CompanyId);
        entity.setUserId(UserId);
        entity.setSourceId(SourceId);
        entity.setSourceUserId(SourceUserId);
        return new PageInfo<>(integralRecordMapper.select(entity));
    }

    @Override
    public List<IntegralRecordEntity> getIntegralRecordByTaskId(int TaskId) {
        IntegralRecordEntity entity = new IntegralRecordEntity();
        entity.setSourceTaskId(TaskId);
        return integralRecordMapper.select(entity);
    }

    @Override
    public List<IntegralRecordEntity> getIntegralRecordByTaskIdAndUserId(int TaskId, int UserId) {
        IntegralRecordEntity entity = new IntegralRecordEntity();
        entity.setSourceTaskId(TaskId);
        entity.setUserId(UserId);
        return integralRecordMapper.select(entity);
    }

    @Override
    public List<IntegralRecordEntity> getIntegralRecordBySubmissionId(int SubmissionId) {
        IntegralRecordEntity entity = new IntegralRecordEntity();
        entity.setSourceSubmissionId(SubmissionId);
        return integralRecordMapper.select(entity);
    }
}
