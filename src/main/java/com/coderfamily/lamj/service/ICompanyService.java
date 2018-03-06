package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.CompanyEntity;
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
     * 新增企业
     * @param entity
     * @return
     */
    Result insert(CompanyEntity entity);

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
}
