package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springmvc.dao.DepartmentMapper;
import org.springmvc.dto.*;
import org.springmvc.pojo.*;
import org.springmvc.service.*;
import org.springmvc.tool.ConfigReader;
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

    @Resource
    private DepartmentMapper departmentMapper;

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
            System.out.println(JSON.toJSONString(paginationResult));
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
        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        try {
            System.out.println("123");
            System.out.println("000");
            List<WrittedTab> writtedTabs = regInfoService.getWrittededListByFlag("已写报告", currIndex, pageSize);
            System.out.println("123");
            PaginationResult<WrittedTab> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(writtedTabs);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 获取已审查信息
     * @Author: Shalldid
     * @Date: Created in 16:02 2018-05-07
     * @Return:
     **/
    @RequestMapping(value = "/getHadVerified")
    @ResponseBody
    public String getHadVerifiedReport(Pagination p, HttpSession httpSession){
        System.out.println("123");
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        int totalRow = regInfoService.countCheckListByFlag("已审核报告"); //得到未安排预约的总数量；
        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        try {
            List<VerifiedTab> writtedTabs = regInfoService.getVerifiedListByFlag("已审核报告", currIndex, pageSize);
            System.out.println("321");
            System.out.println(writtedTabs);
            PaginationResult<VerifiedTab> paginationResult = new PaginationResult();
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
        System.out.println("789");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        User u = (User)httpSession.getAttribute("user");
        RegisterInfo r = regInfoService.getRegInfoByCheckNum(checknum);
        String report_path = reportImageGenerator.outputReport(checknum, hosName, r.getExamitemname(), pat_Name, pat_gender, pat_age, deptName, clinicId, bedNo, jcbw,
                examDesc, examDiagnosis, u.getName(), "", sdf.format(new Date()));
//        Report report = new Report(seriesNumGenerator.getReportCode(),r.getRecordid(),checknum,clinicId,r.getPatientid(),r.getIdcard(),bedNo,new Date(),"已写报告",
//                examDesc,examDiagnosis,u.getId(),r.getExamitemcode(),r.getExamitemname(),0,"","",false,report_path,"",
//                jcbw,sfyangxing);
        Report report = new Report(seriesNumGenerator.getReportCode(),r.getRecordid(),checknum,clinicId,r.getPatientid(),r.getIdcard(),bedNo,new Date(),"已写报告",
                examDesc,examDiagnosis,u.getId(),r.getExamitemcode(),r.getExamitemname(),0,deptName,"",false,report_path,"",
                jcbw,sfyangxing);
        HisInfo hisInfo = new HisInfo(UUID.randomUUID().toString(),r.getPatientid(),new Date(),r.getExamitemname(),report_path,r.getChecknum(),"","",r.getSqdbh(),"",clinicId,
                deptName,r.getIdcard());
        OrderTable o = orderTableService.getOrderTabByCheckNum(r.getChecknum());
        hisInfo.setHosId(o.getOrdersource());
        hisInfo.setPatname(patientService.getPatByIdcard(r.getIdcard()).getPatname());
        //System.out.println(hisInfo);
        try{
//            System.out.println("123000");
//            System.out.println(checknum);
            int report_checknum_count = reportService.getCountByCheckNum(checknum);
//            System.out.println(report_checknum_count);
            int hisinfo_checknum_count = hisInfoService.getCountByCheckNum(checknum);
//            System.out.println(hisinfo_checknum_count);
            int reginfo_checknum_count = regInfoService.getCountByCheckNum(checknum);
//            System.out.println(reginfo_checknum_count);
            if(report_checknum_count>0) {
                reportService.delete(report.getChecknum());
            }
            if(hisinfo_checknum_count>0) {
                hisInfoService.delete(r.getChecknum()); //删除老信息
            }
            if(reginfo_checknum_count>1){
                regInfoService.delete(report.getChecknum());
            }
            int hisinfo_ = hisInfoService.saveNewHisInfo(hisInfo);
            int report_ = reportService.saveNewReport(report);
            int reg_ = regInfoService.updateRegStatusByChecknum("已写报告",checknum);
            System.out.println(hisinfo_);
            System.out.println(report_);
            System.out.println(reg_);
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
     * @Description: 加载审核页面的病人详细信息
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
            System.out.println(r);
            ReportWrittedDetail reportWrittedDetail = new ReportWrittedDetail();
            reportWrittedDetail.setBedNo(r.getBedno());
            reportWrittedDetail.setClinicId(r.getClinicid());
            reportWrittedDetail.setDeptName(r.getDeptname());
            System.out.println(r.getDeptname());
            System.out.println(reportWrittedDetail.getDeptName());
            System.out.println("123567890");
            reportWrittedDetail.setExamDesc(r.getExamdesc());
            reportWrittedDetail.setExamDiagnosis(r.getExamdiagnosis());
            reportWrittedDetail.setHosName(hospitalService.getHosNameByHosId(userService.getHosIdOfUser(user.getDept())));
            reportWrittedDetail.setJcbw(r.getJcbw());
            reportWrittedDetail.setSfyangxing(r.getSfyangxing());
            System.out.println(reportWrittedDetail);
            //System.out.println(JSON.toJSONString(reportWrittedDetail));
            return JSON.toJSONString(reportWrittedDetail);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * @Description: 加载审核页面的病人详细信息(本地)
     * @Author: Shalldid
     * @Date: Created in 10:34 2018-05-08
     * @Return:
     **/
    @RequestMapping(value = "/{bgCode}/modifyReportPatDetailLocal", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String modifyReportPatDetailLocal(@PathVariable("bgCode") String bgCode, HttpSession httpSession){
        //System.out.println(bgCode);
        User user = (User)httpSession.getAttribute("user");
//        String filepath="F:\\DrBroswer-SSM\\src\\main\\resources\\本地医院名称.ini";
//        ConfigReader configReader=new ConfigReader(filepath);
        try {
            Report r = reportService.getReportByBgCode(bgCode);
            System.out.println(r);
            ReportWrittedDetail reportWrittedDetail = new ReportWrittedDetail();
            reportWrittedDetail.setBedNo(r.getBedno());
            reportWrittedDetail.setClinicId(r.getClinicid());
            reportWrittedDetail.setDeptName(r.getDeptname());
            System.out.println(r.getDeptname());
            System.out.println(reportWrittedDetail.getDeptName());
            System.out.println("123567890");
            reportWrittedDetail.setExamDesc(r.getExamdesc());
            reportWrittedDetail.setExamDiagnosis(r.getExamdiagnosis());
            reportWrittedDetail.setHosName( hospitalService.getHosNameByHosId( departmentMapper.getHosIdbyDeptid(user.getDept())));
            reportWrittedDetail.setJcbw(r.getJcbw());
            reportWrittedDetail.setSfyangxing(r.getSfyangxing());
            System.out.println(reportWrittedDetail);
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
        System.out.println("hgf");
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
        report.setFlag("已审核报告");

        report.setVerifydoccode(u.getUsername());


        RegisterInfo r = regInfoService.getRegInfoByCheckNum(report.getChecknum());
//        System.out.println(report.getChecknum());
//        System.out.println("lalala");
//        System.out.println(r);
        r.setFlag("已审核报告");
//        System.out.println(r);
        String report_path = reportImageGenerator.outputReport(report.getChecknum(), hosName, r.getExamitemname(), pat_Name, pat_gender, pat_age, deptName, clinicId, bedNo, jcbw,
                examDesc, examDiagnosis, u.getName(), "", sdf.format(new Date()));
        report.setReportImagePath(report_path);
        report.setReporttime(new Date());
        try{
//            int report_checknum_count = reportService.getCountByCheckNum(report.getChecknum());
//                   int hisinfo_checknum_count = hisInfoService.getCountByCheckNum(r.getChecknum());
//            int reginfo_checknum_count = regInfoService.getCountByCheckNum(report.getChecknum());
//            if(report_checknum_count>0) {
//                reportService.delete(report.getChecknum());
//            }
//                   if(hisinfo_checknum_count>0) {
//                       hisInfoService.delete(r.getChecknum()); //删除老信息
//                   }
//            if(reginfo_checknum_count>0){
//                regInfoService.delete(report.getChecknum());
//            }
            int hisinfo_ = hisInfoService.updateHisInfoTime(r.getPatientid(),new Date(),report_path);
            System.out.println(r.getPatientid());
            System.out.println(report_path);
            int report_ = reportService.updateReportByReportAdd(report);
            int register_ = regInfoService.updateRegisterByRegister(r);
            System.out.println(hisinfo_);
            System.out.println(report_);
            System.out.println(register_);
            if(hisinfo_ == 1 && report_ == 1 && register_==1){
                return "1";
            }else {
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }

    @RequestMapping(value="/backToWriteReport")
    @ResponseBody
    public String backToWriteReport(@RequestParam("checkNum") String checknum,
                                    HttpSession httpSession) throws IOException {
//        System.out.println("lalala");
//        RegisterInfo r = regInfoService.getRegInfoByCheckNum(checknum);
//        System.out.println("123");
        try{
            int reg_ = regInfoService.updateRegStatusByChecknum("已检查",checknum);
            int rep_ =reportService.updateReportStatusByChecknum("已检查",checknum);
//            System.out.println(checknum);
//            System.out.println(reg_);
//            System.out.println("321");
            if(reg_ == 1 &&rep_==1){
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
