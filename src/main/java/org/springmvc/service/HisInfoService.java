package org.springmvc.service;

import org.springmvc.dto.TodayReportTab;
import org.springmvc.pojo.HisInfo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface HisInfoService {

    int saveNewHisInfo(HisInfo hisInfo);    //保存新的hisinfo

    int updateHisInfoTime(String patid, Date date, String imagepath); //更新hisinfo的产生的报告日期和图像路径

    int countReportToday(String hosId); //获取当天的报告数量

    List<TodayReportTab> getTodayReportList(int currIndex, int pageSize,String hosId);   //分页加载当天的报告列表

    HisInfo getHisInfoByCheckNum(String checkNum); //通过checknum加载hisinfo

    List<TodayReportTab> getTodaReportListByCondition(int currIndex, int pageSize, String modality, String pat_type, String dateBegin, String dateEnd,
                                                      String name, String id, String number, String clinic_id) throws ParseException;

    int delete(String checknum);

    int getCountByCheckNum(String checknum);
}
