package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.model.CompanyEntity;
import com.coderfamily.lamj.intef.ICompanyService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 企业管理控制器
 *
 * @author ZhangDL
 * @date 2018/3/3 15:15
 */
@Api(description = "企业管理", value = "company")
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

    @ApiOperation(value = "获取所有的企业列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getAllCompanyList")
    public Result getAllCompanyList() {
        return Result.success(companyService.getCompanyListByCondition(new CompanyEntity()));
    }

    @ApiOperation(value = "获取快要超过有效期的企业（前10）", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getCompanyByExpired")
    public Result getCompanyByExpired() {
        return Result.success(companyService.getCompanyByExpired());
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

    @ApiOperation(value = "新增企业新闻客户端", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insertCompanyNew")
    public Result insertCompanyNew(@RequestBody Map<String, Object> params) {
        int CompanyId = NumberUtil.toInt(params.get("CompanyId") + "");
        List<Integer> Ids = (List) params.get("Ids");
        return companyService.insertCompanyNew(CompanyId, Ids);
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

    @ApiOperation(value = "根据企业ID和客户端ID删除新闻与企业的关联关系", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("deleteCompanyNewByCompanyIdAndNewId")
    public Result deleteCompanyNewByCompanyIdAndNewId(@RequestBody Map<String, Integer> params) {
        int CompanyId = params.get("CompanyId");
        int NewId = params.get("NewId");
        return Result.success(companyService.deleteCompanyNewByCompanyIdAndNewId(CompanyId, NewId));
    }
}
