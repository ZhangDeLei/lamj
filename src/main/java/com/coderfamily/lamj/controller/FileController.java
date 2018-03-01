package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZhangDL
 * @date 2018/3/1 18:40
 */
@Controller
@Api(value = "File文件控制器", description = "用于文件的上传下载")
@RequestMapping(value = "/api/file", headers = "version=1")
@CrossOrigin
public class FileController {
    @ApiOperation(value = "文件上传", httpMethod = "POST", produces = "application/json", response = Result.class)
    @ResponseBody
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) {
        return Result.success();
    }
}
