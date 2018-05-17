package com.coderfamily.lamj.domain;

/**
 * @author ZhangDL
 * @date 2018/5/2 16:31
 */
public class UserStatisticsInfo {
    private int id;
    private String nickName;
    private String teamName;
    private int totalSub;
    private int totalFollow;
    private int totalTask;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalSub() {
        return totalSub;
    }

    public void setTotalSub(int totalSub) {
        this.totalSub = totalSub;
    }

    public int getTotalFollow() {
        return totalFollow;
    }

    public void setTotalFollow(int totalFollow) {
        this.totalFollow = totalFollow;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(int totalTask) {
        this.totalTask = totalTask;
    }
}
