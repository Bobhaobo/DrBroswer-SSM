package org.springmvc.controller;


import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springmvc.dao.*;
import org.springmvc.dto.*;
import org.springmvc.pojo.*;
import org.springmvc.service.*;
import org.springmvc.tool.ReportImageGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping("/baseInfo")
@Controller
public class BaseInfoController {
    @Resource
    private PatientService patientService;
    @Resource
    private ReportService reportService;
    @Resource
    private RegInfoService regInfoService;
    @Resource
    private ReportImageGenerator reportImageGenerator;
    @Resource
    private HisInfoService hisInfoService;
    @Resource
    private UserService userService;
    @Resource
    private HospitalService hospitalService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private AuthListMapper authListMapper;
    @Resource
    private AuthService authService;
    @Resource
    private ClinicService clinicService;


    @RequestMapping(value = "/getPatTypeInfo")
    @ResponseBody
    public String getPatTypeInfo(Pagination p) {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = 6;
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);


        List<BaseInfoPatTypeList> baseInfoPatTypeLists = new ArrayList<BaseInfoPatTypeList>();
        BaseInfoPatTypeList b_1 = new BaseInfoPatTypeList("1", "门诊");
        BaseInfoPatTypeList b_2 = new BaseInfoPatTypeList("2", "住院");
        BaseInfoPatTypeList b_3 = new BaseInfoPatTypeList("3", "急诊");
        BaseInfoPatTypeList b_4 = new BaseInfoPatTypeList("4", "体检");
        BaseInfoPatTypeList b_5 = new BaseInfoPatTypeList("5", "远程");
        BaseInfoPatTypeList b_6 = new BaseInfoPatTypeList("6", "其他");
        baseInfoPatTypeLists.add(b_1);
        baseInfoPatTypeLists.add(b_2);
        baseInfoPatTypeLists.add(b_3);
        baseInfoPatTypeLists.add(b_4);
        baseInfoPatTypeLists.add(b_5);
        baseInfoPatTypeLists.add(b_6);

