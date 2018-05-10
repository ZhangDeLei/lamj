package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.UserCommentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCommentMapper {

    /**
     * 获取评论列表
     * @param CompanyId
     * @param TaskId
     * @param UserId
     * @return
     */
    List<UserCommentEntity> selectUserCommentList(@Param("companyId") int CompanyId,
                                                  @Param("taskId") int TaskId,
                                                  @Param("userId") Integer UserId);

    /**
     * 新增评论
     * @param entity
     * @return
     */
    int insert(UserCommentEntity entity);

    /**
     * 更新评论
     * @param entity
     * @return
     */
    int update(UserCommentEntity entity);

    /**
     * 删除评论
     * @param Id
     * @return
     */
    int delete(@Param("id") int Id);
}