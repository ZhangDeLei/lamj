package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.TeamEntity;
import com.coderfamily.lamj.service.ITeamService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/6 18:41
 */
@Api(value = "team", description = "队伍控制器")
@RestController
@RequestMapping(value = "/api/team", headers = "version=1")
public class TeamController {
    @Autowired
    private ITeamService teamService;

    @ApiOperation(value = "获取队伍列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getTeamList")
    public Result getTeamList(@RequestParam(required = false) String Name,
                              @RequestParam(required = false, defaultValue = "-1") int CompanyId,
                              @RequestParam int PageSize,
                              @RequestParam int CurPage) {
        PageInfo<TeamEntity> pageInfo = teamService.getTeamList(Name, CompanyId, PageSize, CurPage);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "获取所有的队伍列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getAllTeamList")
    public Result getAllTeamList() {
        return Result.success(teamService.getTeamListByCondition(new TeamEntity()));
    }

    @ApiOperation(value = "根据企业ID查询队伍列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getTeamListByCompanyId")
    public Result getTeamListByCompanyId(@RequestParam(required = false) String Name, @RequestParam int CompanyId) {
        TeamEntity entity = new TeamEntity();
        entity.setCompanyId(CompanyId);
        entity.setName(Name);
        List<TeamEntity> mData = teamService.getTeamListByCondition(entity);
        return Result.success(mData);
    }

    @ApiOperation(value = "新增队伍", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody TeamEntity params) {
        return teamService.insert(params);
    }

    @ApiOperation(value = "更新队伍", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody TeamEntity params) {
        return teamService.update(params);
    }

    @ApiOperation(value = "删除队伍", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        return teamService.delete(params.get("Id"));
    }
}
