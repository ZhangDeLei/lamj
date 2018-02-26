package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.dao.MenuMapper;
import com.coderfamily.lamj.domain.MenuInfo;
import com.coderfamily.lamj.model.MenuEntity;
import com.coderfamily.lamj.model.PermissionMenuEntity;
import com.coderfamily.lamj.service.IMenuService;
import com.coderfamily.lamj.service.IPermissionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 13:51
 */
@Service
@Transactional
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private IPermissionMenuService permissionMenuService;

    @Override
    public List<MenuInfo> selectMenuByUserId(int UserId) {
        return menuMapper.selectMenuByUserId(UserId);
    }

    @Override
    public List<MenuEntity> selectMenuByCondition(MenuEntity menuEntity) {
        return menuMapper.selectMenuByCondition(menuEntity);
    }

    @Override
    public List<MenuEntity> selectMenuByPermissionId(int PermissionId) {
        return menuMapper.selectMenuByPermissionId(PermissionId);
    }

    @Override
    public List<MenuInfo> selectMenuByTree() {
        return menuMapper.selectMenuByTree();
    }

    @Override
    public List<MenuEntity> selectMenuByParentId(int ParentId) {
        return menuMapper.selectMenuByParentId(ParentId);
    }

    @Override
    public Result insert(MenuEntity menuEntity) {
        if (menuMapper.existsMenuByName(menuEntity.getName())) {
            return Result.error("该菜单名称已存在");
        }
        //设置新增菜单时需要填充的code信息
        menuEntity.setCode(getInsertMenuCode(menuEntity));
        if (menuMapper.insert(menuEntity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result insertPermissionRelation(PermissionMenuEntity permissionMenuEntity) {
        return permissionMenuService.insert(permissionMenuEntity);
    }

    @Override
    public Result update(MenuEntity menuEntity) {
        if (NullUtil.isNull(menuEntity.getId())) {
            return Result.error("需要修改的菜单ID为空");
        } else if (NullUtil.isNull(menuMapper.selectMenuById(menuEntity.getId()))) {
            return Result.error("需要修改的菜单不存在");
        } else if (menuMapper.update(menuEntity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        if (NullUtil.isNull(menuMapper.selectMenuById(Id))) {
            return Result.error("需要删除的菜单已被删除");
        } else if (menuMapper.delete(Id) > 0) {
            //删除菜单时，需要同步删除菜单与所有权限的关联关系
            permissionMenuService.deleteByMenuId(Id);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result deletePermissionRelation(int PermissionId, int MenuId) {
        return permissionMenuService.delete(MenuId, PermissionId);
    }

    /**
     * 获取当前新增菜单时需要填充的code
     *
     * @return
     */
    private String getInsertMenuCode(MenuEntity menu) {
        String maxCode = menuMapper.selectMenuCodeToMaxByParentId(menu.getParentId());
        int curCode = NumberUtil.toInt(maxCode) + 1;
        return NumberUtil.seats(curCode, maxCode.length());
    }
}
