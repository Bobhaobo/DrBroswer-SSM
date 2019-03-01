package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springmvc.dto.*;
import org.springmvc.pojo.*;
import org.springmvc.service.*;
import org.springmvc.tool.ReportImageGenerator;
import org.springmvc.tool.SeriesNumGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/report")
public class WriteReportController {

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Resource
    private RegInfoService regInfoService;

    @Resource
    private ReportImageGenerator reportImageGenerator;

    @Resource
    private SeriesNumGenerator seriesNumGenerator;

    @Resource
    private HisInfoService hisInfoService;

    @Resource
    private ReportService reportService;

    @Resource
    private HospitalService hospitalService;

    @Resource
    private UserService userService;

    @Resource
    private PatientService patientService;

    @Resource
    private OrderTableService orderTableService;
    /**
     * @Description:
     * @Author: Shalldid
     * @Date: Created in 10:11 2018-05-07
     * @Return:
     **/
    @RequestMapping(value = "/getHadCheckedList" )
    @ResponseBody
    public String getHadCheckedList(Pagination p, HttpSession httpSession) {
        try{
            int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
            int pageSize  = p.getPageSize();
            boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
            int totalRow = regInfoService.countCheckListByFlag("已检查"); //得到未安排预约的总数量；
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1); //总页数，先判断总页数%每页数量是否等于0，若为0返回totalRow / pageSize，若不为0返回(totalRow / pageSize) + 1
            List<CheckedTab> checkedTabs = regInfoService.getCheckedListByFlag("已检查", currIndex, pageSize, httpSession);
            PaginationResult<CheckedTab> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(checkedTabs);
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
     * @Description: 返回已写报告的分页信息
     * @Author: Shalldid
     * @Date: Created in 16:02 2018-05-07
     * @Return:
     **/
    @RequestMapping(value = "/getHadWritted")
    @ResponseBody
    public String getHadWrittedReport(Pagination p, HttpSession httpSession){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        int totalRow = regInfoService.countCheckListByFlag("已写报告"); //得到未安排预约的总数量；
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        try {
            List<WrittedTab> writtedTabs = regInfoService.getWrittededListByFlag("已写报告", currIndex, pageSize);
            PaginationResult<WrittedTab> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(writtedTabs);
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
     * @Description: 填写完报告后从页面传输回的信息写入相应的表内
     * @Author: Shalldid
     * @Date: Created in 10:11 2018-05-07
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
        RegisterInfo r = regInfoService.getRegInfoByCheckNum(checknum);
        String report_path = reportImageGenerator.outputReport(checknum, hosName, r.getExamitemname(), pat_Name, pat_gender, pat_age, deptName, clinicId, bedNo, jcbw,
                examDesc, examDiagnosis, u.getName(), "", sdf.format(new Date()));
        Report report = new Report(seriesNumGenerator.getReportCode(),r.getRecordid(),checknum,clinicId,r.getPatientid(),r.getIdcard(),bedNo,new Date(),"已写报告",
                examDesc,examDiagnosis,u.getId(),r.getExamitemcode(),r.getExamitemname(),0,"","",false,report_path,"",
                jcbw,sfyangxing);
        HisInfo hisInfo = new HisInfo(UUID.randomUUID().toString(),r.getPatientid(),new Date(),r.getExamitemname(),report_path,r.getChecknum(),"","",r.getSqdbh(),"",clinicId,
                deptName,r.getIdcard());
        OrderTable o = orderTableService.getOrderTabByCheckNum(r.getChecknum());
        hisInfo.setHosId(o.getOrdersource());
        hisInfo.setPatname(patientService.getPatByIdcard(r.getIdcard()).getPatname());
        //System.out.println(hisInfo);
        try{
            int hisinfo_ = hisInfoService.saveNewHisInfo(hisInfo);
            int report_ = reportService.saveNewReport(report);
            int reg_ = regInfoService.updateRegStatusByChecknum("已写报告",checknum);
            if(hisinfo_ == 1 && report_ ==1 && reg_ == 1){
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
     * @Description: 加载修改报告时的病人详细信息
     * @Author: Shalldid
     * @Date: Created in 10:34 2018-05-08
     * @Return:
     **/
    @RequestMapping(value = "/{bgCode}/modifyReportPatDetail", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String modifyReportPatDetail(@PathVariable("bgCode") String bgCode, HttpSession httpSession){
        //System.out.println(bgCode);
        User user = (User)httpSession.getAttribute("user");
        try {
            Report r = reportService.getReportByBgCode(bgCode);
            ReportWrittedDetail reportWrittedDetail = new ReportWrittedDetail();
            reportWrittedDetail.setBedNo(r.getBedno());
            reportWrittedDetail.setClinicId(r.getClinicid());
            reportWrittedDetail.setDeptName(r.getDeptname());
            reportWrittedDetail.setExamDesc(r.getExamdesc());
            reportWrittedDetail.setExamDiagnosis(r.getExamdiagnosis());
            reportWrittedDetail.setHosName(hospitalService.getHosNameByHosId(userService.getHosIdOfUser(user.getDept())));
            reportWrittedDetail.setJcbw(r.getJcbw());
            reportWrittedDetail.setSfyangxing(r.getSfyangxing());
            //System.out.println(JSON.toJSONString(reportWrittedDetail));
            return JSON.toJSONString(reportWrittedDetail);
        }catch (Exception e){
            return null;
        }
    }
    /**
     * @Description: 修改报告
     * @Author: Shalldid
     * @Date: Created in 16:47 2018-05-10
     * @Return:
     **/
    @RequestMapping(value = "/modifyReport")
    @ResponseBody
    public String modifyReport(@RequestParam("bgCode") String bgCode,
                               @RequestParam("deptName") String deptName,
                               @RequestParam("clinicId") String clinicId,
                               @RequestParam("bedNo") String bedNo,
                               @RequestParam("jcbw") String jcbw,
                               @RequestParam("sfyangxing") String sfyangxing,
                               @RequestParam("hosName") String hosName,
                               @RequestParam("pat_name") String pat_Name,
                               @RequestParam("pat_gender") String pat_gender,
                               @RequestParam("pat_age") String pat_age,
                               @RequestParam("examDesc") String examDesc,
                               @RequestParam("examDiagnosis") String examDiagnosis,
                               HttpSession httpSession) throws IOException{
//        System.out.println(bgCode);
//        System.out.println(examDesc);
//        System.out.println(examDiagnosis);
        Report report = reportService.getReportByBgCode(bgCode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        User u = (User)httpSession.getAttribute("user");
        report.setBedno(bedNo);
        report.setDeptname(deptName);
        report.setClinicid(clinicId);
        report.setDoccode(u.getId());
        report.setJcbw(jcbw);
        report.setSfyangxing(sfyangxing);
        report.setExamdesc(examDesc);
        report.setExamdiagnosis(examDiagnosis);
        RegisterInfo r = regInfoService.getRegInfoByCheckNum(report.getChecknum());
        String report_path = reportImageGenerator.outputReport(report.getChecknum(), hosName, r.getExamitemname(), pat_Name, pat_gender, pat_age, deptName, clinicId, bedNo, jcbw,
                examDesc, examDiagnosis, u.getName(), "", sdf.format(new Date()));
        report.setReportImagePath(report_path);
        report.setReporttime(new Date());
        try{
            int hisinfo_ = hisInfoService.updateHisInfoTime(r.getPatientid(),new Date(),report_path);
            int report_ = reportService.updateReportByReport(report);
            if(hisinfo_ == 1 && report_ == 1){
                return "1";
            }else {
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }
}
