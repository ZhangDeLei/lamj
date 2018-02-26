package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.DictionaryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictionaryMapper {

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
    int delete(@Param("id") int Id);

}