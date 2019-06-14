package org.springmvc.service;

import org.apache.ibatis.annotations.Param;
import org.springmvc.dto.RemoteAllocateDto;
import org.springmvc.dto.RemoteVerifiedReportTab;
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

    List<RemoteVerifiedReportTab> getVerifiedReportByFlag(int currIndex, int pageSize, HttpSession httpSession);

    int updateFlagById(String flag,String id);


    List<RemoteAllocateDto> getRmoteRegister(String checknum,String modality,String pattype,String name,String sex,String age);

    RemoteRegister getRemoteRegisterById(String id);

    List<RemoteRegister> getRemoteAllocateByFlag(String flag);

    List<RemoteWrittedReportTab> getBackWaitForReprotByFlag(int currIndex, int pageSize, HttpSession httpSession);
//    List<RemoteAllocateDto> getsearchResults(String name, String modality, String pat_type, String number, String sex, String age);

}
