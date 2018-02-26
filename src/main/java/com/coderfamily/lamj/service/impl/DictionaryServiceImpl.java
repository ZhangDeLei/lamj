package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.dao.DictionaryMapper;
import com.coderfamily.lamj.model.DictionaryEntity;
import com.coderfamily.lamj.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 16:13
 */
@Service
@Transactional
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<DictionaryEntity> selectDictByCondition(DictionaryEntity dictionaryEntity) {
        return dictionaryMapper.selectDictByCondition(dictionaryEntity);
    }

    @Override
    public int insert(DictionaryEntity dictionaryEntity) {
        return dictionaryMapper.insert(dictionaryEntity);
    }

    @Override
    public int update(DictionaryEntity dictionaryEntity) {
        return dictionaryMapper.update(dictionaryEntity);
    }

    @Override
    public int delete(int Id) {
        return dictionaryMapper.delete(Id);
    }
}
