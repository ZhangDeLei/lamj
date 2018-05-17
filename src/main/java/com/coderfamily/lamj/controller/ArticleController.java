package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.IArticleService;
import com.coderfamily.lamj.model.ArticleEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/3/14 15:25
 */
@Api(value = "article", description = "网评文章管理")
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;


    @ApiOperation(value = "获取网评文章列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getArticleList")
    public Result getArticleList(@RequestParam int CompanyId,
                                 @RequestParam(required = false) String Title,
                                 @RequestParam(required = false) Integer UserId,
                                 @RequestParam(required = false) Integer TypeId,
                                 @RequestParam(required = false) Integer LevelId,
                                 @RequestParam int PageSize,
                                 @RequestParam int CurPage) {
        return Result.success(articleService.getArticleList(CompanyId, Title, UserId, TypeId, LevelId, PageSize, CurPage));
    }

    @ApiOperation(value = "获取网评文章列表(App接口)", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getArticleListForApp")
    public Result getArticleListForApp(@RequestParam int CompanyId,
                                       @RequestParam(required = false) String Title,
                                       @RequestParam int LevelId,
                                       @RequestParam int PageSize,
                                       @RequestParam int CurPage) {
        return Result.success(articleService.getArticleListForApp(CompanyId, Title, LevelId, PageSize, CurPage));
    }

    @ApiOperation(value = "获取网评网站合集列表(App接口)", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getArticleTypeForApp")
    public Result getArticleTypeForApp(@RequestParam int CompanyId) {
        return Result.success(articleService.getArticleTypeForApp(CompanyId));
    }

    @ApiOperation(value = "获取个人网评文章列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getArticleListByUserId")
    public Result getArticleListByUserId(@RequestParam int CompanyId,
                                         @RequestParam(required = false) String Title,
                                         @RequestParam Integer UserId,
                                         @RequestParam(required = false) Integer TypeId,
                                         @RequestParam(required = false) Integer LevelId,
                                         @RequestParam int PageSize,
                                         @RequestParam int CurPage) {
        return Result.success(articleService.getArticleList(CompanyId, Title, UserId, TypeId, LevelId, PageSize, CurPage));
    }

    @ApiOperation(value = "根据ID获取网评文章详细信息", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getArticleById")
    public Result getArticleById(@RequestParam int Id) {
        return Result.success(articleService.getArticleById(Id));
    }

    @ApiOperation(value = "新增网评文章", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody ArticleEntity entity) {
        return articleService.insert(entity);
    }

    @ApiOperation(value = "更新网评文章", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody ArticleEntity entity) {
        return articleService.update(entity);
    }

    @ApiOperation(value = "删除网评文章", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        return articleService.delete(params.get("Id"));
    }
}
