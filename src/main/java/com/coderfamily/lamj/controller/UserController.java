package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.ResponseCode;
import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.model.UserEntity;
import com.coderfamily.lamj.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/1/25 16:44
 */
@Controller
@Api(value = "User控制器", description = "用户管理")
@RequestMapping(value = "/api/user", headers = "version=1")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "根据条件查询用户列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getUserListByCondition")
    public Result getUserListByCondition(@RequestBody(required = false) UserEntity params) {
        return Result.success(userService.selectUserListByCondition(params));
    }

    @ApiOperation(value = "根据ID查询用户信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getUserById")
    public Result getUserById(@RequestParam int Id) {
        UserEntity entity = userService.selectUserById(Id);
        if (NullUtil.isNotNull(entity)) {
            return Result.success(entity);
        } else {
            return Result.error("用户不存在");
        }
    }

    /**
     * 用户新增
     *
     * @param entity
     * @return
     */
    @ApiOperation(value = "新增用户", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("insert")
    public Result insert(@RequestBody UserEntity entity) {
        boolean isExists = userService.existsUserByUserAccount(entity.getUserAccount());
        if (isExists) {
            return new Result(ResponseCode.user_already_exists.getCode(), ResponseCode.user_already_exists.getMsg());
        }
        int id = userService.insert(entity);
        return Result.success(id);
    }

    @ApiOperation(value = "更新用户", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("update")
    public Result update(@RequestBody UserEntity entity) {
        return userService.update(entity);
    }

    @ApiOperation(value = "更新密码", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("updatePassword")
    public Result updatePassword(@RequestBody Map<String, String> params) {
        return userService.updatePassword(params);
    }

    @ApiOperation(value = "更新头像", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("updatePhoto")
    public Result updatePhoto(@RequestBody Map<String, String> params) {
        return userService.updatePhoto(params);
    }

    @ApiOperation(value = "删除用户", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        return userService.delete(params.get("Id"));
    }

    @ApiOperation(value = "新增用户组、权限与用户的关联关系", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("insertGroupAndPermission")
    public Result insertGroupAndPermission(@RequestBody Map<String, Object> params) {
        int UserId = NumberUtil.toInt(params.get("UserId") + "");
        List<Integer> mGroupIds = (List) params.get("GroupIds");
        List<Integer> mPerIds = (List) params.get("PerIds");
        return userService.insertGroupAndPermission(UserId, mGroupIds, mPerIds);
    }
}
