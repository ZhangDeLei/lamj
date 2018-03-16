package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.SubmissionInfo;
import com.coderfamily.lamj.model.SubmissionEntity;
import com.github.pagehelper.PageInfo;

/**
 * @author ZhangDL
 * @date 2018/3/14 15:30
 */
public interface ISubmissionService {

    /**
     * 获取网评投稿列表
     *
     * @param ComapnyId
     * @param ThemeName
     * @param Title
     * @param UserId
     * @param ProcessId
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<SubmissionInfo> getSubmissionList(int ComapnyId, String ThemeName, String Title, Integer UserId, Integer ProcessId, Boolean Status, int PageSize, int CurPage);

    /**
     * 根据ID获取网评投稿详细信息
     *
     * @param Id
     * @return
     */
    SubmissionInfo getSubmissionById(int Id);

    /**
     * 新增网评投稿信息
     *
     * @param entity
     * @return
     */
    Result insert(SubmissionEntity entity);

    /**
     * 更新网评投稿信息
     *
     * @param entity
     * @return
     */
    Result update(SubmissionEntity entity);

    /**
     * 删除网评投稿信息
     *
     * @param Id
     * @return
     */
    Result delete(int Id);

    /**
     * 根据企业ID删除网评投稿信息
     *
     * @param CompanyId
     * @return
     */
    int deleteByCompanyId(int CompanyId);

    /**
     * 根据用户删除网评投稿信息
     *
     * @param UserId
     * @return
     */
    int deleteByUserId(int UserId);

    /**
     * 更新状态
     *
     * @param Id
     * @param Status
     * @return
     */
    int updateStatus(int Id, int ProcessId, String ProcessCode, String ProcessName, boolean Status);

}
