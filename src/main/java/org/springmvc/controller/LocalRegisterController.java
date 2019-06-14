package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springmvc.dao.DepartmentMapper;
import org.springmvc.dao.RegisterInfoLocalMapper;
import org.springmvc.dto.*;
import org.springmvc.pojo.*;
import org.springmvc.pojo_inner.DicomWorkListInner;
import org.springmvc.pojo_inner.RegisterInfoInner;
import org.springmvc.service.*;
import org.springmvc.tool.ConfigReader;
import org.springmvc.tool.ReportImageGenerator;
import org.springmvc.tool.SeriesNumGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 本地登记
 * @Author: Shalldid
 * @Date: Created in 12:30 2018-07-17
 * @Return:
 **/
@Controller
@RequestMapping("/local")
public class LocalRegisterController {
    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Resource
    private HospitalService hospitalService;

    @Resource
    private UserService userService;

    @Resource
    private RegInfoService regInfoService;

    @Resource
    private SeriesNumGenerator seriesNumGenerator;

    @Resource
    private RegisterInfoLocalMapper registerInfoLocalMapper;


    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private RegisterInfoLocalService registerInfoLocalService;

    @Resource
    private PatientService patientService;

    @Resource
    private ReportImageGenerator reportImageGenerator;

    @Resource
    private HisInfoService hisInfoService;

    @Resource
    private ReportService reportService;

