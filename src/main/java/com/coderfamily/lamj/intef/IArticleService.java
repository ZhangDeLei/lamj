package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.ArticleEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/14 15:30
 */
public interface IArticleService {

    /**
     * 分页获取网评文章列表（企业级）
     *
     * @param CompanyId
     * @param Title
     * @param UserId
     * @param TypeId
     * @param LevelId
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<ArticleEntity> getArticleList(int CompanyId, String Title, Integer UserId, Integer TypeId, Integer LevelId, int PageSize, int CurPage);

    /**
     * 分页获取网评文章列表（app）
     *
     * @param CompanyId
     * @param Title
     * @param LevelId
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<ArticleEntity> getArticleListForApp(int CompanyId, String Title, int LevelId, int PageSize, int CurPage);

    /**
     * 获取网站合集列表（app）
     *
     * @param CompanyId
     * @return
     */
    List<ArticleEntity> getArticleTypeForApp(int CompanyId);

    /**
     * 根据ID查看网评文章详细信息
     *
     * @param Id
     * @return
     */
    ArticleEntity getArticleById(int Id);

    /**
     * 新增网评文章
     *
     * @param entity
     * @return
     */
    Result insert(ArticleEntity entity);

    /**
     * 更新网评文章
     *
     * @param entity
     * @return
     */
    Result update(ArticleEntity entity);

    /**
     * 根据ID删除网评文章
     *
     * @param Id
     * @return
     */
    Result delete(int Id);

    /**
     * 根据企业ID删除网评文章
     *
     * @param CompanyId
     * @return
     */
    int deleteByCompanyId(int CompanyId);

    /**
     * 根据用户ID删除网评文章
     *
     * @param UserId
     * @return
     */
    int deleteByUserId(int UserId);
}
