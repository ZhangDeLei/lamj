package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.IAppManagerService;
import com.coderfamily.lamj.model.AppManagerEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/21 10:10
 */
@Api(value = "appManager", description = "app应用管理")
@RestController
@RequestMapping(value = "/api/appManager", headers = "version=1")
public class AppManagerController {

    @Autowired
    private IAppManagerService appManagerService;

    @ApiOperation(value = "获取APP应用列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getAppList")
    public Result getAppList(@RequestParam(required = false) String Name,
                             @RequestParam(required = false) Integer TypeId,
                             @RequestParam int PageSize,
                             @RequestParam int CurPage) {
        return Result.success(appManagerService.getAppList(Name,TypeId, PageSize, CurPage));
    }

    @ApiOperation(value = "获取最新的APP信息(iOS)", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getAppByNewForiOS")
    public Result getAppByNewForiOS() {
        return Result.success(appManagerService.getAppByNewForiOS());
    }

    @ApiOperation(value = "获取最新的APP信息(Android)", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getAppByNewForAndroid")
    public Result getAppByNewForAndroid() {
        return Result.success(appManagerService.getAppByNewForAndroid());
    }

    @ApiOperation(value = "新增APP更新记录", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody AppManagerEntity entity) {
        return appManagerService.insert(entity);
    }

    @ApiOperation(value = "更新app记录", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody AppManagerEntity entity) {
        return appManagerService.update(entity);
    }

    @ApiOperation(value = "删除app记录", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        return appManagerService.delete(params.get("Id"));
    }
}
