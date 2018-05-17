package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.dao.UserNewAuthMapper;
import com.coderfamily.lamj.model.UserNewAuthEntity;
import com.coderfamily.lamj.intef.IUserNewAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:59
 */
@Service
@Transactional
public class UserNewAuthServiceImpl implements IUserNewAuthService {
    @Autowired
    private UserNewAuthMapper userNewAuthMapper;

    @Override
    public List<UserNewAuthEntity> getUserNewAuthList(int CompanyId, int UserId) {
        return userNewAuthMapper.selectUserNewAuthList(CompanyId, UserId);
    }

    @Override
    public Result insert(UserNewAuthEntity entity) {
        if (userNewAuthMapper.existsUserNewAuthByUserIdAndNewId(entity.getUserId(), entity.getNewId())) {
            return Result.error("当前已授权");
        } else if (userNewAuthMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(UserNewAuthEntity entity) {
        UserNewAuthEntity userNewAuthEntity = userNewAuthMapper.selectById(entity.getId());
        if (NullUtil.isNull(userNewAuthEntity)) {
            return Result.error("当前授权信息已不存在");
        } else if (userNewAuthMapper.update(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int deleteByUserId(int UserId) {
        return userNewAuthMapper.deleteByUserId(UserId);
    }

    @Override
    public int deleteByNewId(int NewId) {
        return userNewAuthMapper.deleteByNewid(NewId);
    }

    @Override
    public int delete(int Id) {
        return userNewAuthMapper.delete(Id);
    }
}
