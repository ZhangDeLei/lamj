package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.common.util.PasUtil;
import com.coderfamily.lamj.common.util.TimeUtils;
import com.coderfamily.lamj.dao.UserMapper;
import com.coderfamily.lamj.domain.UserDetail;
import com.coderfamily.lamj.model.*;
import com.coderfamily.lamj.intef.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private ITeamService teamService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private IUserNewAuthService userNewAuthService;

    @Override
    public PageInfo<UserDetail> selectUserListByCondition(String Name, String UserAccount, String Tel, int StarLevelId,
                                                        int TypeId, Boolean Status, int Sex, int CompanyId, int TeamId
            , int PageSize, int CurPage) {
        PageHelper.startPage(CurPage, PageSize);
        UserDetail entity = new UserDetail();
        entity.setNickName(Name);
        entity.setUserAccount(UserAccount);
        entity.setTel(Tel);
        entity.setStatus(Status);
        entity.setStarLevelId(StarLevelId);
        entity.setTypeId(TypeId);
        entity.setSex(Sex);
        entity.setCompanyId(CompanyId);
        entity.setTeamId(TeamId);
        List<UserDetail> mData = userMapper.selectUserListByCondition(entity);
        PageInfo<UserDetail> pageInfo = new PageInfo<>(mData);
        return pageInfo;
    }


    @Override
    public List<UserDetail> selectAllUser() {
        return userMapper.selectUserListByCondition(new UserDetail());
    }

    @Override
    public UserDetail selectUserByUserAccount(String UserAccount) {
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
    public Result insert(UserDetail userEntity, boolean isCustom) {
        userEntity.setPassword(PasUtil.createPassword(userEntity.getPassword()));

        if (isCustom) {
            DictionaryEntity dict = dictionaryService.DictInfo("Type", "0002");
            userEntity.setTypeCode(dict.getCode());
            userEntity.setTypeId(dict.getId());
            userEntity.setTypeName(dict.getLabel());
        }
        //如果是业务用户添加需要判断是否超过用户数
        if (userEntity.getTypeCode().equals("0002") && companyService.existsCompanyByMaxNum(userEntity.getCompanyId())) {
            return Result.error("当前用户数已经达到上限");
        }
        userMapper.insert(userEntity);
        if (userEntity.getPermissionId() > 0) {
            UserPermissionEntity userPermissionEntity = new UserPermissionEntity();
            userPermissionEntity.setPermissionId(userEntity.getPermissionId());
            userPermissionEntity.setUserId(userEntity.getId());
            List<UserPermissionEntity> mData = new ArrayList<>();
            mData.add(userPermissionEntity);
            permissionService.insertUserRelation(mData);
        }
        insertTeamRelat(userEntity.getTypeCode(), userEntity.getTeamId(), userEntity.getId());
        insertCompanyRelat(userEntity.getTypeCode(), userEntity.getCompanyId(), userEntity.getId());
        return Result.success(userEntity.getId());
    }

    @Override
    public boolean existsUserByUserAccount(String UserAccount) {
        return userMapper.existsUserByUserAccount(UserAccount);
    }

    @Override
    public boolean existsUserRelationPermission(int UserId, int PermissionId) {
        return userMapper.existsUserRelationPermission(UserId, PermissionId);
    }

    @Override
    public Result update(UserDetail userEntity, boolean isCustome) {
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
            if (!isCustome) {
                entity.setTypeId(userEntity.getTypeId());
                entity.setTypeCode(userEntity.getTypeCode());
                entity.setTypeName(userEntity.getTypeName());
            }
            entity.setStarLevelId(userEntity.getStarLevelId());
            entity.setStarLevelCode(userEntity.getStarLevelCode());
            entity.setStarLevelName(userEntity.getStarLevelName());
            //当type业务被改为系统时需要删除对应的业务表关联关系
            if (entity.getTypeCode().equals("0002") && !userEntity.getTypeCode().equals(entity.getTypeCode())) {
                companyService.deleteCompanyUserByUserId(userEntity.getId());
                teamService.deleteTeamUserByUser(userEntity.getId());
            } else {
                insertTeamRelat(entity.getTypeCode(), userEntity.getTeamId(), userEntity.getId());
                insertCompanyRelat(entity.getTypeCode(), userEntity.getCompanyId(), userEntity.getId());
            }
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
    public int updateLastLoginTime(int UserId) {
        UserEntity entity = new UserEntity();
        entity.setId(UserId);
        entity.setLastLoginTime(TimeUtils.getCurrentDate());
        return userMapper.update(entity);
    }

    @Override
    public Result delete(int Id) {
        UserEntity entity = userMapper.selectUserById(Id);
        if (NullUtil.isNull(entity)) {
            return Result.error("用户不存在");
        } else {
            //删除用户时，需要同步删除用户关联的分组以及权限
            if (userMapper.delete(Id) >= 0 && permissionService.deleteUserRelation(Id) >= 0) {
                userNewAuthService.deleteByUserId(Id);
                companyService.deleteCompanyUserByUserId(Id);
                teamService.deleteTeamUserByUser(Id);
                if (NullUtil.isNotNull(entity.getPhotoUrl())) {
                    fileService.deleteFile(entity.getPhotoUrl());
                }
                return Result.success();
            } else {
                return Result.error();
            }
        }
    }

    /**
     * 新增队伍与用户的关联关系
     *
     * @param TeamId
     * @param code
     * @param UserId
     */
    private void insertTeamRelat(String code, int TeamId, int UserId) {
        if (code.equals("0002") && TeamId > 0) {
            teamService.deleteTeamUser(TeamId, UserId);
            TeamUserEntity teamUserEntity = new TeamUserEntity();
            teamUserEntity.setUserId(UserId);
            teamUserEntity.setTeamId(TeamId);
            teamService.insertTeamUser(teamUserEntity);
        }
    }

    /**
     * 新增企业与用户的关联关系
     *
     * @param CompanyId
     * @param UserId
     * @param code
     */
    private void insertCompanyRelat(String code, int CompanyId, int UserId) {
        if (code.equals("0002") && CompanyId > 0) {
            companyService.deleteCompanyUser(CompanyId, UserId);
            CompanyUserEntity companyUserEntity = new CompanyUserEntity();
            companyUserEntity.setUserId(UserId);
            companyUserEntity.setCompanyId(CompanyId);
            companyService.insertCompanyUser(companyUserEntity);
        }
    }
}
