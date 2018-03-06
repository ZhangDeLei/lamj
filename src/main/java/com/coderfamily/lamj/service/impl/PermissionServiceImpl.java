package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.common.util.StringUtil;
import com.coderfamily.lamj.dao.PermissionMapper;
import com.coderfamily.lamj.model.GroupPermissionEntity;
import com.coderfamily.lamj.model.PermissionEntity;
import com.coderfamily.lamj.model.UserPermissionEntity;
import com.coderfamily.lamj.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/2/2 13:51
 */
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionEntity> selectPermissionByUserId(int UserId) {
        return permissionMapper.selectPermissionByUserId(UserId);
    }

    @Override
    public List<PermissionEntity> selectPermissionByGroupId(int GroupId) {
        return permissionMapper.selectPermissionByGroupId(GroupId);
    }

    @Override
    public PageInfo<PermissionEntity> selectPermissionByPage(String Name, int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        PermissionEntity entity = new PermissionEntity();
        entity.setName(Name);
        List<PermissionEntity> mData = permissionMapper.selectPermissionByCondition(entity);
        return new PageInfo<>(mData);
    }

    @Override
    public List<PermissionEntity> selectPermissionByCondition(PermissionEntity entity) {
        return permissionMapper.selectPermissionByCondition(entity);
    }

    @Override
    public boolean existsPermissionByName(String Name) {
        return permissionMapper.existsPermissionByName(Name);
    }

    @Override
    public Result insert(PermissionEntity permissionEntity) {
        permissionEntity.setCode(getMaxCode());
        if (permissionMapper.existsPermissionByName(permissionEntity.getName())) {
            return Result.error("当前权限名称已存在");
        } else if (permissionMapper.insert(permissionEntity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result insertUserRelation(int Id, List<Integer> mIds) {
        deleteUserRelation(Id);
        if (mIds != null && mIds.size() > 0) {
            List<UserPermissionEntity> entities = new ArrayList<>();
            mIds.forEach(item -> {
                UserPermissionEntity entity = new UserPermissionEntity();
                entity.setUserId(Id);
                entity.setPermissionId(item);
                entities.add(entity);
            });
            if (permissionMapper.insertUserRelation(entities) > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        } else {
            return Result.success();
        }
    }

    @Override
    public boolean insertUserRelation(List<UserPermissionEntity> mList) {
        return permissionMapper.insertUserRelation(mList) > 0;
    }

    @Override
    public Result insertGroupRelation(int Id, List<Integer> mIds) {
        deleteGroupRelation(Id);
        if (mIds != null && mIds.size() > 0) {
            List<GroupPermissionEntity> entities = new ArrayList<>();
            mIds.forEach(item -> {
                GroupPermissionEntity entity = new GroupPermissionEntity();
                entity.setGroupId(Id);
                entity.setPermissionId(item);
                entities.add(entity);
            });
            if (permissionMapper.insertGroupRelation(entities) > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        } else {
            return Result.success();
        }
    }

    @Override
    public int update(PermissionEntity permissionEntity) {
        return permissionMapper.update(permissionEntity);
    }

    @Override
    public Result delete(int Id) {
        if (permissionMapper.existsPermissionUserRelat(Id)) {
            return Result.error("当前权限已被用户关联，请移除后再删除");
        }
        if (permissionMapper.existsPermissionUserGroupRelat(Id)) {
            return Result.error("当前权限已被用户组关联，请移除后再删除");
        }
        if (permissionMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int deleteUserRelation(int UserId) {
        return permissionMapper.deleteUserRelation(UserId);
    }

    @Override
    public int deleteGroupRelation(int GroupId) {
        return permissionMapper.deleteGroupRelation(GroupId);
    }

    private String getMaxCode() {
        String maxCode = permissionMapper.selectPermissionCodeForMax();
        int code = NumberUtil.toInt(maxCode) + 1;
        return NumberUtil.seats(code, maxCode.length());
    }
}
