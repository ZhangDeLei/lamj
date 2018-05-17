package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.IUserCommentService;
import com.coderfamily.lamj.model.UserCommentEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/5/2 14:16
 */
@Api(value = "用户评论列表", description = "评论管理")
@RestController
@RequestMapping(value = "api/userComment", headers = "version=1")
public class UserCommentController {
    @Autowired
    private IUserCommentService userCommentService;

    @ApiOperation(value = "获取任务评论列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getUserCommentList")
    public Result getUserCommentList(@RequestParam int CompanyId,
                                     @RequestParam int TaskId,
                                     @RequestParam(required = false) Integer UserId,
                                     @RequestParam int PageSize,
                                     @RequestParam int CurPage) {

        return Result.success(userCommentService.getUserCommentList(CompanyId, TaskId, UserId, PageSize, CurPage));
    }

    @ApiOperation(value = "新增用户评论", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insertUserComment")
    public Result insertUserComment(@RequestBody UserCommentEntity params) {
        return userCommentService.insert(params);
    }

    @ApiOperation(value = "修改用户评论", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("updateUserComment")
    public Result updateUserComment(@RequestBody UserCommentEntity params) {
        return userCommentService.update(params);
    }

    @ApiOperation(value = "删除用户评论", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("deleteUserComment")
    public Result deleteUserComment(@RequestBody Map<String, Integer> params) {
        return userCommentService.delete(params.get("Id"));
    }
}
