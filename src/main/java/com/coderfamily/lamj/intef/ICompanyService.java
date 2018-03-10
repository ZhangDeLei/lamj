package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.CompanyEntity;
import com.coderfamily.lamj.model.CompanyUserEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/3 15:14
 */
public interface ICompanyService {
    /**
     * 获取企业列表
     * @param Name
     * @param EndDate
     * @param BegDate
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<CompanyEntity> getCompanyList(String Name, String BegDate,String EndDate, int PageSize, int CurPage);

    /**
     * 根据条件查询企业列表
     * @param entity
     * @return
     */
    List<CompanyEntity> getCompanyListByCondition(CompanyEntity entity);

    /**
     * 根据ID获取企业信息
     * @param Id
     * @return
     */
    CompanyEntity getCompanyById(int Id);

    /**
     * 判断是否超出有效期
     * @param begDate
     * @param endDate
     * @return
     */
    boolean isExpiredCompany(String begDate,String endDate);

    /**
     * 根据用户ID判断当前企业是否还在有效期之内
     * @param UserId
     * @return
     */
    boolean isExpiredCompanyByUserId(int UserId);

    /**
     * 判断当前企业的用户数是否已经超标
     * @param Id
     * @return
     */
    boolean existsCompanyByMaxNum(int Id);
    /**
     * 新增企业
     * @param entity
     * @return
     */
    Result insert(CompanyEntity entity);

    /**
     * 新增企业用户的关联关系
     * @param entity
     * @return
     */
    int insertCompanyUser(CompanyUserEntity entity);

    /**
     * 修改企业
     * @param entity
     * @return
     */
    Result update(CompanyEntity entity);

    /**
     * 删除企业
     * @param Id
     * @return
     */
    Result delete(int Id);

    /**
     * 删除根据企业ID和用户ID删除用户与企业的关联关系
     * @param CompanyId
     * @param UserId
     * @return
     */
    int deleteCompanyUser(int CompanyId,int UserId);

    /**
     * 删除所有的企业用户信息
     * @param CompanyId
     * @return
     */
    int deleteAllCompanyUser(int CompanyId);

    /**
     * 根据用户ID删除用户与企业的关联关系
     * @param UserId
     * @return
     */
    int deleteCompanyUserByUserId(int UserId);

}
