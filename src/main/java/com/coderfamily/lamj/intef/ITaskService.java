package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.TaskDetail;
import com.coderfamily.lamj.domain.TaskInfo;
import com.coderfamily.lamj.model.TaskEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:58
 */
public interface ITaskService {

    /**
     * 分页获取任务列表
     *
     * @param CompanyId
     * @param Title
     * @param StageId
     * @param NewId
     * @param BegDate
     * @param EndDate
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<TaskEntity> getTaskList(int CompanyId, String Title, Integer StageId, Integer NewId, String BegDate, String EndDate, int PageSize, int CurPage);

    /**
     * 分页获取用户的任务列表
     *
     * @param UserId
     * @param Title
     * @param StageId
     * @param NewId
     * @param BegDate
     * @param EndDate
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<TaskDetail> getTaskListByUserId(int UserId, String Title, Integer StageId, Integer NewId, String BegDate, String EndDate, int PageSize, int CurPage);

    /**
     * 获取最新的任务列表
     * @param CompanyId
     * @return
     */
    List<TaskDetail> getTaskListByNew(int CompanyId);

    /**
     * 根据ID获取任务信息
     *
     * @param Id
     * @return
     */
    TaskInfo getTaskById(int Id);

    /**
     * 获取需要完成任务的人数
     * @param TaskId
     * @return
     */
    int getSucTask(int TaskId);

    /**
     * 获取平台任务总数
     * @return
     */
    int getTaskCount();

    /**
     * 新增任务信息
     *
     * @param info
     * @return
     */
    Result insert(TaskInfo info);

    /**
     * 更新任务信息
     *
     * @param info
     * @return
     */
    Result update(TaskInfo info);

    /**
     * 更新任务信息
     * @param entity
     * @return
     */
    int update(TaskEntity entity);

    /**
     * 根据ID删除任务
     *
     * @param Id
     * @return
     */
    Result delete(int Id);

    /**
     * 批量删除任务列表
     *
     * @param Ids
     * @return
     */
    Result deleteByIds(List<Integer> Ids);


}
