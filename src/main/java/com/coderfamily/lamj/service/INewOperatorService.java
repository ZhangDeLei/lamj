package com.coderfamily.lamj.service;

import com.coderfamily.lamj.model.NewOperatorEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 14:00
 */
public interface INewOperatorService {
    List<NewOperatorEntity> selectNewOperatorByNewId(int NewId);

    int insertByList(List<NewOperatorEntity> mList);

    int deleteByNewId(int NewId);
}
