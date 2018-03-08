package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.NewAuthInfo;
import com.coderfamily.lamj.service.INewAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:53
 */
@Api(value = "newAuth", description = "新闻客户端授权管理")
@RestController
@RequestMapping(value = "/api/newAuth", headers = "version=1")
public class NewAuthController {
    @Autowired
    private INewAuthService newAuthService;

    @ApiOperation(value = "获取新闻客户端授权管理列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getNewAuthList")
    public Result getNewAuthList(@RequestParam(required = false) String Name,
                                 @RequestParam(required = false) Boolean Status,
                                 @RequestParam int PageSize,
                                 @RequestParam int CurPage) {
        return Result.success(newAuthService.select(Name, Status, PageSize, CurPage));
    }

    @ApiOperation(value = "新增新闻客户端授权",httpMethod = "POST",produces = "application/json",response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody NewAuthInfo info){
        return newAuthService.insert(info);
    }

    @ApiOperation(value = "更新新闻客户端授权",httpMethod = "POST",produces = "application/json",response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody NewAuthInfo info){
        return newAuthService.update(info);
    }

    @ApiOperation(value = "删除新闻客户端授权",httpMethod = "POST",produces = "application/json",response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String,Integer> params){
        return newAuthService.delete(params.get("Id"));
    }
}
