package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.domain.MenuInfo;
import com.coderfamily.lamj.model.PermissionEntity;
import com.coderfamily.lamj.intef.IMenuService;
import com.coderfamily.lamj.intef.IPermissionMenuService;
import com.coderfamily.lamj.intef.IPermissionService;
import com.github.pagehelper.PageInfo;
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
    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取权限列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getPermissionList")
    public Result getPermissionList(@RequestParam(required = false) String Name, @RequestParam int PageSize, @RequestParam int CurPage) {
        PageInfo<PermissionEntity> mList = permissionService.selectPermissionByPage(Name, PageSize, CurPage);
        return Result.success(mList);
    }

    @ApiOperation(value = "获取所有权限列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getAllPermissionList")
    public Result getAllPermissionList(){
        return Result.success(permissionService.selectPermissionByCondition(new PermissionEntity()));
    }

    @ApiOperation(value = "根据用户ID获取用户权限列表", httpMethod = "GET", produces = "appilcation/json", response = Result.class)
    @ResponseBody
    @GetMapping("getUserPermissionList")
    public Result getUserPermissionList(@RequestParam int UserId) {
        List<PermissionEntity> mList = permissionService.selectPermissionByUserId(UserId);
        return Result.success(mList);
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
        return permissionService.delete(params.get("Id"));
    }

    @ApiOperation(value = "新增权限与菜单的关联关系(批量新增)", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("insertPermisionMenuRela")
    public Result insertPermisionMenuRela(@RequestBody Map<String, Object> params) {
        return permissionMenuService.insert(NumberUtil.toInt(params.get("Id") + ""), (List) params.get("mIds"));
    }

    @ApiOperation(value = "新增用户与权限的关联关系", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("insertUserPermission")
    public Result insertUserPermission(@RequestBody Map<String, Object> params) {
        int UserId = NumberUtil.toInt(params.get("UserId") + "");
        List<Integer> mIds = (List) params.get("mIds");
        return permissionService.insertUserRelation(UserId, mIds);
    }

    @ApiOperation(value = "根据权限ID获取权限的菜单树形列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getPermissionMenuForTreeById")
    public Result getPermissionMenuForTreeById(@RequestParam int Id) {
        List<MenuInfo> mList = menuService.selectPermissionMenuByTree(Id);
        return Result.success(mList);
    }

    @ApiOperation(value = "获取所有可用的权限列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getPermissionListByUsed")
    public Result getPermissionListByUsed() {
        PermissionEntity entity = new PermissionEntity();
        entity.setStatus(true);
        List<PermissionEntity> mList = permissionService.selectPermissionByCondition(entity);
        return Result.success(mList);
    }
}
