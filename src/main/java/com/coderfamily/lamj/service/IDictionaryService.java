package com.coderfamily.lamj.service;

import com.coderfamily.lamj.model.DictionaryEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 16:12
 */
public interface IDictionaryService {
    /**
     * 根据条件查询字典列表
     *
     * @param dictionaryEntity
     * @return
     */
    List<DictionaryEntity> selectDictByCondition(DictionaryEntity dictionaryEntity);

    /**
     * 新增字典值
     *
     * @param dictionaryEntity
     * @return
     */
    int insert(DictionaryEntity dictionaryEntity);

    /**
     * 更新字典值
     *
     * @param dictionaryEntity
     * @return
     */
    int update(DictionaryEntity dictionaryEntity);

    /**
     * 删除字典值
     *
     * @param Id
     * @return
     */
    int delete(int Id);
}
