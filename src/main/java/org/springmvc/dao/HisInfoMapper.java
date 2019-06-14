package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.HisInfo;

import java.util.Date;
import java.util.List;

public interface HisInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HisInfo record);

    int insertSelective(HisInfo record);

    HisInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HisInfo record);

    int updateByPrimaryKey(HisInfo record);

    int updateHisInfoDateByPatid(@Param("patid") String patid, @Param("date") Date date, @Param("imagepath") String path);

    int countReportToday(@Param("datestart") Date datestart, @Param("dateend") Date dateend, @Param("hosId") String hosId); //获取当天的报告数量

    List<HisInfo> getTodayReportListByPagination(@Param("datestart") Date datestart, @Param("dateend") Date dateend, @Param("hosId") String hosId,
                                                 @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    HisInfo selectByCheckNum(String checknum);

    List<HisInfo> getReportListByCondition(@Param("currIndex") int currIndex, @Param("pageSize") int pageSize,
                                           @Param("condition") String condition);

    int deleteByCheckNum(String checknum);

    int selectCountByCheckNum(String checknum);
}