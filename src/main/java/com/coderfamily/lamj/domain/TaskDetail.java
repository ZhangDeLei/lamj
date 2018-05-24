package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.TaskEntity;

/**
 * @author ZhangDL
 * @date 2018/5/15 14:49
 */
public class TaskDetail extends TaskEntity {
    private int total;
    private int commentTotal;
    private boolean isUserComplated;
    private String imageUrl;

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

    public boolean isUserComplated() {
        return isUserComplated;
    }

    public void setUserComplated(boolean userComplated) {
        isUserComplated = userComplated;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
