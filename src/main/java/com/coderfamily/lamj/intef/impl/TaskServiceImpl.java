package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.TaskMapper;
import com.coderfamily.lamj.domain.TaskInfo;
import com.coderfamily.lamj.model.TaskEntity;
import com.coderfamily.lamj.model.TeamEntity;
import com.coderfamily.lamj.model.TeamTaskEntity;
import com.coderfamily.lamj.intef.ITaskService;
import com.coderfamily.lamj.intef.ITeamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:59
 */
@Service
@Transactional
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ITeamService teamService;

    @Override
    public PageInfo<TaskEntity> getTaskList(int CompanyId, String Title, int StageId, int NewId, int PageSize, int CurPage) {
        PageHelper.startPage(PageSize, CurPage);
        TaskEntity entity = new TaskEntity();
        entity.setCompanyId(CompanyId);
        entity.setTitle(Title);
        entity.setStageId(StageId);
        entity.setNewId(NewId);
        return new PageInfo<>(taskMapper.select(entity));
    }

    @Override
    public TaskInfo getTaskById(int Id) {
        return taskMapper.selectById(Id);
    }

    @Override
    public Result insert(TaskInfo info) {
        info.setCreateDate(TimeUtils.getCurrentDate());
        if (taskMapper.insert(info) > 0) {
            insertTeamTask(info.getId(), info.getTeams());
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result update(TaskInfo info) {
        if (taskMapper.update(info) > 0) {
            insertTeamTask(info.getId(), info.getTeams());
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        if (taskMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result deleteByIds(List<Integer> Ids) {
        if (taskMapper.deleteByIds(Ids) > 0) {
            teamService.deleteTeamTaskByTaskIds(Ids);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    /**
     * 新增队伍与任务的关联关系
     *
     * @param TaskId
     * @param mList
     */
    private void insertTeamTask(int TaskId, List<TeamEntity> mList) {
        if (mList != null && mList.size() > 0) {
            teamService.deleteTeamTaskByTaskId(TaskId);
            List<TeamTaskEntity> mData = new ArrayList<>();
            mList.forEach(t -> {
                TeamTaskEntity entity = new TeamTaskEntity();
                entity.setTeamId(t.getId());
                entity.setTaskId(TaskId);
                mData.add(entity);
            });
            teamService.insertTeamTask(mData);
        }
    }
}
