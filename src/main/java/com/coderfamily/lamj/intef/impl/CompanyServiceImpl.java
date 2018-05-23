package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.CompanyMapper;
import com.coderfamily.lamj.intef.ITeamService;
import com.coderfamily.lamj.model.CompanyEntity;
import com.coderfamily.lamj.model.CompanyUserEntity;
import com.coderfamily.lamj.intef.ICompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private ITeamService teamService;

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
    public List<CompanyEntity> getCompanyByExpired() {
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 60 * 60 * 24 * 10);
        return companyMapper.selectComapnyByExpired(TimeUtils.formatYYMMDDHHMMSS(date));
    }

    @Override
    public boolean isExpiredCompany(String begDate, String endDate) {
        return companyMapper.selectCompanyIsExpired(begDate, endDate);
    }

    @Override
    public int getCompanyCount() {
        return companyMapper.selectCount();
    }

    @Override
    public boolean isExpiredCompanyByUserId(int UserId) {
        return companyMapper.isExpiredCompanyByUserId(UserId);
    }

    @Override
    public boolean existsCompanyByMaxNum(int Id) {
        return companyMapper.existsCompanyByMaxNum(Id);
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
    public int insertCompanyUser(CompanyUserEntity entity) {
        return companyMapper.insertCompanyUser(entity);
    }

    @Override
    public Result insertCompanyNew(int CompanyId, List<Integer> Ids) {
        companyMapper.deleteCompanyNewByCompanyId(CompanyId);
        if (Ids.size() == 0) {
            return Result.success();
        } else if (companyMapper.insertCompanyNew(CompanyId, Ids) > 0) {
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
            companyMapper.deleteCompanyNewByCompanyId(Id);
            companyMapper.deleteAllCompanyUser(Id);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int deleteCompanyUser(int CompanyId, int UserId) {
        return companyMapper.deleteCompanyUser(CompanyId, UserId);
    }

    @Override
    public int deleteAllCompanyUser(int CompanyId) {
        return companyMapper.deleteAllCompanyUser(CompanyId);
    }

    @Override
    public int deleteCompanyUserByUserId(int UserId) {
        return companyMapper.deleteCompanyUserByUserId(UserId);
    }

    @Override
    public Result deleteCompanyNewByCompanyIdAndNewId(int CompanyId, int NewId) {
        if (companyMapper.deleteCompanyNewByCompanyIdAndNewId(CompanyId, NewId) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public CompanyEntity getCompanyDetailByUserId(int UserId) {
        return companyMapper.selectCompanyByUserId(UserId);
    }
}
