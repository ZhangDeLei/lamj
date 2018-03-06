package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.dao.TeamMapper;
import com.coderfamily.lamj.model.TeamEntity;
import com.coderfamily.lamj.model.TeamUserEntity;
import com.coderfamily.lamj.service.ITeamService;
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

    @Override
    public PageInfo<TeamEntity> getTeamList(String Name, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        List<TeamEntity> mData = teamMapper.selectTeamListByName(Name);
        PageInfo<TeamEntity> pageInfo = new PageInfo<>(mData);
        return pageInfo;
    }

    @Override
    public List<TeamEntity> getTeamListByCondition(TeamEntity entity) {
        return teamMapper.selectTeamListByCondition(entity);
    }

    @Override
    public Result insert(TeamEntity entity) {
        if (teamMapper.existsTeamByName(entity.getName(), entity.getCompanyId())) {
            return Result.error("队伍已存在");
        } else if (teamMapper.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int insertTeamUser(List<TeamUserEntity> mList) {
        return teamMapper.insertTeamUser(mList);
    }

    @Override
    public Result update(TeamEntity entity) {
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
        } else if (teamMapper.delete(Id) > 0 && teamMapper.deleteUserRelat(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }
}
