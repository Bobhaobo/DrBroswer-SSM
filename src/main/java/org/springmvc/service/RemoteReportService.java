package org.springmvc.service;

import org.springmvc.dto.RemoteSearchReportTab;
import org.springmvc.pojo.RemoteReport;
import org.springmvc.pojo.TemporaryReport;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public interface RemoteReportService {

    int insertNewReport(RemoteReport remoteReport);

    RemoteReport getReportByChecknum(String checknum);

    RemoteReport getReportById(String id);

    int updateReport(RemoteReport remoteReport);

    int updateReportAdd(RemoteReport remoteReport);

    List<RemoteSearchReportTab> getTodayReport(int currIndex, int pageSize, HttpSession httpSession);

    List<RemoteSearchReportTab> getRemoteReportByCondition(int currIndex, int pageSize, String modality, String pat_type,
                                                           String dateBegin, String dateEnd, String name, String id,
                                                           String number, String clinic_id, HttpSession httpSession) throws ParseException;

    RemoteReport getReportByIdAdd(String id);

    int deleteReportById(String id);

    int insertNewReportToTemporaryReport(TemporaryReport temporaryReport);

    TemporaryReport getTemporaryReportById(String id);

    int updateReportById(String id);
}
