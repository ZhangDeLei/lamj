package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.CompanyNewInfo;
import com.coderfamily.lamj.domain.NewAuthInfo;
import com.coderfamily.lamj.model.NewAuthEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:58
 */
public interface INewAuthService {

    PageInfo<NewAuthInfo> select(String Name, Boolean Status, int PageSize, int CurPage);

    PageInfo<CompanyNewInfo> select(int CompanyId, Boolean Status, int PageSize, int CurPage);

    List<CompanyNewInfo> select(int CompanyId);

    List<NewAuthInfo> selectAll();

    NewAuthEntity selectById(int Id);

    Result insert(NewAuthInfo info);

    Result update(NewAuthInfo info);

    Result delete(int Id);
}
