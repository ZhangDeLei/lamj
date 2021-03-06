package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.TaskInfo;
import com.coderfamily.lamj.intef.ITaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:53
 */
@Api(value = "task", description = "任务控制器")
@RestController
@RequestMapping(value = "/api/task", headers = "version=1")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @ApiOperation(value = "分页获取任务列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getTaskList")
    public Result getTaskList(@RequestParam int CompanyId,
                              @RequestParam(required = false) String Title,
                              @RequestParam(required = false) Integer StageId,
                              @RequestParam(required = false) Integer NewId,
                              @RequestParam(required = false) String BegDate,
                              @RequestParam(required = false) String EndDate,
                              @RequestParam int PageSize,
                              @RequestParam int CurPage) {
        return Result.success(taskService.getTaskList(CompanyId, Title, StageId, NewId, BegDate, EndDate, PageSize, CurPage));
    }

    @ApiOperation(value = "根据用户ID获取用户的任务列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getTaskListByUserId")
    public Result getTaskListByUserId(@RequestParam int UserId,
                                      @RequestParam(required = false) String Title,
                                      @RequestParam(required = false) Integer StageId,
                                      @RequestParam(required = false) Integer NewId,
                                      @RequestParam(required = false) String BegDate,
                                      @RequestParam(required = false) String EndDate,
                                      @RequestParam int PageSize,
                                      @RequestParam int CurPage) {
        return Result.success(taskService.getTaskListByUserId(UserId, Title, StageId, NewId, BegDate, EndDate, PageSize, CurPage));
    }

    @ApiOperation(value = "获取最新的任务列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getTaskListByNew")
    public Result getTaskListByNew(@RequestParam int CompanyId) {
        return Result.success(taskService.getTaskListByNew(CompanyId));
    }

    @ApiOperation(value = "根据ID获取任务信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getTaskById")
    public Result getTaskById(@RequestParam int Id) {
        return Result.success(taskService.getTaskById(Id));
    }

    @ApiOperation(value = "新增任务", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody TaskInfo info) {
        return taskService.insert(info);
    }

    @ApiOperation(value = "更新任务", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody TaskInfo info) {
        return taskService.update(info);
    }

    @ApiOperation(value = "删除任务", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        return taskService.delete(params.get("Id"));
    }

    @ApiOperation(value = "批量删除任务", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Integer> Ids) {
        return taskService.deleteByIds(Ids);
    }
}
