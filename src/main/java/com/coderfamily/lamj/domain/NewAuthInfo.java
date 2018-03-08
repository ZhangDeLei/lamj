package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.NewAuthEntity;
import com.coderfamily.lamj.model.NewOperatorEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 14:02
 */
public class NewAuthInfo extends NewAuthEntity {

    private List<NewOperatorEntity> oprs;

    public List<NewOperatorEntity> getOprs() {
        return oprs;
    }

    public void setOprs(List<NewOperatorEntity> oprs) {
        this.oprs = oprs;
    }
}
