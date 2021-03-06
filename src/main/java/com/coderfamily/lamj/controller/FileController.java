package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangDL
 * @date 2018/3/1 18:40
 */
@RestController
@Api(value = "File文件控制器", description = "用于文件的上传下载")
@RequestMapping(value = "/api/file", headers = "version=1")
public class FileController {

    @Autowired
    private IFileService fileService;

    @ApiOperation(value = "文件上传", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping(value = "fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result fileUpload(@RequestParam MultipartFile file, @RequestParam String type, HttpServletRequest request) {
        return fileService.fileUpload(file, type, request);
    }

    @ApiOperation(value = "根据文件名称删除文件", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("deleteFile")
    public Result deleteFile(@RequestParam String filePath) {
        boolean isSuccess = fileService.deleteFile(filePath);
        return isSuccess ? Result.success() : Result.error();
    }

//    @ApiOperation(value = "文件下载", httpMethod = "GET", produces = "application/json", response = Result.class)
//    @GetMapping("download")
//    public Result download(@RequestParam String Path, HttpServletRequest request) {
//
//    }
}
