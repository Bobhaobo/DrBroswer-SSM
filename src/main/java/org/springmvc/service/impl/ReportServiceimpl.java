package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.ReportMapper;
import org.springmvc.pojo.Report;
import org.springmvc.service.ReportService;

@Service
public class ReportServiceimpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    /**
     * @Description: 保存新的Report
     * @Author: Shalldid
     * @Date: Created in 11:01 2018-05-07
     * @Return:
     **/
    @Override
    public int saveNewReport(Report report){
        return reportMapper.insert(report);
    }
    /**
     * @Description: 通过BgCode加载报告
     * @Author: Shalldid
     * @Date: Created in 10:40 2018-05-08
     * @Return:
     **/
    @Override
    public Report getReportByBgCode(String bgCode){
        return reportMapper.selectByPrimaryKey(bgCode);
    }
    /**
     * @Description: 通过report更新report
     * @Author: Shalldid
     * @Date: Created in 12:05 2018-05-08
     * @Return:
     **/
    @Override
    public int updateReportByReport(Report r){
        return reportMapper.updateByPrimaryKey(r);
    }


    @Override
    public int updateReportByReportAdd(Report r){
        return reportMapper.updateByPrimaryKeyAdd(r);
    }

   @Override
    public  int updateReportStatusByChecknum(String status, String checknum){return reportMapper.updateReportStatusByChecknum(status, checknum);}

    @Override
    public  int delete(String checknum){return reportMapper.deleteByCheckNum(checknum);}

    @Override
    public int getCountByCheckNum(String checknum){return reportMapper.selectCountByCheckNum(checknum);}
}
