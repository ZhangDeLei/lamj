package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.ResponseCode;
import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.domain.UserDetail;
import com.coderfamily.lamj.model.UserEntity;
import com.coderfamily.lamj.intef.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Result getUserListByCondition(@RequestParam(required = false) String Name,
                                         @RequestParam(required = false, defaultValue = "-1") int StarLevelId,
                                         @RequestParam(required = false, defaultValue = "-1") int TypeId,
                                         @RequestParam(required = false) Boolean Status,
                                         @RequestParam(required = false) String Tel,
                                         @RequestParam(required = false, defaultValue = "-1") int Sex,
                                         @RequestParam(required = false) String UserAccount,
                                         @RequestParam(required = false, defaultValue = "-1") int CompanyId,
                                         @RequestParam(required = false, defaultValue = "-1") int TeamId,
                                         @RequestParam(required = false, defaultValue = "10") int PageSize,
                                         @RequestParam(required = false, defaultValue = "1") int CurPage) {
        return Result.success(userService.selectUserListByCondition(Name, UserAccount, Tel, StarLevelId, TypeId, Status,
                Sex, CompanyId, TeamId, PageSize, CurPage));
    }

    @ApiOperation(value = "获取所有用户列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getAllUser")
    public Result getAllUser() {
        return Result.success(userService.selectAllUser());
    }

    @ApiOperation(value = "获取企业的所有用户列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @ResponseBody
    @GetMapping("getAllUserListByCompanyId")
    public Result getAllUserListByCompanyId(@RequestParam int CompanyId) {
        return Result.success(userService.selectAllUserByCompanyId(CompanyId));
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
    public Result insert(@RequestBody UserDetail entity) {
        boolean isExists = userService.existsUserByUserAccount(entity.getUserAccount());
        if (isExists) {
            return new Result(ResponseCode.user_already_exists.getCode(), ResponseCode.user_already_exists.getMsg());
        }
        return userService.insert(entity, false);
    }

    /**
     * 用户新增(用户业务账户新增)
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "新增用户", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("insertCustom")
    public Result insertCustom(@RequestBody UserDetail user) {
        boolean isExists = userService.existsUserByUserAccount(user.getUserAccount());
        if (isExists) {
            return new Result(ResponseCode.user_already_exists.getCode(), ResponseCode.user_already_exists.getMsg());
        }
        return userService.insert(user, true);
    }

    @ApiOperation(value = "更新用户", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("update")
    public Result update(@RequestBody UserDetail entity) {
        return userService.update(entity, false);
    }

    @ApiOperation(value = "更新用户", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("updateCustom")
    public Result updateCustom(@RequestBody UserDetail entity) {
        return userService.update(entity, true);
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
}
