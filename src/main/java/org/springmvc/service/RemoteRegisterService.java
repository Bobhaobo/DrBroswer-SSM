package org.springmvc.service;

import org.springmvc.dto.RemoteWaitForReportTab;
import org.springmvc.dto.RemoteWrittedReportTab;
import org.springmvc.pojo.RemoteRegister;
import org.springmvc.pojo.RemoteReport;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RemoteRegisterService {

    int insertNewRegister(RemoteRegister remoteRegister);

    List<RemoteWaitForReportTab> getWaitForReprotByFlag(int currIndex, int pageSize, HttpSession httpSession);

    RemoteRegister getRemoteRegisterByCheckNum(String checknum);

    int updateFlagByCheckNum(String flag, String checkNum);

    List<RemoteWrittedReportTab> getWrittedReportByFlag(int currIndex, int pageSize, HttpSession httpSession);
}
