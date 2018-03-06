package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.CompanyEntity;
import com.coderfamily.lamj.service.ICompanyService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 企业管理控制器
 *
 * @author ZhangDL
 * @date 2018/3/3 15:15
 */
@Api(description = "企业管理",value = "company")
@RestController
@RequestMapping(value = "/api/company", headers = "version=1")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @ApiOperation(value = "获取企业列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getCompanyList")
    public Result getCompanyList(@RequestParam(required = false) String Name,
                                 @RequestParam(required = false) String BegDate,
                                 @RequestParam(required = false) String EndDate,
                                 @RequestParam int PageSize,
                                 @RequestParam int CurPage) {
        PageInfo<CompanyEntity> pageInfo = companyService.getCompanyList(Name, BegDate, EndDate, PageSize, CurPage);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "获取所有的企业列表",httpMethod = "GET",produces = "application/json",response = Result.class)
    @GetMapping("getAllCompanyList")
    public Result getAllCompanyList(){
        return Result.success(companyService.getCompanyListByCondition(new CompanyEntity()));
    }

    @ApiOperation(value = "根据ID获取企业信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getCompanyById")
    public Result getCompanyById(@RequestParam int Id) {
        return Result.success(companyService.getCompanyById(Id));
    }

    @ApiOperation(value = "新增企业", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody CompanyEntity params) {
        return companyService.insert(params);
    }

    @ApiOperation(value = "修改企业", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody CompanyEntity params) {
        return companyService.update(params);
    }

    @ApiOperation(value = "删除企业", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        return companyService.delete(params.get("Id"));
    }
}
