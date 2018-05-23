package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.IChartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangDL
 * @date 2018/5/16 14:49
 */
@Api(value = "图标查询管理", description = "图标查询管理")
@RestController
@RequestMapping(value = "/api/chart", headers = "version=1")
public class ChartController {

    @Autowired
    private IChartService chartService;

    @ApiOperation(value = "根据企业ID统计队伍人数信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("chartTeam")
    public Result chartTeam(@RequestParam int CompanyId) {
        return Result.success(chartService.chartTeam(CompanyId));
    }

    @ApiOperation(value = "根据企业ID统计任务分布情况", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("chartTask")
    public Result chartTask(@RequestParam int CompanyId) {
        return Result.success(chartService.chartTask(CompanyId));
    }

    @ApiOperation(value = "根据企业ID统计积分数据", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("chartIntegral")
    public Result chartIntegral(@RequestParam int CompanyId) {
        return Result.success(chartService.chartIntegral(CompanyId));
    }

    @ApiOperation(value = "查询系统数据信息",httpMethod = "GET",produces = "application/json",response = Result.class)
    @GetMapping("chartSystemMainTotal")
    public Result chartSystemMainTotal(){
        return Result.success(chartService.chartSystemMainTotal());
    }
}
