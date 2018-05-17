package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.IntegralList;
import com.coderfamily.lamj.model.IntegralRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralRecordMapper {

    List<IntegralRecordEntity> select(IntegralRecordEntity entity);

    int selectTotalIntegral(@Param("userId") int UserId);

    List<IntegralList> selectPersonTotal(IntegralRecordEntity entity);

    int insert(IntegralRecordEntity entity);
}