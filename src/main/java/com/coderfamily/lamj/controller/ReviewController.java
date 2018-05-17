package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangDL
 * @date 2018/5/17 09:51
 */
@Api(value = "新闻评论管理", description = "新闻评论管理")
@RestController
@RequestMapping(value = "/api/review", headers = "version=1")
public class ReviewController {

    @ApiOperation(value = "转发", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("forward")
    public Result forward() {
        return Result.success();
    }

    @ApiOperation(value = "评论", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("discuss")
    public Result discuss() {
        return Result.success();
    }

    @ApiOperation(value = "发帖", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("postHair")
    public Result postHair() {
        return Result.success();
    }

    @ApiOperation(value = "点赞", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("thumbs")
    public Result thumbs() {
        return Result.success();
    }
}
