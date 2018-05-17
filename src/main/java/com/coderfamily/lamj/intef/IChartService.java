package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.domain.ChartInfo;
import com.coderfamily.lamj.domain.ChartIntegralInfo;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/5/16 15:07
 */
public interface IChartService {
    List<ChartInfo> chartTeam(int CompanyId);

    List<ChartInfo> chartTask(int CompanyId);

    ChartIntegralInfo chartIntegral(int CompanyId);
}
