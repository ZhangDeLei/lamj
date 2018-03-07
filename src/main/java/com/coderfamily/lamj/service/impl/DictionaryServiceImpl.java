package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.dao.DictionaryMapper;
import com.coderfamily.lamj.model.DictionaryEntity;
import com.coderfamily.lamj.service.IDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<DictionaryEntity> selectDictByPage(String Name, String EnName, Boolean Status, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        DictionaryEntity entity = new DictionaryEntity();
        entity.setName(Name);
        entity.setEnName(EnName);
        entity.setStatus(Status);
        List<DictionaryEntity> mData = dictionaryMapper.selectDictByCondition(entity);
        return new PageInfo<>(mData);
    }

    @Override
    public List<DictionaryEntity> selectDictByCondition(String EnName) {
        DictionaryEntity entity = new DictionaryEntity();
        entity.setEnName(EnName);
        entity.setStatus(true);
        List<DictionaryEntity> mData = dictionaryMapper.selectDictByCondition(entity);
        return mData;
    }

    @Override
    public DictionaryEntity DictInfo(String EnName, String Code) {
        return dictionaryMapper.selectDictByEnNameAndCode(EnName, Code);
    }

    @Override
    public int insert(DictionaryEntity dictionaryEntity) {
        if (dictionaryMapper.existsByNameAndCode(dictionaryEntity.getEnName(), dictionaryEntity.getCode())) {
            return -1;
        } else {
            dictionaryEntity.setCode(getMaxCode(dictionaryEntity.getEnName()));
            return dictionaryMapper.insert(dictionaryEntity);
        }
    }

    @Override
    public int update(DictionaryEntity dictionaryEntity) {
        DictionaryEntity entity = dictionaryMapper.selectDictById(dictionaryEntity.getId());
        if (NullUtil.isNull(entity)) {
            return -1;
        } else {
            entity.setStatus(dictionaryEntity.getStatus());
            entity.setDescription(dictionaryEntity.getDescription());
            return dictionaryMapper.update(entity);
        }
    }

    @Override
    public int delete(int Id) {
        return dictionaryMapper.delete(Id);
    }

    /**
     * 获取当前字典值的最大编码
     *
     * @param enName
     * @return
     */
    private String getMaxCode(String enName) {
        String curCode = dictionaryMapper.selectMaxCode(enName);
        curCode = NullUtil.isNull(curCode) ? "0000" : curCode;
        int code = NumberUtil.toInt(curCode) + 1;
        return NumberUtil.seats(code, curCode.length());
    }
}
