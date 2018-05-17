package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.ArticleLevelEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/4/26 14:41
 */
public interface IArticleLevelService {

    /**
     * 获取文章级别列表
     *
     * @param CompanyId
     * @param Name
     * @param Status
     * @return
     */
    List<ArticleLevelEntity> getArticleLevelList(int CompanyId, String Name, Boolean Status);

    /**
     * 根据ID获取文章级别详细信息
     *
     * @param Id
     * @return
     */
    ArticleLevelEntity getArticleLevelById(int Id);

    /**
     * 新增文章级别
     *
     * @param entity
     * @return
     */
    Result insert(ArticleLevelEntity entity);

    /**
     * 更新文章级别
     *
     * @param entity
     * @return
     */
    Result update(ArticleLevelEntity entity);

    /**
     * 删除文章级别
     *
     * @param Id
     * @return
     */
    Result delete(int Id);

}
