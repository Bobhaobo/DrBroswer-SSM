package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springmvc.dto.*;
import org.springmvc.pojo.*;
import org.springmvc.service.*;
import org.springmvc.tool.ImageAndReportPathGenerator;
import org.springmvc.tool.ReportImageGenerator;
import org.springmvc.tool.SeriesNumGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/remote")
public class RemoteDiagnosisController {

    private static final Logger logger = Logger.getLogger(RemoteDiagnosisController.class);

    @Resource
    private UserService userService;

    @Resource
    private SeriesNumGenerator seriesNumGenerator;

    @Resource
    private RemoteRegisterService remoteRegisterService;

    @Resource
    private PatientService patientService;

    @Resource
    private ReportImageGenerator reportImageGenerator;

    @Resource
    private RemoteReportService remoteReportService;

    @Resource
    private HospitalService hospitalService;

    @Resource
    private ImageAndReportPathGenerator imageAndReportPathGenerator;

    /**
     * @Description: 远程诊断登记
     * @Author: Shalldid
     * @Date: Created in 11:31 2018-05-10
     * @Return:
     **/
    @RequestMapping(value = "/remote_register",method = RequestMethod.POST)
    @ResponseBody
    public String registerRemoteDiagnosis(@RequestParam("CheckNum") String checkNum,
                                          @RequestParam("PatName") String patName,
                                          @RequestParam("PatGender") String patGender,
                                          @RequestParam("CheckType") String checkType,
                                          @RequestParam("Birthday") String birthday,
                                          @RequestParam("PatType") String patType,
                                          @RequestParam("Tel") String tel,
                                          @RequestParam("Address") String address,
                                          @RequestParam("SocietyID") String societyID,
                                          @RequestParam("PatIDCard") String patIDCard,
                                          HttpSession httpSession) throws Exception{
        RemoteRegister remoteRegister = new RemoteRegister();
        remoteRegister.setFlag("未上传图像");
        remoteRegister.setId(UUID.randomUUID().toString());
        remoteRegister.setIdcard(patIDCard);
        remoteRegister.setModality(checkType);
        remoteRegister.setTagpatientid("");
        remoteRegister.setPattype(patType);
        remoteRegister.setRegdate(new Date());
        User u = (User)httpSession.getAttribute("user");
        remoteRegister.setRemotehos(userService.getHosIdOfUser(u.getDept()));
        remoteRegister.setChecknum(checkNum);
        String result = "";
        try{
            int remote_register_status = remoteRegisterService.insertNewRegister(remoteRegister);
            int patient_insert_status = patientService.insertOrUpdatePat(patName, patIDCard, patGender, birthday, address, societyID, tel);
            if(remote_register_status == 1 && patient_insert_status ==1){
                result = checkNum;
            }
        }catch (Exception e){
            result = "0";
            e.printStackTrace();
        }
        RemoteRegisterCallBack r = new RemoteRegisterCallBack();
        r.setInfo(result);
        r.setUrl("UpLoadFile://" + checkNum + "&" + u.getId() + "&"+ userService.getHosIdOfUser(u.getDept()));
        return JSON.toJSONString(r);
    }
    /**
     * @Description: 返回待写报告列表
     * @Author: Shalldid
     * @Date: Created in 14:49 2018-05-10
     * @Return:
     **/
    @RequestMapping(value = "/getWaitForReportList")
    @ResponseBody
    public String getWaitForReportList(Pagination p, HttpSession httpSession){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        List<RemoteWaitForReportTab> remoteWaitForReportTabs = remoteRegisterService.getWaitForReprotByFlag(currIndex, pageSize, httpSession);
        int totalRow = remoteWaitForReportTabs.size();
//        System.out.println(pageSize);
//        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        PaginationResult<RemoteWaitForReportTab> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(remoteWaitForReportTabs);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        //System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }
    /**
     * @Description: 远程诊断填写报告
     * @Author: Shalldid
     * @Date: Created in 16:18 2018-05-10
     * @Return:
     **/
    @RequestMapping(value = "/submitReport")
    @ResponseBody
    public String submitReport(@RequestParam("pat_checknum") String checknum,
                               @RequestParam("deptName") String deptName,
                               @RequestParam("clinicId") String clinicId,
                               @RequestParam("bedNo") String bedNo,
                               @RequestParam("jcbw") String jcbw,
                               @RequestParam("sfyangxing") String sfyangxing,
                               @RequestParam("examDesc") String examDesc,
                               @RequestParam("examDiagnosis") String examDiagnosis,
                               @RequestParam("hosName") String hosName,
                               @RequestParam("pat_name") String pat_Name,
                               @RequestParam("pat_gender") String pat_gender,
                               @RequestParam("pat_age") String pat_age,
                               HttpSession httpSession) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        User u = (User)httpSession.getAttribute("user");
        RemoteRegister r = remoteRegisterService.getRemoteRegisterByCheckNum(checknum);
        String report_path = reportImageGenerator.outputReport(checknum, hosName, r.getModality(), pat_Name, pat_gender, pat_age, deptName, clinicId, bedNo, jcbw,
                examDesc, examDiagnosis, u.getName(), "", sdf.format(new Date()));
        RemoteReport report = new RemoteReport(UUID.randomUUID().toString(),checknum,clinicId,pat_Name,pat_gender,pat_age,deptName,bedNo,jcbw,new Date(),sfyangxing,
                r.getModality(),report_path,u.getId(),examDesc,examDiagnosis,r.getRemotehos());
//        HisInfo hisInfo = new HisInfo();
//        hisInfo.setHosId(r.getRemotehos());
//        hisInfo.setPatname(pat_Name);
//        hisInfo.setCardno("");
//        hisInfo.setChecknum(report.getChecknum());
//        hisInfo.setClinicid(clinicId);
//        hisInfo.setCsh("");
//        hisInfo.setId(UUID.randomUUID().toString());
//        hisInfo.setIdcard(r.getIdcard());
//        hisInfo.setModality(r.getModality());
//        hisInfo.setPatname(report.getPatname());
//        hisInfo.setPatientid(r.getTagpatientid());
//        hisInfo.setPattype(r.getPattype());
//        hisInfo.setReportdate(new Date());
//        hisInfo.setReportpath(report_path);
//        hisInfo.setSqdbh("");
//        hisInfo.setSqks(report.getDeptname());
        //TODO
        try{
            int report_ = remoteReportService.insertNewReport(report);
            int reg_ = remoteRegisterService.updateFlagByCheckNum("已写报告", checknum);
            if(report_ == 1 && reg_ == 1){
                return "1";
            }else {
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }
    /**
     * @Description: 远程诊断加载已写报告列表
     * @Author: Shalldid
     * @Date: Created in 16:45 2018-05-10
     * @Return:
     **/
    @RequestMapping(value = "/getHadWritted")
    @ResponseBody
    public String getHadWritted(Pagination p, HttpSession httpSession){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        List<RemoteWrittedReportTab> l = remoteRegisterService.getWrittedReportByFlag(currIndex, pageSize, httpSession);
        int totalRow = l.size();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        PaginationResult<RemoteWrittedReportTab> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(l);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        //System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }
    /**
     * @Description: 根据id加载报告病人细节
     * @Author: Shalldid
     * @Date: Created in 15:12 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/{id}/modifyRemoteReportPatDetail" , method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String modifyRemoteReportPatDetail(@PathVariable("id") String id, HttpSession httpSession){
        try {
            System.out.println(id);
            RemoteReport remoteReport = remoteReportService.getReportById(id);
            System.out.println(remoteReport);
            RemoteRegister remoteRegister = remoteRegisterService.getRemoteRegisterByCheckNum(remoteReport.getChecknum());
            RemoteModifyReportPatDetail rmrpd = new RemoteModifyReportPatDetail();
            User u = (User) httpSession.getAttribute("user");
            rmrpd.setHosName(hospitalService.getHosNameByHosId(userService.getHosIdOfUser(u.getDept())));
            rmrpd.setBedNo(remoteReport.getBedno());
            rmrpd.setClinicId(remoteReport.getClinicid());
            rmrpd.setDeptName(remoteReport.getDeptname());
            rmrpd.setExamDesc(remoteReport.getExamdesc());
            rmrpd.setExamDiagnosis(remoteReport.getExamdiagnosis());
            rmrpd.setJcbw(remoteReport.getJcbw());
            rmrpd.setSfyangxing(remoteReport.getSfyangxing());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            rmrpd.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(remoteReport.getChecknum(), sdf.format(remoteReport.getReporttime()), remoteRegister.getRemotehos()));
            //System.out.println(JSON.toJSONString(rmrpd));
            return JSON.toJSONString(rmrpd);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @Description: 根据报告id修改报告
     * @Author: Shalldid
     * @Date: Created in 15:12 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/modifyReport")
    @ResponseBody
    public String remoteModifyReport(@RequestParam("pat_checknum") String checknum,
                                     @RequestParam("deptName") String deptName,
                                     @RequestParam("clinicId") String clinicId,
                                     @RequestParam("bedNo") String bedNo,
                                     @RequestParam("jcbw") String jcbw,
                                     @RequestParam("sfyangxing") String sfyangxing,
                                     @RequestParam("examDesc") String examDesc,
                                     @RequestParam("examDiagnosis") String examDiagnosis,
                                     @RequestParam("hosName") String hosName,
                                     @RequestParam("pat_name") String pat_Name,
                                     @RequestParam("pat_gender") String pat_gender,
                                     @RequestParam("pat_age") String pat_age,
                                     @RequestParam("bgCode") String id, HttpSession httpSession)throws IOException{
        try {
            RemoteReport remoteReport = remoteReportService.getReportById(id);
            remoteReport.setBedno(bedNo);
            remoteReport.setChecknum(checknum);
            remoteReport.setClinicid(clinicId);
            remoteReport.setDeptname(deptName);
            User u = (User)httpSession.getAttribute("user");
            remoteReport.setDoccode(u.getId());
            remoteReport.setExamdesc(examDesc);
            remoteReport.setExamdiagnosis(examDiagnosis);
            remoteReport.setJcbw(jcbw);
            remoteReport.setReporttime(new Date());
            remoteReport.setSfyangxing(sfyangxing);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String report_path = reportImageGenerator.outputReport(checknum, hosName, remoteReport.getModality(), pat_Name, pat_gender, pat_age, deptName, clinicId, bedNo, jcbw,
                    examDesc, examDiagnosis, u.getName(), "", sdf.format(new Date()));
            remoteReport.setReportpath(report_path);
            int update_status = remoteReportService.updateReport(remoteReport);
            if(update_status == 1 ){
                return "1";
            }else {
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }
    /**
     * @Description: 加载当天报告
     * @Author: Shalldid
     * @Date: Created in 15:13 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/loadTodayReport")
    @ResponseBody
    public String loadTodayReport(Pagination p, HttpSession httpSession){
        try{
            int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
            int pageSize  = p.getPageSize();
            boolean ifFirst = (p.getPageCurrent() == 1);
            List<RemoteSearchReportTab> remoteSearchReportTabs = remoteReportService.getTodayReport(currIndex,pageSize,httpSession);
            int totalRow = remoteSearchReportTabs.size();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            PaginationResult<RemoteSearchReportTab> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(remoteSearchReportTabs);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            //System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Description: 根据条件查询报告
     * @Author: Shalldid
     * @Date: Created in 15:13 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/getReportListByCondition")
    @ResponseBody
    public String getReportListByCondition(@RequestParam("modality") String modality,
                                           @RequestParam("pat_type") String pat_type,
                                           @RequestParam("dateBegin") String dateBegin,
                                           @RequestParam("dateEnd") String dateEnd,
                                           @RequestParam("name") String name,
                                           @RequestParam("ID") String id,
                                           @RequestParam("number") String number,
                                           @RequestParam("clinic_id") String clinic_id,
                                           Pagination p, HttpSession httpSession) throws ParseException {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        try {
            List<RemoteSearchReportTab> remoteSearchReportTabs = remoteReportService.getRemoteReportByCondition(currIndex, pageSize, modality, pat_type, dateBegin, dateEnd,
                    name, id, number, clinic_id, httpSession);
            int totalRow = remoteSearchReportTabs.size();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            PaginationResult<RemoteSearchReportTab> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(remoteSearchReportTabs);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            //System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @Description: 加载报告图像
     * @Author: Shalldid
     * @Date: Created in 15:25 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/{checknum}/{randomNum}/loadReportImage")
    public void loadReportImage(@PathVariable("checknum") String checknum,
                                @PathVariable("randomNum") String randomNum, HttpServletResponse httpServletResponse){
        httpServletResponse.setContentType("image/*");
        RemoteReport remoteReport = remoteReportService.getReportByChecknum(checknum);
        try{
            OutputStream o = httpServletResponse.getOutputStream();
            File f = new File(remoteReport.getReportpath());
            FileInputStream fileInputStream = new FileInputStream(f);
            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            o.write(b);
            o.flush();
            fileInputStream.close();
            o.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * @Description: 获取最新的checknum
     * @Author: Shalldid
     * @Date: Created in 10:47 2018-07-04
     * @Return:
     **/
    @RequestMapping(value = "/{type}/getChecknum", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getCheckNum(@PathVariable("type") String type){
        return JSON.toJSONString(seriesNumGenerator.getRemoteChecknum(type));
    }
}
