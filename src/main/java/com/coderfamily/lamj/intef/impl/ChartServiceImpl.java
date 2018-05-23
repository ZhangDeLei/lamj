package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.dao.ChartMapper;
import com.coderfamily.lamj.domain.ChartInfo;
import com.coderfamily.lamj.domain.ChartIntegralInfo;
import com.coderfamily.lamj.domain.SystemMainInfo;
import com.coderfamily.lamj.intef.IChartService;
import com.coderfamily.lamj.intef.ICompanyService;
import com.coderfamily.lamj.intef.ITaskService;
import com.coderfamily.lamj.intef.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/5/16 15:07
 */
@Service
public class ChartServiceImpl implements IChartService {
    @Autowired
    private ChartMapper chartMapper;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IUserService userService;

    @Override
    public List<ChartInfo> chartTeam(int CompanyId) {
        return chartMapper.selectTeam(CompanyId);
    }

    @Override
    public List<ChartInfo> chartTask(int CompanyId) {
        return chartMapper.selectTask(CompanyId);
    }

    @Override
    public ChartIntegralInfo chartIntegral(int CompanyId) {
        ChartIntegralInfo integralInfo = new ChartIntegralInfo();
        integralInfo.setTeamIntegral(chartMapper.selectIntegralByTeam(CompanyId));
        integralInfo.setTeamUserIntegral(chartMapper.selectIntegralByMaxUser(CompanyId));
        return integralInfo;
    }

    @Override
    public SystemMainInfo chartSystemMainTotal() {
        SystemMainInfo systemMainInfo = new SystemMainInfo();
        systemMainInfo.setCompanyTotal(companyService.getCompanyCount());
        systemMainInfo.setTaskTotal(taskService.getTaskCount());
        systemMainInfo.setUserTotal(userService.getUserCount());
        return systemMainInfo;
    }
}
