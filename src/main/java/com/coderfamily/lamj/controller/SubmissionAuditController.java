package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.ISubmissionAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/14 16:14
 */
@Api(value = "submissionAudit", description = "网评投稿审核管理")
@RestController
@RequestMapping(value = "/api/submissionAudit", headers = "version=1")
public class SubmissionAuditController {

    @Autowired
    private ISubmissionAuditService submissionAuditService;

    @ApiOperation(value = "审核通过", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("adopt")
    public Result adopt(@RequestBody Map<String, Integer> params) {
        return Result.success();
    }

    @ApiOperation(value = "审核不通过", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("back")
    public Result back(@RequestBody Map<String, Integer> params) {
        return Result.success();
    }
}
