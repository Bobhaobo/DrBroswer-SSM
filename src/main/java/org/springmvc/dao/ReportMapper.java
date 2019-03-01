package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.Report;

public interface ReportMapper {
    int deleteByPrimaryKey(String reportcode);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(String reportcode);

    Report selectByCheckNum(String checnum);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);

    String getReportCode(@Param("param") String param);
}