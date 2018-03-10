package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.dao.TeamMapper;
import com.coderfamily.lamj.model.CompanyEntity;
import com.coderfamily.lamj.model.TeamEntity;
import com.coderfamily.lamj.model.TeamTaskEntity;
import com.coderfamily.lamj.model.TeamUserEntity;
import com.coderfamily.lamj.intef.ICompanyService;
import com.coderfamily.lamj.intef.ITeamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/6 18:42
 */
@Service
@Transactional
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private ICompanyService companyService;

    @Override
    public PageInfo<TeamEntity> getTeamList(String Name, int CompanyId, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<TeamEntity> mData = teamMapper.selectTeamListByName(Name, CompanyId);
        PageInfo<TeamEntity> pageInfo = new PageInfo<>(mData);
        return pageInfo;
    }

    @Override
    public List<TeamEntity> getTeamListByCondition(TeamEntity entity) {
        return teamMapper.selectTeamListByCondition(entity);
    }

    @Override
    public Result insert(TeamEntity entity) {
        CompanyEntity companyEntity = companyService.getCompanyById(entity.getCompanyId());
        if(NullUtil.isNull(companyEntity)){
            return Result.error();
        }
        entity.setCompanyName(companyEntity.getName());
        if (teamMapper.existsTeamByName(entity.getName(), entity.getCompanyId())) {
            return Result.error("队伍已存在");
        } else if (teamMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int insertTeamUser(TeamUserEntity entity) {
        return teamMapper.insertTeamUser(entity);
    }

    @Override
    public int insertTeamTask(List<TeamTaskEntity> entities) {
        return teamMapper.insertTeamTask(entities);
    }

    @Override
    public Result update(TeamEntity entity) {
        CompanyEntity companyEntity = companyService.getCompanyById(entity.getCompanyId());
        if(NullUtil.isNull(companyEntity)){
            return Result.error();
        }
        entity.setCompanyName(companyEntity.getName());
        TeamEntity team = teamMapper.selectTeamById(entity.getId());
        if (NullUtil.isNull(team)) {
            return Result.error("队伍不存在");
        } else if (!entity.getName().equals(team.getName()) && teamMapper.existsTeamByName(entity.getName(), entity.getCompanyId())) {
            return Result.error("队伍名称已存在");
        } else if (teamMapper.update(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        TeamEntity team = teamMapper.selectTeamById(Id);
        if (NullUtil.isNull(team)) {
            return Result.error("队伍不存在");
        } else if (teamMapper.delete(Id) > 0 && teamMapper.deleteUserRelat(Id) >= 0) {
            teamMapper.deleteTeamTaskByTeamId(Id);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int deleteTeamUser(int TeamId, int UserId) {
        return teamMapper.deleteTeamUser(TeamId, UserId);
    }

    @Override
    public int deleteTeamUserByUser(int UserId) {
        return teamMapper.deleteTeamUserByUserId(UserId);
    }

    @Override
    public int deleteTeamTaskByTaskId(int TaskId) {
        return teamMapper.deleteTeamTaskByTaskId(TaskId);
    }

    @Override
    public int deleteTeamTaskByTaskIds(List<Integer> TaskIds) {
        return teamMapper.deleteTeamTaskByTaskIds(TaskIds);
    }
}
