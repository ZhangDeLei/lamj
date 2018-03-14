package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.dao.NewAuthMapper;
import com.coderfamily.lamj.domain.CompanyNewInfo;
import com.coderfamily.lamj.domain.NewAuthInfo;
import com.coderfamily.lamj.model.NewAuthEntity;
import com.coderfamily.lamj.model.NewOperatorEntity;
import com.coderfamily.lamj.intef.INewAuthService;
import com.coderfamily.lamj.intef.INewOperatorService;
import com.coderfamily.lamj.intef.IUserNewAuthService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class NewAuthServiceImpl implements INewAuthService {
    @Autowired
    private NewAuthMapper newAuthMapper;
    @Autowired
    private INewOperatorService operatorService;
    @Autowired
    private IUserNewAuthService userNewAuthService;

    @Override
    public PageInfo<NewAuthInfo> select(String Name, Boolean Status, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        NewAuthInfo info = new NewAuthInfo();
        info.setName(Name);
        info.setStatus(Status);
        return new PageInfo<>(newAuthMapper.select(info));
    }

    @Override
    public PageInfo<CompanyNewInfo> select(int CompanyId, Boolean Status, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        return new PageInfo<>(newAuthMapper.selectByCompany(CompanyId, Status));
    }

    @Override
    public List<CompanyNewInfo> select(int CompanyId) {
        return newAuthMapper.selectByCompany(CompanyId, true);
    }

    @Override
    public List<NewAuthInfo> selectAll() {
        NewAuthInfo entity = new NewAuthInfo();
        entity.setStatus(true);
        return newAuthMapper.select(entity);
    }

    @Override
    public Result insert(NewAuthInfo info) {
        if (newAuthMapper.existsNewAuthByName(info.getName())) {
            return Result.error("当前客户端已存在");
        } else if (newAuthMapper.insert(info) > 0) {
            insertNewAuthOperator(info.getId(), info.getOprs());
            return Result.success();
        } else {
            return Result.success();
        }
    }

    @Override
    public Result update(NewAuthInfo info) {
        NewAuthInfo newAuthInfo = newAuthMapper.selectById(info.getId());
        if (NullUtil.isNull(newAuthInfo)) {
            return Result.error("当前客户端不存在");
        } else if (!info.getName().equals(newAuthInfo.getName()) && newAuthMapper.existsNewAuthByName(info.getName())) {
            return Result.error("当前客户端已存在");
        } else if (newAuthMapper.update(info) > 0) {
            insertNewAuthOperator(info.getId(), info.getOprs());
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        NewAuthInfo newAuthInfo = newAuthMapper.selectById(Id);
        if (NullUtil.isNull(newAuthInfo)) {
            return Result.error("当前客户端不存在");
        } else {
            newAuthMapper.delete(Id);
            operatorService.deleteByNewId(Id);
            userNewAuthService.deleteByNewId(Id);
            return Result.success();
        }
    }

    /**
     * 新增新闻可操作类型关联关系
     *
     * @param NewId
     * @param mList
     */
    private void insertNewAuthOperator(int NewId, List<NewOperatorEntity> mList) {
        if (mList != null && mList.size() > 0) {
            operatorService.deleteByNewId(NewId);
            mList.forEach(t -> {
                t.setNewId(NewId);
            });
            operatorService.insertByList(mList);
        }
    }
}
