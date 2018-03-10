package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.dao.PermissionMenuMapper;
import com.coderfamily.lamj.model.PermissionMenuEntity;
import com.coderfamily.lamj.intef.IPermissionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/3 14:37
 */
@Service
@Transactional
public class PermissionMenuServiceImpl implements IPermissionMenuService {

    @Autowired
    private PermissionMenuMapper permissionMenuMapper;

    @Override
    public Result insert(int Id, List<Integer> mIds) {
        permissionMenuMapper.deleteByPermissionId(Id);
        if (mIds != null && mIds.size() > 0) {
            List<PermissionMenuEntity> permis = new ArrayList<>();
            mIds.forEach(item -> {
                PermissionMenuEntity entity = new PermissionMenuEntity();
                entity.setMenuId(item);
                entity.setPermissionId(Id);
                permis.add(entity);
            });
            if (permissionMenuMapper.insert(permis) > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        } else {
            return Result.success();
        }
    }

    @Override
    public Result delete(int MenuId, int PermissionId) {
        if (!existsRelat(MenuId, PermissionId)) {
            return Result.error("该菜单与权限的关系已被删除");
        } else if (permissionMenuMapper.delete(MenuId, PermissionId) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int deleteByMenuId(int MenuId) {
        return permissionMenuMapper.deleteByMenuId(MenuId);
    }

    @Override
    public boolean existsRelat(int MenuId, int PermissionId) {
        return permissionMenuMapper.existsRelat(MenuId, PermissionId);
    }

    @Override
    public boolean checkDeleteByMenuId(int MenuId) {
        return permissionMenuMapper.checkDeleteByMenuId(MenuId);
    }
}
