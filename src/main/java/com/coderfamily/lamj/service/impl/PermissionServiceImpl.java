package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.common.util.StringUtil;
import com.coderfamily.lamj.dao.PermissionMapper;
import com.coderfamily.lamj.model.PermissionEntity;
import com.coderfamily.lamj.service.IPermissionService;
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
    public List<PermissionEntity> selectPermissionByCondition(PermissionEntity permissionEntity) {
        return permissionMapper.selectPermissionByCondition(permissionEntity);
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
    public int insertUserRelation(int UserId, int PermissionId) {
        return permissionMapper.insertUserRelation(UserId, PermissionId);
    }

    @Override
    public int insertGroupRelation(int GroupId, int PermissionId) {
        return permissionMapper.insertGroupRelation(GroupId, PermissionId);
    }

    @Override
    public int update(PermissionEntity permissionEntity) {
        return permissionMapper.update(permissionEntity);
    }

    @Override
    public int delete(int Id) {
        return permissionMapper.delete(Id);
    }

    @Override
    public int deleteUserRelation(int UserId, int PermissionId) {
        return permissionMapper.deleteUserRelation(UserId, PermissionId);
    }

    @Override
    public int deleteGroupRelation(int GroupId, int PermissionId) {
        return permissionMapper.deleteGroupRelation(GroupId, PermissionId);
    }

    private String getMaxCode() {
        String maxCode = permissionMapper.selectPermissionCodeForMax();
        int code = NumberUtil.toInt(maxCode) + 1;
        return NumberUtil.seats(code, maxCode.length());
    }
}
