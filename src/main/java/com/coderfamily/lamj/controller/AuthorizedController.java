package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.ResponseCode;
import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.PasUtil;
import com.coderfamily.lamj.common.util.StringUtil;
import com.coderfamily.lamj.common.util.TokenUtil;
import com.coderfamily.lamj.domain.UserDetail;
import com.coderfamily.lamj.domain.UserInfo;
import com.coderfamily.lamj.intef.ICompanyService;
import com.coderfamily.lamj.intef.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/1/26 13:37
 */
@RestController
@Api(value = "Token授权", description = "系统登陆授权控制器")
@RequestMapping(value = "api/auth")
public class AuthorizedController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICompanyService companyService;

    /**
     * 登陆授权
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "登陆授权", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("login")
    public Result login(@RequestBody Map<String, Object> param) {
        String UserAccount = StringUtil.toStr(param.get("UserAccount"));
        String Password = PasUtil.createPassword(StringUtil.toStr(param.get("Password")));
        UserDetail user = userService.selectUserByUserAccount(UserAccount);
        UserInfo userInfo = new UserInfo();
        if (user == null) {
            return Result.init(ResponseCode.unknown_account.getCode(), ResponseCode.unknown_account.getMsg());
        } else if (!Password.equals(user.getPassword())) {
            return Result.init(ResponseCode.password_incorrect.getCode(), ResponseCode.password_incorrect.getMsg());
        } else if (user.getTypeCode().equals("0002") && companyService.isExpiredCompanyByUserId(user.getId())) {//如果是业务用户，需要判断一下是否已经超过有效期
            return Result.init(ResponseCode.expired.getCode(), ResponseCode.expired.getMsg());
        } else if (!user.getStatus()) {
            return Result.init(ResponseCode.user_stop.getCode(), ResponseCode.user_stop.getMsg());
        } else {
            userService.updateLastLoginTime(user.getId());
            userInfo.setUser(user);
            userInfo.setCompany(companyService.getCompanyDetailByUserId(user.getId()));
            userInfo.setToken(TokenUtil.sign(UserAccount, Password));
        }
        return Result.success(userInfo);
    }
}
