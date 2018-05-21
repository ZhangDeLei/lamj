package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.domain.UserNewAuthInfo;
import com.coderfamily.lamj.model.UserNewAuthEntity;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/3/8 10:58
 */
public interface IUserNewAuthService {

    /**
     * 根据用户ID查询用户授权列表
     *
     * @param CompanyId
     * @param UserId
     * @return
     */
    List<UserNewAuthInfo> getUserNewAuthList(int CompanyId, int UserId);

    /**
     * 新增用户授权记录
     *
     * @param entity
     * @return
     */
    Result insert(UserNewAuthEntity entity);

    /**
     * 更新用户授权记录
     *
     * @param entity
     * @return
     */
    Result update(UserNewAuthEntity entity);

    /**
     * 根据新闻ID删除用户与新闻的关联关系
     *
     * @param NewId
     * @return
     */
    int deleteByNewId(int NewId);

    /**
     * 根据用户ID删除用户与新闻的关联关系
     *
     * @param UserId
     * @return
     */
    int deleteByUserId(int UserId);

    /**
     * 根据用户ID删除用户与新闻的关联关系
     *
     * @param Id
     * @return
     */
    int delete(int Id);
}
