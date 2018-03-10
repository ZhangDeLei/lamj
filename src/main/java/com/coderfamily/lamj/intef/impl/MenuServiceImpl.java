package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.dao.MenuMapper;
import com.coderfamily.lamj.domain.MenuInfo;
import com.coderfamily.lamj.model.MenuEntity;
import com.coderfamily.lamj.intef.IMenuService;
import com.coderfamily.lamj.intef.IPermissionMenuService;
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
    public List<MenuInfo> selectPermissionMenuByTree(int PermissionId) {
        return menuMapper.selectPermissionMenuByTree(PermissionId);
    }

    @Override
    public List<MenuEntity> selectMenuByParentId(int ParentId) {
        return menuMapper.selectMenuByParentId(ParentId);
    }

    @Override
    public boolean selectHasChildMenu(int Id) {
        return menuMapper.selectHasChildMenu(Id);
    }

    @Override
    public MenuEntity selectMenubyId(int Id) {
        return menuMapper.selectMenuById(Id);
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
    public Result update(MenuEntity menuEntity) {
        MenuEntity existsMenu = menuMapper.selectMenuById(menuEntity.getId());
        //判断是否已经修改过父节点，如果修改了，需要重置code
        if (isUpdateParentId(menuEntity)) {
            menuEntity.setCode(getInsertMenuCode(menuEntity));
        }
        if (NullUtil.isNull(menuEntity.getId())) {
            return Result.error("需要修改的菜单ID为空");
        } else if (NullUtil.isNull(existsMenu)) {
            return Result.error("需要修改的菜单不存在");
        } else if (menuMapper.update(menuEntity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result delete(int Id) {
        if (menuMapper.selectHasChildMenu(Id)) {
            return Result.error("当前菜单存在子菜单，请先删除子菜单");
        } else if (permissionMenuService.checkDeleteByMenuId(Id)) {
            return Result.error("当前菜单已关联权限，请先取消权限后再删除");
        } else if (NullUtil.isNull(menuMapper.selectMenuById(Id))) {
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
        //如果当前的maxcode为空并且parentid=0，则表示第一次新增菜单
        if (NullUtil.isNull(maxCode) && menu.getParentId() == 0) {
            maxCode = "0000";
            //如果当前maxcode为空，并且parentid>0则表示第一次新增二级菜单
        } else if (NullUtil.isNull(maxCode) && menu.getParentId() > 0) {
            MenuEntity parentMenu = menuMapper.selectMenuById(menu.getParentId());
            maxCode = parentMenu.getCode() + "00";
        }
        int curCode = NumberUtil.toInt(maxCode) + 1;
        return NumberUtil.seats(curCode, maxCode.length());
    }

    /**
     * 判断是否是修改过父节点
     *
     * @param menu
     * @return
     */
    private boolean isUpdateParentId(MenuEntity menu) {
        boolean isUpdate = false;
        MenuEntity menuEntity = selectMenubyId(menu.getId());
        if (menu.getParentId() != menuEntity.getParentId()) {
            isUpdate = true;
        }
        return isUpdate;
    }
}
