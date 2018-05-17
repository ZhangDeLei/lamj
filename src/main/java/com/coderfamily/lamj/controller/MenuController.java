package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.ResponseCode;
import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.domain.MenuInfo;
import com.coderfamily.lamj.model.MenuEntity;
import com.coderfamily.lamj.intef.IMenuService;
import com.coderfamily.lamj.intef.IPermissionMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/2/2 16:22
 */
@RestController
@Api(value = "menu", description = "用户菜单管理")
@RequestMapping(value = "api/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IPermissionMenuService permissionMenuService;

    @ApiOperation(value = "获取用户菜单列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getMenuListByUserId")
    public Result getMenuListByUserId(@RequestParam(required = true) int UserId) {
        List<MenuInfo> menuList = menuService.selectMenuByUserId(UserId);
        Result result = null;
        if (NullUtil.isNull(menuList)) {
            result = Result.init(ResponseCode.result_null.getCode(), ResponseCode.result_null.getMsg());
        } else {
            result = Result.success(menuList);
        }
        return result;
    }

    @ApiOperation(value = "获取菜单树形列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getMenuListByTree")
    public Result getMenuListByTree() {
        List<MenuInfo> menuList = menuService.selectMenuByTree();
        Result result = null;
        if (NullUtil.isNull(menuList)) {
            result = Result.init(ResponseCode.result_null.getCode(), ResponseCode.result_null.getMsg());
        } else {
            result = Result.success(menuList);
        }
        return result;
    }

    @ApiOperation(value = "根据ParentId获取菜单列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getMenuListByParentId")
    public Result getMenuListByParentId(@RequestParam(required = true) int ParentId) {
        List<MenuEntity> menuList = menuService.selectMenuByParentId(ParentId);
        Result result = null;
        if (NullUtil.isNull(menuList)) {
            result = Result.init(ResponseCode.result_null.getCode(), ResponseCode.result_null.getMsg());
        } else {
            result = Result.success(menuList);
        }
        return result;
    }

    @ApiOperation(value = "根据ID获取菜单详细信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getMenuById")
    public Result getMenuById(@RequestParam int Id) {
        MenuEntity menuEntity = menuService.selectMenubyId(Id);
        Result result = null;
        if (NullUtil.isNull(menuEntity)) {
            result = Result.error("当前菜单不存在");
        } else {
            result = Result.success(menuEntity);
        }

        return result;
    }

    @ApiOperation(value = "检查是否可以执行删除操作", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("checkDelete")
    public Result checkDeleteByMenuId(@RequestParam int Id) {
        return Result.success(permissionMenuService.checkDeleteByMenuId(Id));
    }

    @ApiOperation(value = "新增菜单", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("insertMenu")
    public Result insertMenu(@RequestBody MenuEntity param) {
        return menuService.insert(param);
    }

    @ApiOperation(value = "更新菜单", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("updateMenu")
    public Result updateMenu(@RequestBody MenuEntity param) {
        return menuService.update(param);
    }

    @ApiOperation(value = "根据ID删除菜单", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("deleteMenu")
    public Result deleteMenu(@RequestBody Map<String, Integer> param) {
        return menuService.delete(param.get("Id"));
    }
}
