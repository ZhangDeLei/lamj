package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.dao.UserMapper;
import com.coderfamily.lamj.model.UserEntity;
import com.coderfamily.lamj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangDL
 * @date 2018/1/25 16:13
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity selectUserByUserAccount(String UserAccount) {
        return userMapper.selectUserByUserAccount(UserAccount);
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
    public int insertGroupRelation(int UserId, int GroupId) {
        return userMapper.insertGroupRelation(UserId, GroupId);
    }

    @Override
    public int insertPermissionRelation(int UserId, int PermissionId) {
        return userMapper.insertPermissionRelation(UserId, PermissionId);
    }

    @Override
    public int update(UserEntity userEntity) {
        return userMapper.update(userEntity);
    }

    @Override
    public int delete(int Id) {
        return userMapper.delete(Id);
    }

    @Override
    public int deleteGroupRelation(int UserId, int GroupId) {
        return userMapper.deleteGroupRelation(UserId, GroupId);
    }

    @Override
    public int deletePermissionRelation(int UserId, int PermissionId) {
        return userMapper.deletePermissionRelation(UserId, PermissionId);
    }
}