    /**
     * @Description: 医院本地登记(把信息插入到dicom_worklist和patlist)
     * @Author: Shalldid
     * @Date: Created in 15:05 2018-07-17
     * @Return:
     **/
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam("checkType_localPart")String checkType,@RequestParam("checkNum_localPart")String checkNum_localPart,
                           @RequestParam("checkDevice_localPart")String checkDevice,@RequestParam("PatName_localPart")String PatName,
                           @RequestParam("PatGender_localPart")String patGender,@RequestParam("birthday_localPart")String birthday,
                           @RequestParam("PatType_localPart")String PatType,@RequestParam("PatIDCard_localPart")String PatIDCard,
                           @RequestParam("tel_localPart")String tel,@RequestParam("address_localPart")String address,
                           @RequestParam("societyID_localPart")String societyID,@RequestParam("clinicID_localPart")String clinicID,
                           @RequestParam("sqdbh_localPart")String sqdbh,@RequestParam("jcbw_localPart")String jcbw,
                           @RequestParam("bedroom_localPart")String bedroom,@RequestParam("bedNo_localPart")String bedNo,
                           @RequestParam("sjmd_localPart")String sjmd,@RequestParam("card_num_localPart")String card_num,
                           HttpSession httpSession) throws ParseException {
        System.out.println("hxd");
        RegisterInfoInner register = regInfoService.insertNewRegInfoLocal(clinicID,PatName,patGender,birthday,address,societyID,PatIDCard,tel,bedroom,bedNo,"DEFAULT","默认",
                checkType,card_num,jcbw,sjmd,sqdbh,PatType,checkDevice,httpSession);
        System.out.println(register);//null
        int status_pat = patientService.insertOrUpdatePat(PatName,PatIDCard,patGender,birthday,address,societyID,
                tel); //插入病人信息
        System.out.println(status_pat);
        ReturnStatus rs = new ReturnStatus();
        if(register != null && status_pat > 0){
            rs.setStatusCode("200");
            rs.setMessage("操作成功！");
        }else {
            rs.setStatusCode("300");
            rs.setMessage("未知错误！");
        }
        return JSON.toJSONString(rs);
    }

    /**
     * @Description: 加载医院可用检查类型
     * @Author: Shalldid
     * @Date: Created in 11:18 2018-07-17
     * @Return:
     **/
    @RequestMapping(value = "/getCheckItems", produces="text/html; charset=UTF-8")
    @ResponseBody
    public String loadCheckItems(HttpSession httpSession){
        User u = (User)httpSession.getAttribute("user");
        List<ExamItemLocal> examItems = hospitalService.getExamItemsLocalByHosId(userService.getHosIdOfUser(u.getDept()));
        List<SelectPicker> list = new ArrayList<SelectPicker>();
        //ExamItemInfo examItemInfo = new ExamItemInfo();   //不能在外面新建对象，具体参考ArrayList的add过程
        for(ExamItemLocal e : examItems){
            //System.out.println(e);
            SelectPicker examItemInfo = new SelectPicker();
            examItemInfo.setLabel(e.getExamitemname());
            examItemInfo.setValue(e.getExamitemcode());
            list.add(examItemInfo);
        }
        //System.out.println(JSON.toJSONString(list));
        return JSON.toJSONString(list);
    }

    /**
     * @Description: 根据examItemCode从数据库device_info查询可用检查设备
     * @Author: Shalldid
     * @Date: Created in 9:34 2018-05-03
     * @Return:
     **/
    @RequestMapping(value = "/{examItemCode}/getCheckDevicesLocal", produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getCheckDeviceList(@PathVariable("examItemCode") String examItemCode){
        List<Device> l = regInfoService.getExamItemDeviceLocal(examItemCode);    //查询的设备列表
        List<SelectPicker> list = new ArrayList<SelectPicker>();
        for(Device d : l){
            //System.out.println(d);
            SelectPicker device = new SelectPicker();
            device.setLabel(d.getDevdesc());
            device.setValue(d.getDevdesc());
            list.add(device);
        }
        //System.out.println(JSON.toJSONString(l));
        return JSON.toJSONString(list);
    }
    /**
     * @Description: 获取最新的checknum
     * @Author: Shalldid
     * @Date: Created in 10:47 2018-07-04
     * @Return:
     **/
    @RequestMapping(value = "/{type}/getCheckNum", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getCheckNum(@PathVariable("type") String type){
        try{
            return JSON.toJSONString(seriesNumGenerator.getLocalCheckNum(type));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @Description: 加载修改报告时的病人详细信息(本地)
     * @Author: Shalldid
     * @Date: Created in 10:47 2018-07-04
     * @Return:
     **/
    @RequestMapping(value = "/{bgCode}/modifyReportPatDetailLocal", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String modifyReportPatDetail(@PathVariable("bgCode") String bgCode, HttpSession httpSession){
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



//            reportWrittedDetail.setHosName(String.valueOf(configReader.get("section1","key1")).substring(1,4));
            reportWrittedDetail.setHosName(hospitalService.getHosNameByHosId( departmentMapper.getHosIdbyDeptid(user.getDept())));

            reportWrittedDetail.setJcbw(r.getJcbw());
            reportWrittedDetail.setSfyangxing(r.getSfyangxing());
            System.out.println(reportWrittedDetail);
            System.out.println(JSON.toJSONString(reportWrittedDetail));
            return JSON.toJSONString(reportWrittedDetail);
        }catch (Exception e){
            return null;
        }
    }


    /**
     * @Description: 修改报告(本地)
     * @Author: Shalldid
     * @Date: Created in 10:47 2018-07-04
     * @Return:
     **/
    @RequestMapping(value = "/modifyReportLocal")
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
        System.out.println(hosName);
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

        report.setVerifydoccode(u.getUsername());//获取审核医师的名字

        RegisterInfoInner r = regInfoService.getRegInfoByCheckNumLocalModify(report.getChecknum());
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
            int register_ = registerInfoLocalService.updateRegisterByRegisterLocal(r);
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


    /**
     * @Description: 填写完报告后从页面传输回的信息写入相应的表内
     * @Author: Shalldid
     * @Date: Created in
     * @Return:
     **/
    @RequestMapping(value = "/submitReportLocal")
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

//        String filepath="F:\\DrBroswer-SSM\\src\\main\\resources\\本地医院名称.ini";
//        ConfigReader configReader=new ConfigReader(filepath);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        User u = (User)httpSession.getAttribute("user");
        RegisterInfoInner r = regInfoService.getRegInfoByCheckNumLocal(checknum);
        String report_path = reportImageGenerator.outputReport(checknum, hosName, r.getExamitemname(), pat_Name, pat_gender, pat_age, deptName, clinicId, bedNo, jcbw,
                examDesc, examDiagnosis, u.getName(), "", sdf.format(new Date()));
//        Report report = new Report(seriesNumGenerator.getReportCode(),r.getRecordid(),checknum,clinicId,r.getPatientid(),r.getIdcard(),bedNo,new Date(),"已写报告",
//                examDesc,examDiagnosis,u.getId(),r.getExamitemcode(),r.getExamitemname(),0,"","",false,report_path,"",
//                jcbw,sfyangxing);
        Report report = new Report(seriesNumGenerator.getReportCode(),r.getRecordid(),checknum,clinicId,r.getPatientid(),r.getIdentityid(),bedNo,new Date(),"已写报告",
                examDesc,examDiagnosis,u.getId(),r.getExamitemcode(),r.getExamitemname(),0,deptName,"",false,report_path,"",
                jcbw,sfyangxing);
        HisInfo hisInfo = new HisInfo(UUID.randomUUID().toString(),r.getPatientid(),new Date(),r.getExamitemname(),report_path,r.getChecknum(),"","",r.getSqdbh(),"",clinicId,
                deptName,r.getIdentityid());

//        String s=String.valueOf(configReader.get("section1","key2"));
//        hisInfo.setHosId(String.valueOf(configReader.get("section1","key2")).substring(1,s.length()-1));
        hisInfo.setHosId( departmentMapper.getHosIdbyDeptid(u.getDept()));


        hisInfo.setPatname(patientService.getPatByIdcard(r.getIdentityid()).getPatname());
        try{
            int report_checknum_count = reportService.getCountByCheckNum(checknum);
            int hisinfo_checknum_count = hisInfoService.getCountByCheckNum(checknum);
            int reginfo_checknum_count = regInfoService.getCountByCheckNum(checknum);
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
            int reg_ = regInfoService.updateRegStatusByChecknumLocal("已写报告",checknum);
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
     * @Description: 返回已写报告的分页信息
     * @Author: Shalldid
     * @Date: Created in
     * @Return:
     **/
    @RequestMapping(value = "/getHadWrittedLocal")
    @ResponseBody
    public String getHadWrittedReportLocal(Pagination p, HttpSession httpSession){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        int totalRow = regInfoService.countCheckListByFlagLocal("已写报告"); //得到未安排预约的总数量；
        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        try {
            System.out.println("123");
            System.out.println("000");
            List<WrittedTab> writtedTabs = regInfoService.getWrittededListByFlagLocal("已写报告", currIndex, pageSize,httpSession);
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
     * @Description: 获取已审查信息(本地)
     * @Author: Shalldid
     * @Date: Created in 16:02 2018-05-07
     * @Return:
     **/
    @RequestMapping(value = "/getHadVerifiedLocal")
    @ResponseBody
    public String getHadVerifiedReportLocal(Pagination p, HttpSession httpSession){
        System.out.println("123");
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        int totalRow = regInfoService.countCheckListByFlag("已审核报告"); //得到未安排预约的总数量；
        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        User u = (User)httpSession.getAttribute("user");
        try {
            List<VerifiedTab> writtedTabs = regInfoService.getVerifiedListByFlagLocal("已审核报告", currIndex, pageSize,httpSession);
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
     * @Description: 报告退回
     * @Author: Shalldid
     * @Date: Created in
     * @Return:
     **/
    @RequestMapping(value="/backToWriteReportLocal")
    @ResponseBody
    public String backToWriteReport(@RequestParam("checkNum") String checknum,
                                    HttpSession httpSession) throws IOException {
        try{
            int reg_ = regInfoService.updateRegStatusByChecknumLocal("已检查",checknum);
            int rep_ =reportService.updateReportStatusByChecknum("已检查",checknum);
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



    /**
     * @Description: 获取已检查信息(本地)
     * @Author: Shalldid
     * @Date: Created in 10:47 2018-07-04
     * @Return:
     **/
    @RequestMapping(value="getAllRegisterInfo", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getAllRegisterInfo (Pagination p, HttpSession httpSession) {
        System.out.println("123");
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        System.out.println("333");
        int totalRow = registerInfoLocalService.countCheckListByFlag("已检查");
        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
//        List<RegisterInfoInner> registerInfoInners = new ArrayList<RegisterInfoInner>();
        System.out.println("123");
        List<CheckedTab> checkedTabs = registerInfoLocalService.getCheckedListByFlag("已检查", currIndex, pageSize, httpSession);
//        registerInfoInners = registerInfoLocalMapper.getAllInfoByFlag("已检查");
        System.out.println("123");

//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        List<RegisterInfoInnerDto> registerInfoInnerDtoList=new ArrayList<RegisterInfoInnerDto>();
//        for(int i=0;i<registerInfoInners.size();i++)
//        {
//            RegisterInfoInnerDto registerInfoInnerDto=new RegisterInfoInnerDto();
////            System.out.println("1");
//            registerInfoInnerDto.setAddress(registerInfoInners.get(i).getAddress());
////            System.out.println("2");
//            registerInfoInnerDto.setAge(registerInfoInners.get(i).getAge());
////            System.out.println("3");
//            registerInfoInnerDto.setAgetype(registerInfoInners.get(i).getAgetype());
////            System.out.println("4");
//            registerInfoInnerDto.setBedno(registerInfoInners.get(i).getBedno());
////            System.out.println("5");
//            registerInfoInnerDto.setCardno(registerInfoInners.get(i).getCardno());
////            System.out.println("6");
//            registerInfoInnerDto.setChecknum(registerInfoInners.get(i).getChecknum());
////            System.out.println("7");
//            registerInfoInnerDto.setClinicid(registerInfoInners.get(i).getClinicid());
////            System.out.println("8");
//            registerInfoInnerDto.setDeptcode(registerInfoInners.get(i).getDeptcode());
////            System.out.println("9");
//            registerInfoInnerDto.setDeptname(registerInfoInners.get(i).getDeptname());
////            System.out.println("10");
//            registerInfoInnerDto.setExamitemcode(registerInfoInners.get(i).getExamitemcode());
////            System.out.println("11");
//            registerInfoInnerDto.setExamitemname(registerInfoInners.get(i).getExamitemname());
////            System.out.println("12");
//            registerInfoInnerDto.setFlag(registerInfoInners.get(i).getFlag());
////            System.out.println("13");
//            registerInfoInnerDto.setIdentityid(registerInfoInners.get(i).getIdentityid());
////            System.out.println("14");
//            registerInfoInnerDto.setJcbw(registerInfoInners.get(i).getJcbw());
////            System.out.println("15");
//            registerInfoInnerDto.setPatbirthdate(sdf.format(registerInfoInners.get(i).getPatbirthdate()));
////            System.out.println("16");
//            registerInfoInnerDto.setDevcode(registerInfoInners.get(i).getDevcode());
////            System.out.println("17");
//            registerInfoInnerDto.setDevname(registerInfoInners.get(i).getDevname());
////            System.out.println("18");
//            registerInfoInnerDto.setDistrict(registerInfoInners.get(i).getDistrict());
////            System.out.println("19");
//            registerInfoInnerDto.setExamnamecode(registerInfoInners.get(i).getExamnamecode());
////            System.out.println("20");
//            registerInfoInnerDto.setExamnamename(registerInfoInners.get(i).getExamnamename());
////            System.out.println("21");
//            registerInfoInnerDto.setIfusingflag(registerInfoInners.get(i).getIfusingflag());
////            System.out.println("22");
//            registerInfoInnerDto.setIsdicomflag(registerInfoInners.get(i).getIsdicomflag());
////            System.out.println("23");
//            registerInfoInnerDto.setJclb(registerInfoInners.get(i).getJclb());
////            System.out.println("24");
//            registerInfoInnerDto.setJcsf(registerInfoInners.get(i).getJcsf());
////            System.out.println("25");
//            registerInfoInnerDto.setLczd(registerInfoInners.get(i).getLczd());
////            System.out.println("26");
//            registerInfoInnerDto.setIsprint(registerInfoInners.get(i).getIsprint());
////            System.out.println("27");
//            registerInfoInnerDto.setIsregist(registerInfoInners.get(i).getIsregist());
////            System.out.println("28");
//            registerInfoInnerDto.setMakercode(registerInfoInners.get(i).getMakercode());
////            System.out.println("29");
//            registerInfoInnerDto.setMakername(registerInfoInners.get(i).getMakername());
////            System.out.println("30");
//            registerInfoInnerDto.setNewpatient(registerInfoInners.get(i).getNewpatient());
////            System.out.println("31");
//            registerInfoInnerDto.setNowoperator(registerInfoInners.get(i).getNowoperator());
////            System.out.println("32");
//            registerInfoInnerDto.setOperatorcode(registerInfoInners.get(i).getOperatorcode());
////            System.out.println("33");
//            registerInfoInnerDto.setPatgender(registerInfoInners.get(i).getPatgender());
////            System.out.println("34");
//            registerInfoInnerDto.setPatientid(registerInfoInners.get(i).getPatientid());
////            System.out.println("35");
//            registerInfoInnerDto.setPatname(registerInfoInners.get(i).getPatname());
////            System.out.println("36");
//            registerInfoInnerDto.setPatnamepy(registerInfoInners.get(i).getPatnamepy());
////            System.out.println("37");
//            registerInfoInnerDto.setPatroomcode(registerInfoInners.get(i).getPatroomcode());
////            System.out.println("38");
//            registerInfoInnerDto.setPatroomname(registerInfoInners.get(i).getPatroomname());
////            System.out.println("39");
//            registerInfoInnerDto.setPattypecode(registerInfoInners.get(i).getPattypecode());
////            System.out.println("40");
//            registerInfoInnerDto.setPaycount(registerInfoInners.get(i).getPaycount());
////            System.out.println("41");
//            registerInfoInnerDto.setPayflag(registerInfoInners.get(i).getPayflag());
////            System.out.println("42");
//            if(registerInfoInners.get(i).getPhotodate()==null|| "".equals(registerInfoInners.get(i).getPhotodate()))
//                registerInfoInnerDto.setPhotodate("0");
//            else {
//                registerInfoInnerDto.setPhotodate(sdf.format(registerInfoInners.get(i).getPhotodate()));
//            }
////            System.out.println("43");
//            registerInfoInnerDto.setRecordid(registerInfoInners.get(i).getRecordid());
////            System.out.println("44");
//            registerInfoInnerDto.setRegistertime(sdf.format(registerInfoInners.get(i).getRegistertime()));//
////            System.out.println("45");
//            registerInfoInnerDto.setSjmd(registerInfoInners.get(i).getSjmd());
////            System.out.println("46");
//            registerInfoInnerDto.setSqdbh(registerInfoInners.get(i).getSqdbh());
////            System.out.println("47");
//            registerInfoInnerDto.setStudyid(registerInfoInners.get(i).getStudyid());
////            System.out.println("48");
//            registerInfoInnerDto.setTelephone(registerInfoInners.get(i).getTelephone());
////            System.out.println("49");
//            registerInfoInnerDto.setUpdatetime(sdf.format(registerInfoInners.get(i).getUpdatetime()));//
////            System.out.println("50");
//            registerInfoInnerDto.setXuhao(registerInfoInners.get(i).getXuhao());
////            System.out.println("51");
//            registerInfoInnerDto.setYibaoid(registerInfoInners.get(i).getYibaoid());
////            System.out.println("52");
//            registerInfoInnerDtoList.add(registerInfoInnerDto);
////            System.out.println("mmm");
//        }

//        PaginationResult<RegisterInfoInnerDto> paginationResult = new PaginationResult<RegisterInfoInnerDto>();
        PaginationResult<CheckedTab> paginationResult = new PaginationResult<CheckedTab>();
        System.out.println("123");
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(checkedTabs);
//        paginationResult.setList(registerInfoInnerDtoList);
        System.out.println("123");
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);

        System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }
}
