package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.CompanyMapper;
import com.coderfamily.lamj.model.CompanyEntity;
import com.coderfamily.lamj.service.ICompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/3 15:15
 */
@Service
@Transactional
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public PageInfo<CompanyEntity> getCompanyList(String Name, String BegDate, String EndDate, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<CompanyEntity> mList = companyMapper.selectCompanyList(Name, BegDate, EndDate);
        PageInfo<CompanyEntity> pageInfo = new PageInfo<>(mList);
        return pageInfo;
    }

    @Override
    public CompanyEntity getCompanyById(int Id) {
        return companyMapper.selectCompanyById(Id);
    }

    @Override
    public List<CompanyEntity> getCompanyListByCondition(CompanyEntity entity) {
        return companyMapper.selectCompanyListByCondition(entity);
    }

    @Override
    public boolean isExpiredCompany(String begDate, String endDate) {
        return companyMapper.selectCompanyIsExpired(begDate, endDate);
    }

    @Override
    public boolean isExpiredCompanyByUserId(int UserId) {
        return companyMapper.isExpiredCompanyByUserId(UserId);
    }

    @Override
    public Result insert(CompanyEntity entity) {
        entity.setCreateDate(TimeUtils.getCurrentDate());
        if (companyMapper.existsCompanyByName(entity.getName())) {
            return Result.error("当前企业已经存在");
        } else if (companyMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(CompanyEntity entity) {
        CompanyEntity companyEntity = companyMapper.selectCompanyById(entity.getId());
        if (NullUtil.isNull(companyEntity)) {
            return Result.error("当前企业不存在");
        } else if (companyMapper.update(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        CompanyEntity companyEntity = companyMapper.selectCompanyById(Id);
        if (NullUtil.isNull(companyEntity)) {
            return Result.error("当前企业不存在");
        } else if (companyMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }
}
