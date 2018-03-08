package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.dao.CaliberMapper;
import com.coderfamily.lamj.model.CaliberEntity;
import com.coderfamily.lamj.service.ICaliberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:58
 */
@Service
@Transactional
public class CaliberServiceImpl implements ICaliberService {
    @Autowired
    private CaliberMapper caliberMapper;

    @Override
    public PageInfo<CaliberEntity> select(String Name, Boolean Status, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        CaliberEntity entity = new CaliberEntity();
        entity.setName(Name);
        entity.setStatus(Status);
        return new PageInfo<>(caliberMapper.select(entity));
    }

    @Override
    public Result insert(CaliberEntity entity) {
        if (caliberMapper.existsCaliberByName(entity.getName())) {
            return Result.error("该口径已存在");
        } else if (caliberMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(CaliberEntity entity) {
        CaliberEntity caliberEntity = caliberMapper.selectById(entity.getId());
        if (NullUtil.isNull(caliberEntity)) {
            return Result.error("该口径不存在");
        } else if (!entity.getName().equals(caliberEntity.getName()) && caliberMapper.existsCaliberByName(entity.getName())) {
            return Result.error("该口径已存在");
        } else if (caliberMapper.update(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        CaliberEntity caliberEntity = caliberMapper.selectById(Id);
        if (NullUtil.isNull(caliberEntity)) {
            return Result.error("该口径不存在");
        } else if (caliberMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }
}
