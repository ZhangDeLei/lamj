package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.intef.IArticleLevelService;
import com.coderfamily.lamj.model.ArticleLevelEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/4/26 14:51
 */
@Api(value = "网评级别", description = "网评级别管理")
@RestController
@RequestMapping(value = "api/articleLevel")
public class ArticleLevelController {

    @Autowired
    private IArticleLevelService articleLevelService;

    @ApiOperation(value = "获取文章级别列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getArticleLevelList")
    public Result getArticleLevelList(@RequestParam int CompanyId,
                                      @RequestParam(required = false) String Name,
                                      @RequestParam(required = false) Boolean Status) {
        return Result.success(articleLevelService.getArticleLevelList(CompanyId, Name, Status));
    }

    @ApiOperation(value = "新增文章级别", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody ArticleLevelEntity param) {
        return articleLevelService.insert(param);
    }

    @ApiOperation(value = "删除文章级别", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> param) {
        return articleLevelService.delete(param.get("Id"));
    }

    @ApiOperation(value = "更新文章级别", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody ArticleLevelEntity param) {
        return articleLevelService.update(param);
    }

}
