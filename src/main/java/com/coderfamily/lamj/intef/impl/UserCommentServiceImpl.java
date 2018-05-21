package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.UserCommentMapper;
import com.coderfamily.lamj.intef.*;
import com.coderfamily.lamj.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/5/2 14:20
 */
@Service
@Transactional
public class UserCommentServiceImpl implements IUserCommentService {
    @Autowired
    private UserCommentMapper userCommentMapper;
    @Autowired
    private IIntegralRecordService integralRecordService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IDictionaryService dictionaryService;

    @Override
    public PageInfo<UserCommentEntity> getUserCommentList(int CompanyId, int TaskId, Integer UserId, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<UserCommentEntity> mData = userCommentMapper.selectUserCommentList(CompanyId, TaskId, UserId);
        return new PageInfo<>(mData);
    }

    @Override
    public Result insert(UserCommentEntity params) {
        if (userCommentMapper.insert(params) > 0) {
            insertIntegral(params);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(UserCommentEntity params) {
        if (userCommentMapper.update(params) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        if (userCommentMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    /**
     * 新增积分
     * @param uce
     */
    private void insertIntegral(UserCommentEntity uce){
        UserEntity userEntity = userService.selectUserById(uce.getUserId());
        TaskEntity taskEntity = taskService.getTaskById(uce.getTaskId());
        DictionaryEntity dictionaryEntity = dictionaryService.DictInfo("Source","0001");
        IntegralRecordEntity entity = new IntegralRecordEntity();
        entity.setCompanyId(uce.getCompanyId());
        entity.setUserId(uce.getUserId());
        entity.setUserName(userEntity.getNickName());
        entity.setIntegral(taskEntity.getIntegral());
        entity.setSourceId(dictionaryEntity.getId());
        entity.setSourceCode(dictionaryEntity.getCode());
        entity.setSourceName(dictionaryEntity.getLabel());
        entity.setSourceTaskId(uce.getTaskId());
        entity.setCreateTime(TimeUtils.getCurrentDate());
        integralRecordService.insert(entity);
    }
}
