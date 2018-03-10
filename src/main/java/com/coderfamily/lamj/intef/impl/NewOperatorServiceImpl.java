package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.dao.NewOperatorMapper;
import com.coderfamily.lamj.model.NewOperatorEntity;
import com.coderfamily.lamj.intef.INewOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 14:01
 */
@Service
@Transactional
public class NewOperatorServiceImpl implements INewOperatorService {
    @Autowired
    private NewOperatorMapper newOperatorMapper;

    @Override
    public List<NewOperatorEntity> selectNewOperatorByNewId(int NewId) {
        return newOperatorMapper.selectNewOperatorByNewId(NewId);
    }

    @Override
    public int insertByList(List<NewOperatorEntity> mList) {
        return newOperatorMapper.insertByList(mList);
    }

    @Override
    public int deleteByNewId(int NewId) {
        return newOperatorMapper.deleteByNewId(NewId);
    }
}
