package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.NewAuthInfo;
import com.coderfamily.lamj.model.NewAuthEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewAuthMapper {
    List<NewAuthInfo> select(NewAuthEntity entity);

    NewAuthInfo selectById(@Param("id") int Id);

    boolean existsNewAuthByName(@Param("name") String Name);

    int insert(NewAuthEntity entity);

    int update(NewAuthEntity entity);

    int delete(@Param("id") int Id);
}