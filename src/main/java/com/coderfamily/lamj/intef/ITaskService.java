package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
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
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<TaskEntity> getTaskList(int CompanyId, String Title, int StageId, int NewId, int PageSize, int CurPage);

    /**
     * 根据ID获取任务信息
     *
     * @param Id
     * @return
     */
    TaskInfo getTaskById(int Id);

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
