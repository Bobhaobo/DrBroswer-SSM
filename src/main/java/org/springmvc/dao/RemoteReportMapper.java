package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.RemoteReport;

import java.util.Date;
import java.util.List;

public interface RemoteReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(RemoteReport record);

    int insertSelective(RemoteReport record);

    RemoteReport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RemoteReport record);

    int updateByPrimaryKey(RemoteReport record);

    int updateByPrimaryKeyAdd(RemoteReport record);

    RemoteReport selectBycheckNum(String checknum);

    RemoteReport selectBycheckNumAdd(String checknum);

    List<RemoteReport> getTodayReportListByPagination(@Param("dateStart") Date datestart, @Param("dateEnd") Date dateend,
                                                      @Param("currIndex") int currIndex, @Param("pageSize") int pageSize,
                                                      @Param("hos") String hos);

    List<RemoteReport> getReportListByCondition(@Param("currIndex") int currIndex, @Param("pageSize") int pageSize, @Param("condition") String contidtion);

    RemoteReport selectByPrimaryKeyAdd(String id);

    int deleteById(@Param("id") String id);

    int updateById(String id);

    int updateRemarkById(@Param("id") String id,@Param("remarks") String remarks);

    RemoteReport selectByPrimaryKeyAddSecond(String id);

    int insertAdd(RemoteReport report);
}