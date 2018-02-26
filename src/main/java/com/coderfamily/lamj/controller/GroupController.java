package com.coderfamily.lamj.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhangDL
 * @date 2018/2/2 16:22
 */
@Controller
@Api(value = "group",description = "用户分组管理")
@RequestMapping(value = "api/group")
public class GroupController {
}
