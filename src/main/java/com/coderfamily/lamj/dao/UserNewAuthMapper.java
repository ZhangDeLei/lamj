package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.model.UserNewAuthEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserNewAuthMapper {

    List<UserNewAuthEntity> selectUserNewAuthList(@Param("companyId") int CompanyId, @Param("userId") int UserId);

    UserNewAuthEntity selectById(@Param("id") int Id);

    boolean existsUserNewAuthByUserIdAndNewId(@Param("userId") int UserId, @Param("newId") int NewId);

    int insert(UserNewAuthEntity entity);

    int update(UserNewAuthEntity entity);

    int deleteByNewid(@Param("newId") int NewId);

    int deleteByUserId(@Param("userId") int UserId);

    int delete(@Param("id") int Id);
}