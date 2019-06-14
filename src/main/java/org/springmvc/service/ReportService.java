package org.springmvc.service;

import org.springmvc.pojo.Report;

public interface ReportService {

    int saveNewReport(Report report);   //保存新的Report

    Report getReportByBgCode(String bgCode); //通过BgCode加载报告

    int updateReportByReport(Report r); //通过report更新report

    int updateReportStatusByChecknum(String status, String checknum);

    int delete(String checknum);

    int getCountByCheckNum(String checknum);

    int updateReportByReportAdd(Report report);
}
