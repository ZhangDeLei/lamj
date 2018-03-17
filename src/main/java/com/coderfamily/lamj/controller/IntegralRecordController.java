package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.IDictionaryService;
import com.coderfamily.lamj.intef.IIntegralRecordService;
import com.coderfamily.lamj.model.DictionaryEntity;
import com.coderfamily.lamj.model.IntegralRecordEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/15 08:36
 */
@Api(value = "integralRecord", description = "积分管理")
@RestController
@RequestMapping(value = "/api/integralRecord", headers = "version=1")
public class IntegralRecordController {
    @Autowired
    private IIntegralRecordService integralRecordService;
    @Autowired
    private IDictionaryService dictionaryService;

    @ApiOperation(value = "个人积分查询", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getIntegralRecordByUserId")
    public Result getIntegralRecordByUserId(@RequestParam int UserId,
                                            @RequestParam(required = false) Integer SourceId,
                                            @RequestParam int PageSize,
                                            @RequestParam int CurPage) {
        return Result.success(integralRecordService.getIntegralRecordByUserId(UserId, SourceId, PageSize, CurPage));
    }

    @ApiOperation(value = "企业查询用户积分", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getIntegralRecordByCompany")
    public Result getIntegralRecordByCompany(@RequestParam int CompanyId,
                                             @RequestParam(required = false) Integer UserId,
                                             @RequestParam(required = false) Integer SourceId,
                                             @RequestParam(required = false) Integer SourceUserId,
                                             @RequestParam int PageSize,
                                             @RequestParam int CurPage) {
        return Result.success(integralRecordService.getIntegralRecordByCompany(CompanyId, UserId, SourceId, SourceUserId, PageSize, CurPage));
    }

    @ApiOperation(value = "根据任务ID查询积分记录", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getIntegralRecordByTaskId")
    public Result getIntegralRecordByTaskId(@RequestParam int TaskId) {
        return Result.success(integralRecordService.getIntegralRecordByTaskId(TaskId));
    }

    @ApiOperation(value = "根据任务ID及用户ID查询积分记录", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getIntegralRecordByTaskIdAndUserId")
    public Result getIntegralRecordByTaskIdAndUserId(@RequestParam int TaskId, @RequestParam int UserId) {
        return Result.success(integralRecordService.getIntegralRecordByTaskIdAndUserId(TaskId, UserId));
    }

    @ApiOperation(value = "根据投稿ID查询积分记录", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getIntegralRecordBySubmissionId")
    public Result getIntegralRecordBySubmissionId(@RequestParam int SubmissionId) {
        return Result.success(integralRecordService.getIntegralRecordBySubmissionId(SubmissionId));
    }

    @ApiOperation(value = "新增积分", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody IntegralRecordEntity entity) {
        DictionaryEntity dict = dictionaryService.DictInfo("Source","0003");
        entity.setSourceName(dict.getLabel());
        entity.setSourceCode(dict.getCode());
        entity.setSourceId(dict.getId());
        if (integralRecordService.insert(entity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "核减积分", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("reduce")
    public Result reduce(@RequestBody IntegralRecordEntity entity) {
        return integralRecordService.reduce(entity);
    }
}
