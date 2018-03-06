package com.coderfamily.lamj.service;

import com.coderfamily.lamj.model.DictionaryEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 16:12
 */
public interface IDictionaryService {
    /**
     * 根据条件查询字典列表
     *
     * @param Name
     * @param EnName
     * @param Status
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<DictionaryEntity> selectDictByPage(String Name,String EnName,int Status,int PageSize,int CurPage);

    /**
     * 根据
     * @param EnName
     * @return
     */
    List<DictionaryEntity> selectDictByCondition(String EnName);
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
