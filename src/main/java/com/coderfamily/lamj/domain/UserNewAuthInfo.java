package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.NewAuthEntity;
import com.coderfamily.lamj.model.UserNewAuthEntity;

public class UserNewAuthInfo extends UserNewAuthEntity {
    private NewAuthEntity newAuthEntity;

    public NewAuthEntity getNewAuthEntity() {
        return newAuthEntity;
    }

    public void setNewAuthEntity(NewAuthEntity newAuthEntity) {
        this.newAuthEntity = newAuthEntity;
    }
}
