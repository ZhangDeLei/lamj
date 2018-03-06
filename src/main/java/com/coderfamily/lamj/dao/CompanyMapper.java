package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.CompanyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {

    List<CompanyEntity> selectCompanyList(@Param("name") String Name, @Param("begDate") String BegDate, @Param("endDate") String EndDate);

    CompanyEntity selectCompanyById(@Param("id") int Id);

    List<CompanyEntity> selectCompanyListByCondition(CompanyEntity entity);

    boolean selectCompanyIsExpired(@Param("begDate") String BegDate, @Param("endDate") String EndDate);

    boolean isExpiredCompanyByUserId(@Param("userId") int UserId);

    boolean existsCompanyByName(@Param("name") String Name);

    int insert(CompanyEntity entity);

    int update(CompanyEntity entity);

    int delete(@Param("id") int Id);
}