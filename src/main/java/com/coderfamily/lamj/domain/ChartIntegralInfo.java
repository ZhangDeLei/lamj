package com.coderfamily.lamj.domain;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/5/16 14:57
 */
public class ChartIntegralInfo {
    private List<ChartInfo> teamIntegral;
    private List<ChartInfo> teamUserIntegral;

    public List<ChartInfo> getTeamIntegral() {
        return teamIntegral;
    }

    public void setTeamIntegral(List<ChartInfo> teamIntegral) {
        this.teamIntegral = teamIntegral;
    }

    public List<ChartInfo> getTeamUserIntegral() {
        return teamUserIntegral;
    }

    public void setTeamUserIntegral(List<ChartInfo> teamUserIntegral) {
        this.teamUserIntegral = teamUserIntegral;
    }
}
