package com.coderfamily.lamj.service.impl;

import com.coderfamily.lamj.dao.GroupMapper;
import com.coderfamily.lamj.model.GroupEntity;
import com.coderfamily.lamj.service.IGroupService;
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
public class GroupServiceImpl implements IGroupService {
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<GroupEntity> selectGroupByCondition(GroupEntity groupEntity) {
        return groupMapper.selectGroupByCondition(groupEntity);
    }

    @Override
    public List<GroupEntity> selectGroupByUserId(int UserId) {
        return groupMapper.selectGroupByUserId(UserId);
    }

    @Override
    public boolean existsGroupByName(String Name) {
        return groupMapper.existsGroupByName(Name);
    }

    @Override
    public int insert(GroupEntity groupEntity) {
        return groupMapper.insert(groupEntity);
    }

    @Override
    public int update(GroupEntity groupEntity) {
        return groupMapper.update(groupEntity);
    }

    @Override
    public int delete(int Id) {
        return groupMapper.delete(Id);
    }
}
