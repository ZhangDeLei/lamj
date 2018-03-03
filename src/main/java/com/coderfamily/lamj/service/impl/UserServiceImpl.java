package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.common.util.PasUtil;
import com.coderfamily.lamj.dao.UserMapper;
import com.coderfamily.lamj.model.UserEntity;
import com.coderfamily.lamj.model.UserGroupEntity;
import com.coderfamily.lamj.model.UserPermissionEntity;
import com.coderfamily.lamj.service.IFileService;
import com.coderfamily.lamj.service.IGroupService;
import com.coderfamily.lamj.service.IPermissionService;
import com.coderfamily.lamj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangDL
 * @date 2018/1/25 16:13
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IFileService fileService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IGroupService groupService;

    @Override
    public List<UserEntity> selectUserListByCondition(UserEntity entity) {
        return userMapper.selectUserListByCondition(entity);
    }

    @Override
    public UserEntity selectUserByUserAccount(String UserAccount) {
        return userMapper.selectUserByUserAccount(UserAccount);
    }

    @Override
    public UserEntity selectUserById(int Id) {
        return userMapper.selectUserById(Id);
    }

    @Override
    public UserEntity login(String UserAccount, String Password) {
        return userMapper.login(UserAccount, Password);
    }

    @Override
    public int insert(UserEntity userEntity) {
        return userMapper.insert(userEntity);
    }

    @Override
    public Result insertGroupAndPermission(int UserId, List<Integer> GroupIds, List<Integer> PerIds) {
        permissionService.deleteUserRelation(UserId);
        groupService.deleteGroupRelation(UserId);
        List<UserGroupEntity> groupList = new ArrayList<>();
        List<UserPermissionEntity> perList = new ArrayList<>();
        boolean isSuccess = false;
        if (GroupIds != null && GroupIds.size() > 0) {
            GroupIds.forEach(t -> {
                UserGroupEntity entity = new UserGroupEntity();
                entity.setUserId(UserId);
                entity.setGroupId(t);
                groupList.add(entity);
            });
            isSuccess = groupService.insertUserGroup(groupList);
        }

        if (PerIds != null && PerIds.size() > 0) {
            PerIds.forEach(t -> {
                UserPermissionEntity entity = new UserPermissionEntity();
                entity.setUserId(UserId);
                entity.setPermissionId(t);
                perList.add(entity);
            });
            isSuccess = permissionService.insertUserRelation(perList);
        }
        if (isSuccess) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public boolean existsUserByUserAccount(String UserAccount) {
        return userMapper.existsUserByUserAccount(UserAccount);
    }

    @Override
    public boolean existsUserRelationGroup(int UserId, int GroupId) {
        return userMapper.existsUserRelationGroup(UserId, GroupId);
    }

    @Override
    public boolean existsUserRelationPermission(int UserId, int PermissionId) {
        return userMapper.existsUserRelationPermission(UserId, PermissionId);
    }

    @Override
    public Result update(UserEntity userEntity) {
        UserEntity entity = selectUserById(userEntity.getId());
        if (NullUtil.isNull(entity)) {
            return Result.error("用户不存在");
        } else {
            entity.setTel(userEntity.getTel());
            entity.setNickName(userEntity.getNickName());
            entity.setId(userEntity.getId());
            entity.setSex(userEntity.getSex());
            entity.setStatus(userEntity.getStatus());
            entity.setUserAccount(userEntity.getUserAccount());
            if (userMapper.update(entity) > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        }
    }

    @Override
    public Result updatePassword(Map<String, String> params) {
        UserEntity entity = selectUserById(NumberUtil.toInt(params.get("Id")));
        if (NullUtil.isNull(entity)) {
            return Result.error("用户不存在");
        } else if (NullUtil.isNull(params.get("password"))) {
            return Result.error("密码不能为空");
        } else {
            entity.setPassword(PasUtil.createPassword(params.get("password")));
            if (userMapper.update(entity) > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        }
    }

    @Override
    public Result updatePhoto(Map<String, String> params) {
        UserEntity entity = selectUserById(NumberUtil.toInt(params.get("Id")));
        if (NullUtil.isNull(entity)) {
            return Result.error("用户不存在");
        } else {
            entity.setPhotoUrl(params.get("path"));
            if (userMapper.update(entity) > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        }
    }

    @Override
    public Result delete(int Id) {
        UserEntity entity = userMapper.selectUserById(Id);
        if (NullUtil.isNull(entity)) {
            return Result.error("用户不存在");
        } else {
            //删除用户时，需要同步删除用户关联的分组以及权限
            if (userMapper.delete(Id) >= 0 && groupService.deleteGroupRelation(Id) >= 0 && permissionService.deleteUserRelation(Id) >= 0) {
                if (NullUtil.isNotNull(entity.getPhotoUrl())) {
                    fileService.deleteFile(entity.getPhotoUrl());
                }
                return Result.success();
            } else {
                return Result.error();
            }
        }
    }
}
