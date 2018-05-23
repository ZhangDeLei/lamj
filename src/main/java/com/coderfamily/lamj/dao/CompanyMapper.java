package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.CompanyEntity;
import com.coderfamily.lamj.model.CompanyUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {

    List<CompanyEntity> selectCompanyList(@Param("name") String Name, @Param("begDate") String BegDate, @Param("endDate") String EndDate);

    CompanyEntity selectCompanyById(@Param("id") int Id);

    List<CompanyEntity> selectCompanyListByCondition(CompanyEntity entity);

    List<CompanyEntity> selectComapnyByExpired(@Param("expiredDate") String ExpiredDate);

    int selectCount();

    boolean selectCompanyIsExpired(@Param("begDate") String BegDate, @Param("endDate") String EndDate);

    boolean isExpiredCompanyByUserId(@Param("userId") int UserId);

    boolean existsCompanyByName(@Param("name") String Name);

    boolean existsCompanyByMaxNum(@Param("id") int Id);

    int insert(CompanyEntity entity);

    int insertCompanyUser(CompanyUserEntity entity);

    int insertCompanyNew(@Param("id") int Id, @Param("list") List<Integer> Ids);

    int update(CompanyEntity entity);

    int delete(@Param("id") int Id);

    int deleteCompanyUser(@Param("companyId") int CompanyId, @Param("userId") int UserId);

    int deleteAllCompanyUser(@Param("companyId") int CompanyId);

    int deleteCompanyUserByUserId(@Param("userId") int UserId);

    int deleteCompanyNewByCompanyId(@Param("id") int CompanyId);

    int deleteCompanyNewByCompanyIdAndNewId(@Param("companyId") int CompanyId, @Param("newId") int NewId);

    CompanyEntity selectCompanyByUserId(@Param("userId") int UserId);
}