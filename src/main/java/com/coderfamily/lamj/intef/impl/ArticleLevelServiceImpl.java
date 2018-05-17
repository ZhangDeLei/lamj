package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.ArticleLevelMapper;
import com.coderfamily.lamj.intef.IArticleLevelService;
import com.coderfamily.lamj.model.ArticleLevelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/4/26 14:43
 */
@Service
@Transactional
public class ArticleLevelServiceImpl implements IArticleLevelService {

    @Autowired
    private ArticleLevelMapper articleLevelMapper;

    @Override
    public List<ArticleLevelEntity> getArticleLevelList(int CompanyId, String Name, Boolean Status) {
        ArticleLevelEntity param = new ArticleLevelEntity();
        param.setCompanyId(CompanyId);
        param.setName(Name);
        param.setStatus(Status);
        return articleLevelMapper.select(param);
    }

    @Override
    public ArticleLevelEntity getArticleLevelById(int Id) {
        return articleLevelMapper.selectById(Id);
    }

    @Override
    public Result insert(ArticleLevelEntity entity) {
        entity.setCreateTime(TimeUtils.getCurrentDate());
        entity.setLastUpdateTime(TimeUtils.getCurrentDate());
        boolean isExists = articleLevelMapper.existsByNameAndCompanyId(entity.getName(), entity.getCompanyId());
        if (isExists) {
            return Result.error("当前级别已存在");
        } else if (articleLevelMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(ArticleLevelEntity entity) {
        ArticleLevelEntity levelEntity = articleLevelMapper.selectById(entity.getId());
        entity.setLastUpdateTime(TimeUtils.getCurrentDate());
        if (levelEntity == null) {
            return Result.error("当前级别不存在");
        } else if (articleLevelMapper.existsByNameAndCompanyId(entity.getName(), entity.getCompanyId()) && !entity.getName().equals(levelEntity.getName())) {
            return Result.error("当前级别名称已存在");
        } else if (articleLevelMapper.update(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        ArticleLevelEntity levelEntity = articleLevelMapper.selectById(Id);
        if (levelEntity == null) {
            return Result.success();
        } else if (articleLevelMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }
}
