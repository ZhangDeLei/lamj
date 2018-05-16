package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.dao.ChartMapper;
import com.coderfamily.lamj.domain.ChartInfo;
import com.coderfamily.lamj.domain.ChartIntegralInfo;
import com.coderfamily.lamj.intef.IChartService;
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
}
