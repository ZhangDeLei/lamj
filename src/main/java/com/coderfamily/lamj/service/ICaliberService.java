package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.CaliberEntity;
import com.github.pagehelper.PageInfo;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:57
 */
public interface ICaliberService {
    PageInfo<CaliberEntity> select(String Name, Boolean Status, int PageSize, int CurPage);

    Result insert(CaliberEntity entity);

    Result update(CaliberEntity entity);

    Result delete(int Id);
}
