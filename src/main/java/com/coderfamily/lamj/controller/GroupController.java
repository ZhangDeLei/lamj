package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.domain.GroupPermissionInfo;
import com.coderfamily.lamj.model.GroupEntity;
import com.coderfamily.lamj.model.GroupPermissionEntity;
import com.coderfamily.lamj.service.IGroupService;
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
@Api(value = "group", description = "用户分组管理")
@RequestMapping(value = "api/group")
public class GroupController {
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "获取所有用户组信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getGroupList")
    public Result getGroupList() {
        List<GroupEntity> mList = groupService.selectGroupList();
        return Result.success(mList);
    }

    @ApiOperation(value = "新增用户组", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("insertGroup")
    public Result insertGroup(@RequestBody GroupEntity param) {
        return groupService.insert(param);
    }

    @ApiOperation(value = "更新用户组", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("updateGroup")
    public Result updateGroup(@RequestBody GroupEntity param) {
        if (groupService.update(param) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "删除用户组", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("deleteGroup")
    public Result deleteGroup(@RequestBody Map<String, Integer> param) {
        return groupService.delete(param.get("Id"));
    }

    @ApiOperation(value = "更新用户组权限信息", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("updateGroupPermission")
    public Result updateGroupPermission(@RequestBody Map<String, Object> params) {
        return permissionService.insertGroupRelation(NumberUtil.toInt(params.get("Id") + ""), (List) params.get("mIds"));
    }

    @ApiOperation(value = "根据用户组ID获取用户组权限信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getGroupPermissionByGroupId")
    public Result getGroupPermissionByGroupId(@RequestParam int Id) {
        List<GroupPermissionInfo> mList = groupService.selectGroupPermissionByGroupId(Id);
        return Result.success(mList);
    }
}
