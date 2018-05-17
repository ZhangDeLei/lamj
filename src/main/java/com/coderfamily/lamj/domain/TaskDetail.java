package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.TaskEntity;

/**
 * @author ZhangDL
 * @date 2018/5/15 14:49
 */
public class TaskDetail extends TaskEntity {
    private int total;
    private int commentTotal;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }
}