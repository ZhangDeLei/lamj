package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.ResponseCode;
import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.PasUtil;
import com.coderfamily.lamj.model.UserEntity;
import com.coderfamily.lamj.service.IUserService;
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
    public Result getUserListByCondition(@RequestBody(required = false) UserEntity params) {
        return Result.success(userService.selectUserListByCondition(params));
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

        UserEntity user = new UserEntity();
        user.setUserAccount(entity.getUserAccount());
        user.setNickName(entity.getNickName());
        user.setSex(entity.getSex());
        user.setPassword(PasUtil.createPassword(entity.getPassword()));
        user.setPhotoUrl(entity.getPhotoUrl());
        user.setTel(entity.getTel());
        int id = userService.insert(user);
        return Result.success(id);
    }

    @ApiOperation(value = "删除用户",httpMethod = "POST",produces = "application/json",response = Result.class)
    @ResponseBody
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String,Integer> params){
        return userService.delete(params.get("Id"));
    }
}