        PaginationResult<BaseInfoPatTypeList> paginationResult = new PaginationResult<BaseInfoPatTypeList>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(baseInfoPatTypeLists); //!
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);

        return JSON.toJSONString(paginationResult);
    }

        @RequestMapping(value = "/getDepAttribute")
        @ResponseBody
        public String getDepAttributeInfo(Pagination p){
            int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
            int pageSize  = p.getPageSize();
            boolean ifFirst = (p.getPageCurrent() == 1);
            int totalRow = 6;
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);


            List<BaseInfoPatTypeList> baseInfoPatTypeLists = new ArrayList<BaseInfoPatTypeList>();
            BaseInfoPatTypeList b_1 = new BaseInfoPatTypeList("1","临床");
            BaseInfoPatTypeList b_2 = new BaseInfoPatTypeList("2","医技");
            baseInfoPatTypeLists.add(b_1);
            baseInfoPatTypeLists.add(b_2);

            PaginationResult<BaseInfoPatTypeList> paginationResult = new PaginationResult<BaseInfoPatTypeList>();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(baseInfoPatTypeLists); //!
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);

            return JSON.toJSONString(paginationResult);
    }
    @RequestMapping(value = "/getPersonAttribute")
    @ResponseBody
    public String getPersonAttribute(Pagination p) {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = 6;
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);


        List<BaseInfoPatTypeList> baseInfoPatTypeLists = new ArrayList<BaseInfoPatTypeList>();
        BaseInfoPatTypeList b_1 = new BaseInfoPatTypeList("002", "医生");
        BaseInfoPatTypeList b_2 = new BaseInfoPatTypeList("003", "护士");
        BaseInfoPatTypeList b_3 = new BaseInfoPatTypeList("004", "主任");
        BaseInfoPatTypeList b_4 = new BaseInfoPatTypeList("005", "技师");
        baseInfoPatTypeLists.add(b_1);
        baseInfoPatTypeLists.add(b_2);
        baseInfoPatTypeLists.add(b_3);
        baseInfoPatTypeLists.add(b_4);

        PaginationResult<BaseInfoPatTypeList> paginationResult = new PaginationResult<BaseInfoPatTypeList>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(baseInfoPatTypeLists); //!
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);

        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/getRoomInfo")
    @ResponseBody
    public String getRoomInfo(Pagination p) {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = 6;
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);


        List<BaseInfoPatTypeList> baseInfoPatTypeLists = new ArrayList<BaseInfoPatTypeList>();
        BaseInfoPatTypeList b_1 = new BaseInfoPatTypeList("001", "一病房");
        BaseInfoPatTypeList b_2 = new BaseInfoPatTypeList("002", "二病房");
        BaseInfoPatTypeList b_3 = new BaseInfoPatTypeList("003", "三病房");
        BaseInfoPatTypeList b_4 = new BaseInfoPatTypeList("004", "四病房");
        baseInfoPatTypeLists.add(b_1);
        baseInfoPatTypeLists.add(b_2);
        baseInfoPatTypeLists.add(b_3);
        baseInfoPatTypeLists.add(b_4);

        PaginationResult<BaseInfoPatTypeList> paginationResult = new PaginationResult<BaseInfoPatTypeList>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(baseInfoPatTypeLists); //!
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);

        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/getPersonPart")
    @ResponseBody
    public String getPersonPart(Pagination p) {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = 6;
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);


        List<BaseInfoPatTypeList> baseInfoPatTypeLists = new ArrayList<BaseInfoPatTypeList>();
        BaseInfoPatTypeList b_1 = new BaseInfoPatTypeList("CT", "腰椎");
        BaseInfoPatTypeList b_2 = new BaseInfoPatTypeList("CT", "颈椎");
        BaseInfoPatTypeList b_3 = new BaseInfoPatTypeList("CT", "骶尾骨");
        BaseInfoPatTypeList b_4 = new BaseInfoPatTypeList("CT", "髋关节");
        BaseInfoPatTypeList b_5 = new BaseInfoPatTypeList("CT", "五官颈部");
        BaseInfoPatTypeList b_6 = new BaseInfoPatTypeList("CT", "头颅");
        BaseInfoPatTypeList b_7 = new BaseInfoPatTypeList("CT", "上腹部");
        BaseInfoPatTypeList b_8 = new BaseInfoPatTypeList("CT", "泌尿");
        BaseInfoPatTypeList b_9 = new BaseInfoPatTypeList("CT", "四肢关节");
        BaseInfoPatTypeList b_10 = new BaseInfoPatTypeList("CT", "心血管");
        BaseInfoPatTypeList b_11 = new BaseInfoPatTypeList("CT", "胸部");
        BaseInfoPatTypeList b_12 = new BaseInfoPatTypeList("CT", "膝关节");
        BaseInfoPatTypeList b_13 = new BaseInfoPatTypeList("CT", "椎体");
        BaseInfoPatTypeList b_14 = new BaseInfoPatTypeList("CT", "内分泌");
        BaseInfoPatTypeList b_15 = new BaseInfoPatTypeList("CT", "颈部");
        BaseInfoPatTypeList b_16 = new BaseInfoPatTypeList("CT", "腹部");
        baseInfoPatTypeLists.add(b_1);
        baseInfoPatTypeLists.add(b_2);
        baseInfoPatTypeLists.add(b_3);
        baseInfoPatTypeLists.add(b_4);
        baseInfoPatTypeLists.add(b_5);
        baseInfoPatTypeLists.add(b_6);
        baseInfoPatTypeLists.add(b_7);
        baseInfoPatTypeLists.add(b_8);
        baseInfoPatTypeLists.add(b_9);
        baseInfoPatTypeLists.add(b_10);
        baseInfoPatTypeLists.add(b_11);
        baseInfoPatTypeLists.add(b_12);
        baseInfoPatTypeLists.add(b_13);
        baseInfoPatTypeLists.add(b_14);
        baseInfoPatTypeLists.add(b_15);
        baseInfoPatTypeLists.add(b_16);

        PaginationResult<BaseInfoPatTypeList> paginationResult = new PaginationResult<BaseInfoPatTypeList>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(baseInfoPatTypeLists); //!
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);

        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/getCheckResult")
    @ResponseBody
    public String getCheckResult(Pagination p) {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = 6;
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);


        List<CheckResult> checkResults = new ArrayList<CheckResult>();
        CheckResult checkResult1=new CheckResult("01","正常","阳性");
        CheckResult checkResult2=new CheckResult("02","肿瘤","阴性");
        CheckResult checkResult3=new CheckResult("03","炎症","阴性");
        CheckResult checkResult4=new CheckResult("04","外伤","阴性");
        CheckResult checkResult5=new CheckResult("05","慢性","阴性");
        CheckResult checkResult6=new CheckResult("06","先天","阴性");
        CheckResult checkResult7=new CheckResult("07","其他","阴性");
        checkResults.add(checkResult1);
        checkResults.add(checkResult2);
        checkResults.add(checkResult3);
        checkResults.add(checkResult4);
        checkResults.add(checkResult5);
        checkResults.add(checkResult6);
        checkResults.add(checkResult7);


        PaginationResult<CheckResult> paginationResult = new PaginationResult<CheckResult>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(checkResults); //!
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);

        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/getPatInfo")
    @ResponseBody
    public String getPatInfo(Pagination p)
    {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = patientService.account();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        List<Patient> patients =new ArrayList<Patient>();
        patients=patientService.getAll();
        PaginationResult<Patient> paginationResult = new PaginationResult<Patient>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(patients);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/addNewUser")
    @ResponseBody
    public  String setUserInfo(@RequestParam("user_id") String user_id,
                              @RequestParam("user_pass") String user_pass,
                              @RequestParam("user_pass_confirm") String user_pass_confirm,
                              @RequestParam("user_name") String user_name,
                              @RequestParam("user_hos") String user_hos,
                               @RequestParam("user_dept") String user_dept,
                              @RequestParam("user_auth") String user_auth,
                              HttpSession httpSession)throws IOException {
        //
        User u = new User();
        u.setUsername(user_id);
        u.setId(user_id);
        u.setPassword(user_pass);
        u.setName(user_name);
        u.setDept(user_dept);
        u.setRegtime(new Date());
        u.setAuth(user_auth);
        try{
            userService.addNewUser(u);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "0";
        }

        @RequestMapping(value = "/getHosInfo")
        @ResponseBody
    public String getHosInfo(Pagination p)
    {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = hospitalService.account2();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        List<Hospital> hospitals = new ArrayList<Hospital>();
        try{
            hospitals = hospitalService.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        PaginationResult<Hospital> paginationResult = new PaginationResult<Hospital>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(hospitals);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value="/getDeptInfo")
    @ResponseBody
    public  String getDeptInfo(Pagination p)
    {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = hospitalService.account1();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        List<Department> departments = new ArrayList<Department>();
        try{
            departments = hospitalService.getall();
        }catch (Exception e){
            e.printStackTrace();
        }
        PaginationResult<Department> paginationResult = new PaginationResult<Department>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(departments);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/getDeviceInfo")
    @ResponseBody
    public String getDeviceInfo(Pagination p) {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = 6;
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);

        List<DeviceInfo> deviceInfos = new ArrayList<DeviceInfo>();
        DeviceInfo deviceInfo1=new DeviceInfo("008","CT","HRI_CTAWP66563");
        DeviceInfo deviceInfo2=new DeviceInfo("009","MR","MRC48512");
        DeviceInfo deviceInfo3=new DeviceInfo("010","CTS","CT222222");
        DeviceInfo deviceInfo4=new DeviceInfo("011","CR","CR222222");
        DeviceInfo deviceInfo5=new DeviceInfo("012","DX","DX333333");
        DeviceInfo deviceInfo6=new DeviceInfo("013","MR","MR121212");
        DeviceInfo deviceInfo7=new DeviceInfo("014","CR","CR121318");
        DeviceInfo deviceInfo8=new DeviceInfo("015","CT","CT123334");
        DeviceInfo deviceInfo9=new DeviceInfo("016","DX","DX1313331");
        deviceInfos.add(deviceInfo1);
        deviceInfos.add(deviceInfo2);
        deviceInfos.add(deviceInfo3);
        deviceInfos.add(deviceInfo4);
        deviceInfos.add(deviceInfo5);
        deviceInfos.add(deviceInfo6);
        deviceInfos.add(deviceInfo7);
        deviceInfos.add(deviceInfo8);
        deviceInfos.add(deviceInfo9);

        PaginationResult<DeviceInfo> paginationResult = new PaginationResult<DeviceInfo>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(deviceInfos); //!
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);

        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/getCheckAbout")
    @ResponseBody
    public String getCheckAbout(Pagination p)
    {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
            int totalRow = hospitalService.account();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        List<Exam> exams = new ArrayList<Exam>();
            exams = hospitalService.get();
        PaginationResult<Exam> paginationResult = new PaginationResult<Exam>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(exams);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public String getUserInfo(Pagination p)
    {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> list = userMapper.selectAllUser();
        List<UserListTab> userListTabs = new ArrayList<UserListTab>();
        for(User u : list){
            UserListTab userListTab = new UserListTab();
            userListTab.setUser_auth(u.getAuth());
            String str=u.getAuth();
            String[ ] strs=str.split(",");
            StringBuilder stringBuilder=new StringBuilder();
            int i=0;
            for(String s:strs)
            {
                i++;
                if(i==strs.length)
                {
                    stringBuilder.append(authService.getAuthNameByCode(s));
                }
                else
                {
                    stringBuilder.append(authService.getAuthNameByCode(s) + ",");
                }
            }
            userListTab.setUser_auth(stringBuilder.toString());
            userListTab.setUser_dept(departmentMapper.getDeptNamebyDeptid(u.getDept()));
            userListTab.setUser_hos(hospitalMapper.getHosNameByHosId(departmentMapper.getHosIdbyDeptid(u.getDept())));
            userListTab.setUser_id(u.getId());
            userListTab.setUser_name(u.getName());
            Date date = u.getRegtime();
            String rightdate =simpleDateFormat.format(date);
            userListTab.setUser_reg(rightdate);
            userListTabs.add(userListTab);
        }
        int totalRow = userListTabs.size();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        PaginationResult<UserListTab> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(userListTabs);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        return JSON.toJSONString(paginationResult);
    }


    @RequestMapping(value = "/getClinicInfo")
    @ResponseBody
    public String getClinicInfo(Pagination p)
    {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        System.out.println(pageSize);
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = clinicService.account();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        System.out.println("123");
        System.out.println((totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1));
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        System.out.println("321");
        List<Clinic> clinics =new ArrayList<Clinic>();
        System.out.println("######################");
        clinics=clinicService.getAll();
        System.out.println(clinics);
        PaginationResult<Clinic> paginationResult = new PaginationResult<Clinic>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(clinics);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }


    @RequestMapping(value = "/getClinicInfo2")
    @ResponseBody
    public String getClinicInfo2(Pagination p,
                                 @RequestParam ("idcard") String idcard,
                                 @RequestParam("patname123") String patname,
                                 @RequestParam("patientid123") String patientid,
                                 @RequestParam("updatetime123") String updatetime,
                                 @RequestParam("entity123") String entity)throws ParseException
    {
        System.out.println("%%%%%%%%%%");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(idcard);
        System.out.println(patname);
        System.out.println(patientid);
        System.out.println(updatetime);
        if(idcard!=null &&  !("".equals(idcard))) {
            System.out.println("lalala");
            int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
            int pageSize = p.getPageSize();
            boolean ifFirst = (p.getPageCurrent() == 1);
            int totalRow = clinicService.account();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            System.out.println(totalPage);
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics = clinicService.getAllInfo1(idcard);
            System.out.println(clinics);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            PaginationResult<ClinicDto> paginationResult = new PaginationResult<ClinicDto>();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(clinicDtoList);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println("###################");
            System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }else if(patname!=null &&  !("".equals(patname))){
            System.out.println("hahaha");
            int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
            int pageSize = p.getPageSize();
            boolean ifFirst = (p.getPageCurrent() == 1);
            int totalRow = clinicService.account();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics = clinicService.getAllInfoByPatname(patname);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            System.out.println(clinicDtoList);
            PaginationResult<ClinicDto> paginationResult = new PaginationResult<ClinicDto>();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(clinicDtoList);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println("###################");
            System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }else if(patientid!=null && !("".equals(patientid))){
            System.out.println("!@##$%%^^^^&");
            int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
            int pageSize = p.getPageSize();
            boolean ifFirst = (p.getPageCurrent() == 1);
            int totalRow = clinicService.account();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics=clinicService.getAllInfoByPatientid(patientid);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            PaginationResult<ClinicDto> paginationResult = new PaginationResult<ClinicDto>();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(clinicDtoList);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println("###################");
            System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }else if(updatetime!=null && !("".equals(updatetime))){
            int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
            int pageSize = p.getPageSize();
            boolean ifFirst = (p.getPageCurrent() == 1);
            int totalRow = clinicService.account();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
            System.out.println("123");
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            System.out.println("123");
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics=clinicService.getAllInfoByUpdateTime(updatetime);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            PaginationResult<ClinicDto> paginationResult = new PaginationResult<ClinicDto>();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(clinicDtoList);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println("###################");
            System.out.println("s");
            System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }else if(entity!=null && !("".equals(entity))){
            System.out.println("lalala");
            System.out.println(entity);
            int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
            int pageSize = p.getPageSize();
            boolean ifFirst = (p.getPageCurrent() == 1);
            int totalRow = clinicService.account();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics=clinicService.getAllInfoByentity(entity);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            System.out.println(clinicDtoList);
            PaginationResult<ClinicDto> paginationResult = new PaginationResult<ClinicDto>();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(clinicDtoList);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println("###################");
            System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }
        else{
            return JSON.toJSONString(0);
        }
    }

    @RequestMapping(value = "/getClinicInfo3", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getClinicInfo3(Pagination p,
                                 @RequestParam ("idcard") String idcard,
                                 @RequestParam("patname123") String patname,
                                 @RequestParam("patientid123") String patientid,
                                 @RequestParam("updatetime123") String updatetime,
                                 @RequestParam("entity123") String entity)throws ParseException
    {
        System.out.println("%%%%%%%%%%");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(idcard);
        System.out.println(patname);
        System.out.println(patientid);
        System.out.println(updatetime);
        if(idcard!=null &&  !("".equals(idcard))) {
            System.out.println("lalala");
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics = clinicService.getAllInfo1(idcard);
            System.out.println(clinics);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            System.out.println("###################");
            System.out.println(JSON.toJSONString(clinicDtoList));
            return JSON.toJSONString(clinicDtoList);
        }else if(patname!=null &&  !("".equals(patname))){
            System.out.println("hahaha");
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics = clinicService.getAllInfoByPatname(patname);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            System.out.println(clinicDtoList);
            System.out.println("###################");
            System.out.println(JSON.toJSONString(clinicDtoList));
            return JSON.toJSONString(clinicDtoList);
        }else if(patientid!=null && !("".equals(patientid))){
            System.out.println("!@##$%%^^^^&");
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics=clinicService.getAllInfoByPatientid(patientid);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            System.out.println("###################");
            System.out.println(JSON.toJSONString(clinicDtoList));
            return JSON.toJSONString(clinicDtoList);
        }else if(updatetime!=null && !("".equals(updatetime))){
            System.out.println("123");
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics=clinicService.getAllInfoByUpdateTime(updatetime);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            System.out.println("###################");
            System.out.println("s");
            System.out.println(JSON.toJSONString(clinicDtoList));
            return JSON.toJSONString(clinicDtoList);
        }else if(entity!=null && !("".equals(entity))){
            System.out.println("lalala");
            System.out.println(entity);
            List<Clinic> clinics = new ArrayList<Clinic>();
            clinics=clinicService.getAllInfoByentity(entity);
            List<ClinicDto> clinicDtoList=new ArrayList<ClinicDto>();
            for(int i=0;i< clinics.size();i++){
                ClinicDto c=new ClinicDto();
                c.setAddress(clinics.get(i).getAddress());
                c.setAge(clinics.get(i).getAge());
                c.setClinicdoc(clinics.get(i).getClinicdoc());
                c.setEntity(clinics.get(i).getEntity());
                c.setFamilyhis(clinics.get(i).getFamilyhis());
                c.setIdcard(clinics.get(i).getIdcard());
                c.setMainsuit(clinics.get(i).getMainsuit());
                c.setMaritalhis(clinics.get(i).getMaritalhis());
                c.setPastillnesshis(clinics.get(i).getPastillnesshis());
                c.setPatbirthdate(sdf.format(clinics.get(i).getPatbirthdate()));
                c.setPatgender(clinics.get(i).getPatgender());
                c.setPatientid(clinics.get(i).getPatientid());
                c.setPatname(clinics.get(i).getPatname());
                c.setPersonalhis(clinics.get(i).getPersonalhis());
                c.setPresentillnesshis(clinics.get(i).getPresentillnesshis());
                c.setSpecialitycheck(clinics.get(i).getSpecialitycheck());
                c.setTelephone(clinics.get(i).getTelephone());
                c.setTentativediagnosis(clinics.get(i).getTentativediagnosis());
                c.setUpdatetime(sdf.format(clinics.get(i).getUpdatetime()));
                c.setYibaoid(clinics.get(i).getYibaoid());
                clinicDtoList.add(c);
            }
            System.out.println(clinicDtoList);
            System.out.println("###################");
            System.out.println(JSON.toJSONString(clinicDtoList));
            return JSON.toJSONString(clinicDtoList);
        }
        else{
            return JSON.toJSONString(0);
        }
    }

    @RequestMapping(value = "/getClinicInfo1")
    @ResponseBody
    public String getClinicInfo1(Pagination p,
                                 @RequestParam ("idcard") String idcard
                               )throws ParseException {
//        System.out.println("%%%%%%%%%%");
        System.out.println(idcard);
        System.out.println("lalala");
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = clinicService.account();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        List<Clinic> clinics = new ArrayList<Clinic>();
        clinics = clinicService.getAllInfo1(idcard);
        System.out.println(clinics);
        PaginationResult<Clinic> paginationResult = new PaginationResult<Clinic>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(clinics);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        System.out.println("###################");
        System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }
}
