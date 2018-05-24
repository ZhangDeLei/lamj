package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.UserCommentEntity;

public class UserCommentInfo extends UserCommentEntity {
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
