package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.ISubmissionService;
import com.coderfamily.lamj.model.SubmissionEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/14 15:25
 */
@Api(value = "submission", description = "网评投稿管理")
@RestController
@RequestMapping(value = "/api/submission", headers = "version=1")
public class SubmissionController {

    @Autowired
    private ISubmissionService submissionService;

    @ApiOperation(value = "获取网评投稿列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getSubmissionList")
    public Result getSubmissionList(@RequestParam int CompanyId,
                                    @RequestParam(required = false) String ThemeName,
                                    @RequestParam(required = false) String Title,
                                    @RequestParam(required = false) Integer UserId,
                                    @RequestParam(required = false) Integer ProcessId,
                                    @RequestParam(required = false) Integer LevelId,
                                    @RequestParam(required = false) Boolean Status,
                                    @RequestParam int PageSize,
                                    @RequestParam int CurPage) {
        return Result.success(submissionService.getSubmissionList(CompanyId, ThemeName, Title, UserId, ProcessId, LevelId, Status, PageSize, CurPage));
    }

    @ApiOperation(value = "获取个人网评投稿列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getSubmissionListByUserId")
    public Result getSubmissionListByUserId(@RequestParam int CompanyId,
                                            @RequestParam(required = false) String ThemeName,
                                            @RequestParam(required = false) String Title,
                                            @RequestParam Integer UserId,
                                            @RequestParam(required = false) Integer ProcessId,
                                            @RequestParam(required = false) Integer LevelId,
                                            @RequestParam(required = false) Boolean Status,
                                            @RequestParam int PageSize,
                                            @RequestParam int CurPage) {
        return Result.success(submissionService.getSubmissionList(CompanyId, ThemeName, Title, UserId, ProcessId, LevelId, Status, PageSize, CurPage));
    }

    @ApiOperation(value = "根据ID获取网评投稿详细信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getSubmissionById")
    public Result getSubmissionById(@RequestParam int Id) {
        return Result.success(submissionService.getSubmissionById(Id));
    }

    @ApiOperation(value = "新增网评投稿", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody SubmissionEntity entity) {
        return submissionService.insert(entity);
    }

    @ApiOperation(value = "更新网评投稿信息", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody SubmissionEntity entity) {
        return submissionService.update(entity);
    }

    @ApiOperation(value = "根据ID删除网评投稿信息", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        return submissionService.delete(params.get("Id"));
    }

}
