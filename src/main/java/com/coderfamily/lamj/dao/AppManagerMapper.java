package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.AppManagerEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppManagerMapper {

    int update(AppManagerEntity entity);

    int insert(AppManagerEntity entity);

    int delete(@Param("id") int Id);

    List<AppManagerEntity> select(@Param("name") String name, @Param("typeId") Integer TypeId);

    AppManagerEntity selectByNew(@Param("typeId") int TypeId);

    AppManagerEntity selectById(@Param("id") int Id);
}