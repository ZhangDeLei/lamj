package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.dao.UserCommentMapper;
import com.coderfamily.lamj.intef.IUserCommentService;
import com.coderfamily.lamj.model.UserCommentEntity;
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

    @Override
    public PageInfo<UserCommentEntity> getUserCommentList(int CompanyId, int TaskId, Integer UserId, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<UserCommentEntity> mData = userCommentMapper.selectUserCommentList(CompanyId, TaskId, UserId);
        return new PageInfo<>(mData);
    }

    @Override
    public Result insert(UserCommentEntity params) {
        if (userCommentMapper.insert(params) > 0) {
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
}
