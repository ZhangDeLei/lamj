package com.coderfamily.lamj.dao;

import com.coderfamily.lamj.domain.ChartInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhangDL
 * @date 2018/5/16 14:58
 */
public interface ChartMapper {

    List<ChartInfo> selectTeam(@Param("companyId") int CompanyId);

    List<ChartInfo> selectTask(@Param("companyId") int CompanyId);

    List<ChartInfo> selectIntegralByTeam(@Param("companyId") int CompanyId);

    List<ChartInfo> selectIntegralByMaxUser(@Param("companyId") int CompanyId);
}
