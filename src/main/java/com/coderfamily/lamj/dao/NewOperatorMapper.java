package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.NewOperatorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewOperatorMapper {

    List<NewOperatorEntity> selectNewOperatorByNewId(@Param("newId") int NewId);

    int insertByList(@Param("list") List<NewOperatorEntity> mList);

    int deleteByNewId(@Param("newId") int NewId);

}