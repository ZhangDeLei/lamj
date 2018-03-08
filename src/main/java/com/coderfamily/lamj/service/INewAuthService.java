package com.coderfamily.lamj.service;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.NewAuthInfo;
import com.github.pagehelper.PageInfo;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:58
 */
public interface INewAuthService {

    PageInfo<NewAuthInfo> select(String Name,Boolean Status,int PageSize,int CurPage);

    Result insert(NewAuthInfo info);

    Result update(NewAuthInfo info);

    Result delete(int Id);
}
