package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.ArticleMapper;
import com.coderfamily.lamj.intef.IArticleService;
import com.coderfamily.lamj.model.ArticleEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/14 15:30
 */
@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageInfo<ArticleEntity> getArticleList(int CompanyId, String Title, Integer UserId, Integer TypeId, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<ArticleEntity> mData = articleMapper.select(CompanyId, Title, UserId, TypeId);
        return new PageInfo<>(mData);
    }

    @Override
    public ArticleEntity getArticleById(int Id) {
        return articleMapper.selectById(Id);
    }

    @Override
    public Result insert(ArticleEntity entity) {
        entity.setCreateTime(TimeUtils.getCurrentDate());
        if (articleMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(ArticleEntity entity) {
        ArticleEntity articleEntity = getArticleById(entity.getId());
        if (NullUtil.isNull(articleEntity)) {
            return Result.error("该网评文章不存在");
        } else if (articleMapper.update(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        ArticleEntity articleEntity = getArticleById(Id);
        if (NullUtil.isNull(articleEntity)) {
            return Result.error("该网评文章不存在");
        } else if (articleMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int deleteByCompanyId(int CompanyId) {
        return articleMapper.deleteByCompanyId(CompanyId);
    }

    @Override
    public int deleteByUserId(int UserId) {
        return articleMapper.deleteByUserId(UserId);
    }
}
