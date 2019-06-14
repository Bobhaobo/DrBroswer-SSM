package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.RemoteReport;
import org.springmvc.pojo.TemporaryReport;

import java.util.Date;
import java.util.List;

public interface TemporaryReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(TemporaryReport record);

    int insertSelective(TemporaryReport record);

    TemporaryReport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TemporaryReport record);

    int updateByPrimaryKey(TemporaryReport record);

    TemporaryReport selectBycheckNum(String checknum);

    String selectIdByRemoteReportId(String remote_report_id);

    TemporaryReport selectTemporaryReportByRemoteReportId(String remote_report_id);

    int updateIfHadVerified(@Param("verifydoccode") String verifydoccode,@Param("verifydocname") String verifydocname,@Param("verifyupdatetime") Date verifyupdatetime,@Param("remoteReportId") String id );

    int selectCountBycheckNum(String checknum);

    List<TemporaryReport> selectListBycheckNum(String checknum);
}