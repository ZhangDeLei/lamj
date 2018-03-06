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
     * 获取最大的编码
     *
     * @param EnName
     * @return
     */
    String selectMaxCode(@Param("enName") String EnName);

    /**
     * 根据ID查询字典详细信息
     *
     * @param Id
     * @return
     */
    DictionaryEntity selectDictById(@Param("id") int Id);

    /**
     * 根据名称和value判断当前字典值是否重复
     *
     * @param EnName
     * @param Code
     * @return
     */
    boolean existsByNameAndCode(@Param("enName") String EnName, @Param("code") String Code);

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