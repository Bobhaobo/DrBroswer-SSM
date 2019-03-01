package org.springmvc.service;

import org.springmvc.dto.RemoteSearchReportTab;
import org.springmvc.pojo.RemoteReport;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public interface RemoteReportService {

    int insertNewReport(RemoteReport remoteReport);

    RemoteReport getReportByChecknum(String checknum);

    RemoteReport getReportById(String id);

    int updateReport(RemoteReport remoteReport);

    List<RemoteSearchReportTab> getTodayReport(int currIndex, int pageSize, HttpSession httpSession);

    List<RemoteSearchReportTab> getRemoteReportByCondition(int currIndex, int pageSize, String modality, String pat_type,
                                                           String dateBegin, String dateEnd, String name, String id,
                                                           String number, String clinic_id, HttpSession httpSession) throws ParseException;
}
