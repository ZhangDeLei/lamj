package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.AppManagerMapper;
import com.coderfamily.lamj.intef.IAppManagerService;
import com.coderfamily.lamj.intef.IFileService;
import com.coderfamily.lamj.model.AppManagerEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/21 10:06
 */
@Service
@Transactional
public class AppManagerServiceImpl implements IAppManagerService {
    @Autowired
    private AppManagerMapper appManagerMapper;
    @Autowired
    private IFileService fileService;

    @Override
    public PageInfo<AppManagerEntity> getAppList(String Name, Integer TypeId, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<AppManagerEntity> mData = appManagerMapper.select(Name, TypeId);
        return new PageInfo<>(mData);
    }

    @Override
    public AppManagerEntity getAppByNew(int TypeId) {
        return appManagerMapper.selectByNew(TypeId);
    }

    @Override
    public Result insert(AppManagerEntity entity) {
        entity.setUploadTime(TimeUtils.getCurrentDate());
        if (appManagerMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(AppManagerEntity entity) {
        AppManagerEntity appManagerEntity = appManagerMapper.selectById(entity.getId());
        if (NullUtil.isNull(appManagerEntity)) {
            return Result.error("记录不存在");
        } else if (appManagerMapper.update(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        if (appManagerMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }
}
