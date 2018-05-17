package com.coderfamily.lamj.intef;

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
    PageInfo<DictionaryEntity> selectDictByPage(String Name,String EnName,Boolean Status,int PageSize,int CurPage);

    /**
     * 根据应用名称获取字典值列表
     * @param EnName
     * @return
     */
    List<DictionaryEntity> selectDictByCondition(String EnName);

    /**
     * 根据英文名称和编码获取具体的字典值信息
     * @param EnName
     * @param Code
     * @return
     */
    DictionaryEntity DictInfo(String EnName,String Code);
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
