package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.AppManagerEntity;
import com.github.pagehelper.PageInfo;

/**
 * @author ZhangDL
 * @date 2018/3/21 10:02
 */
public interface IAppManagerService {

    /**
     * 获取app列表
     * @param Name
     * @param TypeId
     * @return
     */
    PageInfo<AppManagerEntity> getAppList(String Name,Integer TypeId, int PageSize, int CurPage);

    /**
     * 获取最新的app用于更新(iOS)
     * @return
     */
    AppManagerEntity getAppByNewForiOS();

    /**
     * 获取最新的app用于更新(Android)
     * @return
     */
    AppManagerEntity getAppByNewForAndroid();

    /**
     * 新增
     * @param entity
     * @return
     */
    Result insert(AppManagerEntity entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    Result update(AppManagerEntity entity);

    /**
     * 删除
     * @param Id
     * @return
     */
    Result delete(int Id);

}
