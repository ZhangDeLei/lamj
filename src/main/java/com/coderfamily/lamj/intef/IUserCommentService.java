package com.coderfamily.lamj.intef;

import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.model.UserCommentEntity;
import com.github.pagehelper.PageInfo;

/**
 * @author ZhangDL
 * @date 2018/5/2 14:20
 */
public interface IUserCommentService {
    /**
     * 分页获取用户评论列表
     * @param CompanyId
     * @param TaskId
     * @param UserId
     * @param PageSize
     * @param CurPage
     * @return
     */
    PageInfo<UserCommentEntity> getUserCommentList(int CompanyId, int TaskId, Integer UserId, int PageSize, int CurPage);

    /**
     * 新增用户评论
     * @param params
     * @return
     */
    Result insert(UserCommentEntity params);

    /**
     * 删除用户评论
     * @param params
     * @return
     */
    Result update(UserCommentEntity params);

    /**
     * 根据ID删除用户评论
     */
    Result delete(int Id);
}
