package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.ArticleLevelEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleLevelMapper {

    List<ArticleLevelEntity> select(ArticleLevelEntity entity);

    ArticleLevelEntity selectById(@Param("id") int id);

    boolean existsByNameAndCompanyId(@Param("name") String Name,@Param("companyId") int CompanyId);

    int delete(@Param("id") int id);

    int update(ArticleLevelEntity entity);

    int insert(ArticleLevelEntity entity);
}