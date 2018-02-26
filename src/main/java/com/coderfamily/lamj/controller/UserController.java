package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.ResponseCode;
import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.PasUtil;
import com.coderfamily.lamj.model.UserEntity;
import com.coderfamily.lamj.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 用户新增
     * @param UserAccount
     * @param Password
     * @param NickName
     * @param Tel
     * @param Sex
     * @param PhotoUrl
     * @return
     */
    @ApiOperation(value = "新增用户", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(@RequestParam String UserAccount,
                         @RequestParam String Password,
                         @RequestParam String NickName,
                         @RequestParam String Tel,
                         @RequestParam(defaultValue = "1", required = false) int Sex,
                         @RequestParam(defaultValue = "", required = false) String PhotoUrl) {
        boolean isExists = userService.existsUserByUserAccount(UserAccount);
        if (isExists) {
            return new Result(ResponseCode.user_already_exists.getCode(), ResponseCode.user_already_exists.getMsg());
        }

        UserEntity user = new UserEntity();
        user.setUserAccount(UserAccount);
        user.setNickName(NickName);
        user.setSex(Sex);
        user.setPassword(PasUtil.createPassword(Password));
        user.setPhotoUrl(PhotoUrl);
        user.setTel(Tel);
        int id = userService.insert(user);
        return Result.success(id);
    }
}
