package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.CaliberEntity;
import com.coderfamily.lamj.intef.ICaliberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:53
 */
@Api(value = "caliber", description = "口径管理")
@RestController
@RequestMapping(value = "/api/caliber", headers = "version=1")
public class CaliberController {

    @Autowired
    private ICaliberService caliberService;

    @ApiOperation(value = "分页获取口径列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getCaliberList")
    public Result getCaliberList(@RequestParam(required = false) String Name,
                                 @RequestParam(required = false) Boolean Status,
                                 @RequestParam int PageSize,
                                 @RequestParam int CurPage) {
        return Result.success(caliberService.select(Name, Status, PageSize, CurPage));
    }

    @ApiOperation(value = "新增口径", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody CaliberEntity entity) {
        return caliberService.insert(entity);
    }

    @ApiOperation(value = "更新口径", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody CaliberEntity entity) {
        return caliberService.update(entity);
    }

    @ApiOperation(value = "删除口径", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        return Result.success(caliberService.delete(params.get("Id")));
    }
}
