package com.coderfamily.lamj.controller;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.model.DictionaryEntity;
import com.coderfamily.lamj.service.IDictionaryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/2/2 16:22
 */
@RestController
@Api(value = "dictionary", description = "字典管理")
@RequestMapping(value = "api/dictionary")
public class DictionaryController {

    @Autowired
    private IDictionaryService dictionaryService;

    @ApiOperation(value = "获取字典列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getDictList")
    public Result getDictList(@RequestParam(required = false, defaultValue = "") String Name,
                              @RequestParam(required = false, defaultValue = "") String EnName,
                              @RequestParam(required = false) Boolean Status,
                              @RequestParam int PageSize,
                              @RequestParam int CurPage) {
        DictionaryEntity entity = new DictionaryEntity();
        if (NullUtil.isNotNull(Name)) {
            entity.setName(Name);
        }
        if (NullUtil.isNotNull(Status)) {
            entity.setStatus(Status);
        }
        if (NullUtil.isNotNull(EnName)) {
            entity.setEnName(EnName);
        }
        PageInfo<DictionaryEntity> pageInfo = dictionaryService.selectDictByPage(Name, EnName, Status, PageSize, CurPage);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "根据英文名称获取字典列表", httpMethod = "GET", produces = "application/json", response = Result.class)
    @GetMapping("getDictListByEnName")
    public Result getDictListByEnName(@RequestParam String EnName) {
        List<DictionaryEntity> mData = dictionaryService.selectDictByCondition(EnName);
        return Result.success(mData);
    }

    @ApiOperation(value = "新增字典值", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("insert")
    public Result insert(@RequestBody DictionaryEntity params) {
        Result result = Result.error();
        if (dictionaryService.insert(params) > 0) {
            result = Result.success();
        }
        return result;
    }

    @ApiOperation(value = "更新字典值", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("update")
    public Result update(@RequestBody DictionaryEntity params) {
        Result result = Result.error();
        if (dictionaryService.update(params) > 0) {
            result = Result.success();
        }
        return result;
    }

    @ApiOperation(value = "删除字典值", httpMethod = "POST", produces = "application/json", response = Result.class)
    @PostMapping("delete")
    public Result delete(@RequestBody Map<String, Integer> params) {
        Result result = Result.error();
        if (dictionaryService.delete(params.get("Id")) > 0) {
            result = Result.success();
        }
        return result;
    }
}
