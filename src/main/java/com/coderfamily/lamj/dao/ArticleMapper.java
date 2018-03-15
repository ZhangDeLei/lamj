package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.ArticleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    List<ArticleEntity> select(@Param("companyId") int CompanyId,@Param("title") String Title,@Param("userId") Integer UserId,@Param("typeId") Integer TypeId);

    ArticleEntity selectById(@Param("id") int Id);

    int insert(ArticleEntity entity);

    int update(ArticleEntity entity);

    int delete(@Param("id") int Id);

    int deleteByCompanyId(@Param("companyId") int CompanyId);

    int deleteByUserId(@Param("userId") int UserId);
}