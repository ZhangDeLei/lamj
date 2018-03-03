package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NumberUtil;
import com.coderfamily.lamj.dao.GroupMapper;
import com.coderfamily.lamj.domain.GroupPermissionInfo;
import com.coderfamily.lamj.model.GroupEntity;
import com.coderfamily.lamj.model.UserGroupEntity;
import com.coderfamily.lamj.service.IGroupService;
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
public class GroupServiceImpl implements IGroupService {
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<GroupEntity> selectGroupByCondition(GroupEntity groupEntity) {
        return groupMapper.selectGroupByCondition(groupEntity);
    }

    @Override
    public List<GroupEntity> selectGroupList() {
        return groupMapper.selectGroupList();
    }

    @Override
    public List<GroupEntity> selectGroupByUserId(int UserId) {
        return groupMapper.selectGroupByUserId(UserId);
    }

    @Override
    public List<GroupPermissionInfo> selectGroupPermissionByGroupId(int GroupId) {
        return groupMapper.selectGroupPermissionByGroupId(GroupId);
    }

    @Override
    public boolean existsGroupByName(String Name) {
        return groupMapper.existsGroupByName(Name);
    }

    @Override
    public Result insert(GroupEntity groupEntity) {
        groupEntity.setCode(getMaxCode());
        if (existsGroupByName(groupEntity.getName())) {
            return Result.error("用户组名称已存在");
        } else if (groupMapper.insert(groupEntity) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int update(GroupEntity groupEntity) {
        return groupMapper.update(groupEntity);
    }

    @Override
    public Result delete(int Id) {
        if (groupMapper.existsGroupHasPermissionById(Id)) {
            return Result.error("当前用户组包含权限信息，请先删除");
        } else if (groupMapper.delete(Id) > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @Override
    public int deleteGroupRelation(int UserId) {
        return groupMapper.deleteGroupRelation(UserId);
    }

    @Override
    public Result insertUserGroup(int UserId, List<Integer> mIds) {
        deleteGroupRelation(UserId);
        if (mIds != null && mIds.size() > 0) {
            List<UserGroupEntity> mData = new ArrayList<>();
            mIds.forEach(item -> {
                UserGroupEntity entity = new UserGroupEntity();
                entity.setUserId(UserId);
                entity.setGroupId(item);
                mData.add(entity);
            });
            if (groupMapper.insertUserGroupRelat(mData) > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        } else {
            return Result.success();
        }
    }

    @Override
    public boolean insertUserGroup(List<UserGroupEntity> mList) {
        return groupMapper.insertUserGroupRelat(mList) > 0;
    }

    private String getMaxCode() {
        String maxCode = groupMapper.selectMaxCode();
        int code = NumberUtil.toInt(maxCode) + 1;
        return NumberUtil.seats(code, maxCode.length());
    }
}
