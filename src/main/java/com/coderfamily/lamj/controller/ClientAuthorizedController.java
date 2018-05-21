package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端授权
 */
@Api(value = "客户端授权接口", description = "客户端授权")
@RestController
@RequestMapping(value = "/api/clientAuth", headers = "version=1")
public class ClientAuthorizedController {

    @ApiOperation(value = "get接口测试", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getTest")
    public Result getTest(@RequestParam String Url) {
        String result = HttpUtil.get(Url, null);
        return Result.success(result);
    }
}
