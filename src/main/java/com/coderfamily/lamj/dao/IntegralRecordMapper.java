package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.IntegralRecordEntity;

import java.util.List;

public interface IntegralRecordMapper {

    List<IntegralRecordEntity> select(IntegralRecordEntity entity);

    int insert(IntegralRecordEntity entity);
}