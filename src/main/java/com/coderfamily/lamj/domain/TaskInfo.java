package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.TaskEntity;
import com.coderfamily.lamj.model.TeamEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 16:34
 */
public class TaskInfo extends TaskEntity {
    private List<TeamEntity> teams;

    public List<TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamEntity> teams) {
        this.teams = teams;
    }
}
