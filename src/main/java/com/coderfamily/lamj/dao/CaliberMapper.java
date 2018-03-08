package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.CaliberEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaliberMapper {

    List<CaliberEntity> select(CaliberEntity entity);

    boolean existsCaliberByName(@Param("name") String Name);

    CaliberEntity selectById(@Param("id") int Id);

    int insert(CaliberEntity entity);

    int update(CaliberEntity entity);

    int delete(@Param("id") int Id);
}