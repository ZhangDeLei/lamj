package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.PermissionEntity;
import com.coderfamily.lamj.model.PermissionMenuEntity;
import com.coderfamily.lamj.service.IPermissionMenuService;
import com.coderfamily.lamj.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/2/2 16:22
 */
@Controller
@Api(value = "permission", description = "用户权限管理")
@RequestMapping(value = "api/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IPermissionMenuService permissionMenuService;

    @ApiOperation(value = "获取权限列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getPermissionList")
    public Result getPermissionList() {
        Result result = null;
        List<PermissionEntity> mList = permissionService.selectPermissionByCondition(new PermissionEntity());
        if (mList == null) {
            result = Result.error("当前不存在权限信息");
        } else {
            result = Result.success(mList);
        }
        return result;
    }

    @ApiOperation(value = "新增权限", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("insert")
    public Result insert(@RequestBody PermissionEntity params) {
        return permissionService.insert(params);
    }

    @ApiOperation(value = "修改权限", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("update")
    public Result update(@RequestBody PermissionEntity params) {
        Result result = null;
        if (permissionService.update(params) > 0) {
            result = Result.success();
        } else {
            result = Result.error();
        }
        return result;
    }

    @ApiOperation(value = "删除权限", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        if (permissionService.delete(params.get("Id")) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "新增权限与菜单的关联关系(批量新增)", httpMethod = "POST", produces = "application", response = Result.class)
    @ResponseBody
    @PostMapping("insertPermisionMenuRela")
    public Result insertPermisionMenuRela(@RequestBody List<PermissionMenuEntity> mList) {
        return permissionMenuService.insert(mList);
    }

}
