package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.UserNewAuthEntity;
import com.coderfamily.lamj.intef.IUserNewAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:54
 */
@Api(value = "userNewAuth", description = "用户新闻客户端授权管理")
@RestController
@RequestMapping(value = "/api/userNewAuth", headers = "version=1")
public class UserNewAuthController {
    @Autowired
    private IUserNewAuthService userNewAuthService;

    @ApiOperation(value = "根据用户ID获取授权列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getUserNewAuthList")
    public Result getUserNewAuthList(@RequestParam int CompanyId, @RequestParam int UserId) {
        return Result.success(userNewAuthService.getUserNewAuthList(CompanyId, UserId));
    }

    @ApiOperation(value = "新增用户授权信息", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody UserNewAuthEntity entity) {
        return userNewAuthService.insert(entity);
    }

    @ApiOperation(value = "更新用户授权信息", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody UserNewAuthEntity entity) {
        return userNewAuthService.update(entity);
    }
}
