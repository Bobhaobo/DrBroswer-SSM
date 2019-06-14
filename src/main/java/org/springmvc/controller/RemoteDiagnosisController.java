package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springmvc.dao.*;
import org.springmvc.dao_junior.DicomWorkListJuniorMapper;
import org.springmvc.dao_junior.RegisterInfoJuniorMapper;
import org.springmvc.dto.*;
import org.springmvc.pojo.*;
import org.springmvc.pojo_inner.RegisterInfoInner;
import org.springmvc.pojo_junior.DicomWorkListJunior;
import org.springmvc.pojo_junior.RegisterInfoJunior;
import org.springmvc.service.*;
import org.springmvc.tool.DataSourceContextHolder;
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
import java.util.ArrayList;
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

    @Resource
    private RemoteRegisterMapper remoteRegisterMapper;

    @Resource
    private AllocateMapper allocatemapper;

    @Resource
    private RelationMapper relationMapper;


    @Resource
    private ReportMapper reportMapper;


    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RegisterInfoInnerService registerInfoInnerService;

    @Resource
    private TemporaryReportMapper temporaryReportMapper;

    @Resource
    private RemoteReportMapper remotereportMapper;

    @Resource
    private RegisterInfoJuniorMapper registerInfoJuniorMapper;

    @Resource
    private HospitalMapper hospitalMapper;

    @Resource
    private DicomWorkListJuniorMapper dicomWorkListJuniorMapper;

    @Resource
    private CompareJuniorMapper compareJuniorMapper;

    @Resource
    private PatientMapper patientMapper;

    /**
     * @Description: 远程诊断登记(免登记)
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

//        r.setUrl("UpLoadFile://" + checkNum + "&" + u.getId() + "&"+ userService.getHosIdOfUser(u.getDept()));
        r.setUrl("UpLoadFile://" +"2" + "&"+userService.getHosIdOfUser(u.getDept()) + "&" + u.getUsername());
        return JSON.toJSONString(r);
    }

    /**
     * @Description: 远程诊断登记（批量）
     * @Author: Shalldid
     * @Date: Created in 11:31 2018-05-10
     * @Return:
     **/
    @RequestMapping(value = "/remote_register_batch",method = RequestMethod.POST)
    @ResponseBody
    public String registerRemoteDiagnosisBatch(@RequestParam("CheckNum") String checknum,
                                          HttpSession httpSession) throws Exception{
        System.out.println(checknum);

        String[] aa = checknum.split("\\,");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyyMMdd");

        RemoteRegisterCallBack r = new RemoteRegisterCallBack();
        String s="";

        User u = (User) httpSession.getAttribute("user");

        for(String a:aa) {
            String c = a.replaceAll("[`qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
            System.out.println(c);

            DicomWorkListJunior dicomWorkListJunior = dicomWorkListJuniorMapper.selectByAccessionN(c);
            RegisterInfoJunior registerInfoJunior = registerInfoJuniorMapper.selectByRecordId(c);
            RemoteRegister remoteRegister = new RemoteRegister();
            remoteRegister.setFlag("未上传图像");
            remoteRegister.setId(UUID.randomUUID().toString());//
            System.out.println(registerInfoJunior);
            System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
            if(registerInfoJunior.getIdentityid()==null || "".equals(registerInfoJunior.getIdentityid())){
                remoteRegister.setIdcard(dicomWorkListJunior.getPatientid()+dicomWorkListJunior.getPatientid()+"00");
                System.out.println("ca");
                System.out.println(dicomWorkListJunior.getPatientid()+dicomWorkListJunior.getPatientid()+"00");
            }
            else {
                remoteRegister.setIdcard(registerInfoJunior.getIdentityid());
            }
            System.out.println(dicomWorkListJunior);
            remoteRegister.setModality(dicomWorkListJunior.getModality());
            remoteRegister.setTagpatientid("");
            remoteRegister.setRegdate(new Date());
            System.out.println(dicomWorkListJunior.getStartdate().substring(0, 4) + "-" + dicomWorkListJunior.getStartdate().substring(4, 6) + "-" + dicomWorkListJunior.getStartdate().substring(6, 8));
            remoteRegister.setStudydate(simpleDateFormat.parse(dicomWorkListJunior.getStartdate().substring(0, 4) + "-" + dicomWorkListJunior.getStartdate().substring(4, 6) + "-" + dicomWorkListJunior.getStartdate().substring(6, 8)));
//            User u = (User) httpSession.getAttribute("user");
            remoteRegister.setRemotehos(userService.getHosIdOfUser(u.getDept()));
            remoteRegister.setChecknum(c);
            String result = "";
            try {

                int patient_insert_status;
                String id="";
                id=registerInfoJunior.getIdentityid();
                if(id==null || "".equals(id)){
                    int patient_insert_status1 = patientService.insertOrUpdatePat(registerInfoJunior.getPatname(), dicomWorkListJunior.getPatientid()+dicomWorkListJunior.getPatientid()+"00" , registerInfoJunior.getPatgender(), sdf.format(registerInfoJunior.getPatbirthdate()), registerInfoJunior.getAddress(), "", registerInfoJunior.getTelephone());
                    patient_insert_status=patient_insert_status1;
                }
                else{
                    int patient_insert_status1 = patientService.insertOrUpdatePat(registerInfoJunior.getPatname(), registerInfoJunior.getIdentityid(), registerInfoJunior.getPatgender(), sdf.format(registerInfoJunior.getPatbirthdate()), registerInfoJunior.getAddress(), "", registerInfoJunior.getTelephone());
                    patient_insert_status=patient_insert_status1;
                }

                int remote_register_status = remoteRegisterService.insertNewRegister(remoteRegister);
//                int patient_insert_status = patientService.insertOrUpdatePat(registerInfoJunior.getPatname(), registerInfoJunior.getIdentityid(), registerInfoJunior.getPatgender(), sdf.format(registerInfoJunior.getPatbirthdate()), registerInfoJunior.getAddress(), "", registerInfoJunior.getTelephone());

                CompareJunior compareJunior=new CompareJunior(UUID.randomUUID().toString(),registerInfoJunior.getRecordid(),departmentMapper.getHosIdbyDeptid(u.getDept()));
                int compare_junior_satatus=compareJuniorMapper.insert(compareJunior);

                if (remote_register_status == 1 && patient_insert_status == 1 &&compare_junior_satatus==1) {
                    result = c;
                }
            } catch (Exception e) {
                result = "0";
                e.printStackTrace();
            }

            if(result=="0"){
                r.setInfo("0");
                break;
            }

//            RemoteRegisterCallBack r = new RemoteRegisterCallBack();
            r.setInfo(result);

            s+= "&" + dicomWorkListJunior.getPatientid() +"-"+ c + "-" + simpleDateFormat1.format(remoteRegister.getStudydate());
//            r.setUrl("UpLoadFile://" + userService.getHosIdOfUser(u.getDept()) + "/" + u.getUsername() + "/" + checknum + "&" + remoteRegister.getStudydate());
        }
        r.setUrl("UpLoadFile://" +"1"+ "&"+userService.getHosIdOfUser(u.getDept()) + "&" + u.getUsername() +s);

        System.out.println(r.getUrl());
        return JSON.toJSONString(r);
    }
    /**
     * @Description: 远程诊断登记（单个）
     * @Author: Shalldid
     * @Date: Created in 11:31 2018-05-10
     * @Return:
     **/
    @RequestMapping(value = "/remote_register_batch_single",method = RequestMethod.POST)
    @ResponseBody
    public String registerRemoteDiagnosisSingle(@RequestParam("CheckNum") String checknum,
                                          HttpSession httpSession) throws Exception{
        System.out.println(checknum);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyyMMdd");

        RemoteRegisterCallBack r = new RemoteRegisterCallBack();
//        String s="";

        User u = (User) httpSession.getAttribute("user");


            String c = checknum.replaceAll("[`qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
            System.out.println(c);

            DicomWorkListJunior dicomWorkListJunior = dicomWorkListJuniorMapper.selectByAccessionN(c);
            RegisterInfoJunior registerInfoJunior = registerInfoJuniorMapper.selectByRecordId(c);
            RemoteRegister remoteRegister = new RemoteRegister();
            remoteRegister.setFlag("未上传图像");
            remoteRegister.setId(UUID.randomUUID().toString());//
            System.out.println(registerInfoJunior);
            remoteRegister.setIdcard(registerInfoJunior.getIdentityid());
            System.out.println(dicomWorkListJunior);
            remoteRegister.setModality(dicomWorkListJunior.getModality());
            remoteRegister.setTagpatientid("");
            remoteRegister.setRegdate(new Date());
            System.out.println(dicomWorkListJunior.getStartdate().substring(0, 4) + "-" + dicomWorkListJunior.getStartdate().substring(4, 6) + "-" + dicomWorkListJunior.getStartdate().substring(6, 8));
            remoteRegister.setStudydate(simpleDateFormat.parse(dicomWorkListJunior.getStartdate().substring(0, 4) + "-" + dicomWorkListJunior.getStartdate().substring(4, 6) + "-" + dicomWorkListJunior.getStartdate().substring(6, 8)));
//            User u = (User) httpSession.getAttribute("user");
            remoteRegister.setRemotehos(userService.getHosIdOfUser(u.getDept()));
            remoteRegister.setChecknum(c);
            String result = "";
            try {
                int remote_register_status = remoteRegisterService.insertNewRegister(remoteRegister);
                int patient_insert_status = patientService.insertOrUpdatePat(registerInfoJunior.getPatname(), registerInfoJunior.getIdentityid(), registerInfoJunior.getPatgender(), sdf.format(registerInfoJunior.getPatbirthdate()), registerInfoJunior.getAddress(), "", registerInfoJunior.getTelephone());

                CompareJunior compareJunior=new CompareJunior(UUID.randomUUID().toString(),registerInfoJunior.getRecordid(),departmentMapper.getHosIdbyDeptid(u.getDept()));
                int compare_junior_satatus=compareJuniorMapper.insert(compareJunior);

                if (remote_register_status == 1 && patient_insert_status == 1 &&compare_junior_satatus==1) {
                    result = c;
                }
            } catch (Exception e) {
                result = "0";
                e.printStackTrace();
            }
//            RemoteRegisterCallBack r = new RemoteRegisterCallBack();
            r.setInfo(result);

//            s+= "&" + c + "-" + simpleDateFormat1.format(remoteRegister.getStudydate());
//            r.setUrl("UpLoadFile://" + userService.getHosIdOfUser(u.getDept()) + "/" + u.getUsername() + "/" + checknum + "&" + remoteRegister.getStudydate());

        r.setUrl("UpLoadFile://" +"2"+ "&"+userService.getHosIdOfUser(u.getDept()) + "&" + u.getUsername());

        System.out.println(r.getUrl());
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
//        List<RemoteWaitForReportTab> remoteWaitForReportTabs = remoteRegisterService.getWaitForReprotByFlag(currIndex, pageSize, httpSession);
        List<RemoteWrittedReportTab> l = remoteRegisterService.getBackWaitForReprotByFlag(currIndex, pageSize, httpSession);


//        int totalRow = remoteWaitForReportTabs.size();

        int totalRow = l.size();

//        System.out.println(pageSize);
//        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
//        PaginationResult<RemoteWaitForReportTab> paginationResult = new PaginationResult();
        PaginationResult<RemoteWrittedReportTab> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(l);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
//        System.out.println("123");
        System.out.println(JSON.toJSONString(paginationResult));
//        System.out.println("321");
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
                               @RequestParam("suggestion") String suggestion,
                               HttpSession httpSession) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        User u = (User)httpSession.getAttribute("user");
        RemoteRegister r = remoteRegisterService.getRemoteRegisterByCheckNum(checknum);
        String report_path = reportImageGenerator.outputReport(checknum, hosName, r.getModality(), pat_Name, pat_gender, pat_age, deptName, clinicId, bedNo, jcbw,
                examDesc, examDiagnosis, u.getName(), "", sdf.format(new Date()));
        RemoteReport report = new RemoteReport(UUID.randomUUID().toString(),checknum,clinicId,pat_Name,pat_gender,pat_age,deptName,bedNo,jcbw,new Date(),sfyangxing,
                r.getModality(),report_path,u.getId(),examDesc,examDiagnosis,r.getRemotehos(),null,"",suggestion);
        System.out.println(suggestion);


        TemporaryReport temporaryReport=new TemporaryReport(UUID.randomUUID().toString(),report.getId(),checknum,report_path,u.getUsername(),u.getName(),new Date());
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
            if(remoteReportService.getReportByChecknum(report.getChecknum())!=null){
                remoteReportService.deleteReportById(remoteReportService.getReportByChecknum(report.getChecknum()).getId());
            }
//            int report_ = remoteReportService.insertNewReport(report);
            int report_ = remotereportMapper.insertAdd(report);
            int reg_ = remoteRegisterService.updateFlagByCheckNum("已写报告", checknum);
            int temporary_report=remoteReportService.insertNewReportToTemporaryReport(temporaryReport);
            if(report_ == 1 && reg_ == 1 && temporary_report==1){
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

    @RequestMapping(value = "/getHadVerified")
    @ResponseBody
    public String getHadVerified(Pagination p, HttpSession httpSession){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        List<RemoteVerifiedReportTab> l = remoteRegisterService.getVerifiedReportByFlag(currIndex, pageSize, httpSession);
        int totalRow = l.size();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        PaginationResult<RemoteVerifiedReportTab> paginationResult = new PaginationResult();
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
     * @Description: 获取远程诊断所有患者
     * @Author: Shalldid
     * @Date: Created in 15:12 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/getAllRemotePat",method = RequestMethod.POST)
    @ResponseBody
    public String getAllRemotePat(Pagination p, HttpSession httpSession){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        List<RemoteRegister> l = remoteRegisterMapper.getAll();

        List<RemoteFlowDto> remoteFlowDtos=new ArrayList<RemoteFlowDto>();

        for(RemoteRegister r:l){
            RemoteFlowDto remoteFlowDto=new RemoteFlowDto();
            remoteFlowDto.setAge(patientMapper.selectByPrimaryKey(r.getIdcard()).getAge());
            remoteFlowDto.setChecknum(r.getChecknum());
            remoteFlowDto.setFlag(r.getFlag());
            remoteFlowDto.setModality(r.getModality());
            remoteFlowDto.setName(patientMapper.selectByPrimaryKey(r.getIdcard()).getPatname());
            remoteFlowDto.setPattype(r.getPattype());
            remoteFlowDto.setRemotehos(r.getRemotehos());
            remoteFlowDto.setSex(patientMapper.selectByPrimaryKey(r.getIdcard()).getPatgender());
            remoteFlowDtos.add(remoteFlowDto);
        }

        int totalRow = l.size();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
//        int totalPage = 1;
        PaginationResult<RemoteFlowDto> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(remoteFlowDtos);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }

    /**
     * @Description: flow_detail.js获取修改数量
     * @Author: Shalldid
     * @Date: Created in 15:12 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/loadModifyCount",method = RequestMethod.POST,produces="text/html; charset=UTF-8")
    @ResponseBody
    public String loadModifyCount(Pagination p, HttpSession httpSession,
                                         @RequestParam("checknum") String checknum){

        System.out.println(checknum);
        int count=temporaryReportMapper.selectCountBycheckNum(checknum);
        System.out.println(count);
        return JSON.toJSONString(count);
    }



    /**
     * @Description: flow_detail.js获取单个患者信息
     * @Author: Shalldid
     * @Date: Created in 15:12 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/getRemotePatByCheckNum",method = RequestMethod.POST,produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getRemotePatByCheckNum(Pagination p, HttpSession httpSession,
                                         @RequestParam("checknum") String checknum){
        List<RemoteRegister> rr = remoteRegisterMapper.getRemoteByCheckNum(checknum);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String age = "";
        String modality="";
        String patname="";
        String pattype="";
        String remotehos="";
        String status="";
        String sex="";
        int i=1;

        for(RemoteRegister r:rr){
            age=patientMapper.selectByPrimaryKey(r.getIdcard()).getAge()+patientMapper.selectByPrimaryKey(r.getIdcard()).getAgetype();
            modality=r.getModality();
            patname=patientMapper.selectByPrimaryKey(r.getIdcard()).getPatname();
            pattype=r.getPattype();
            remotehos=r.getRemotehos();
            status=r.getFlag();
            sex=patientMapper.selectByPrimaryKey(r.getIdcard()).getPatgender();
            if(i==1)
                break;
        }

        List<TemporaryReport> temporaryReports=temporaryReportMapper.selectListBycheckNum(checknum);

        List<TemporaryReportDto> temporaryReportDtos=new ArrayList<TemporaryReportDto>();

        for(TemporaryReport t:temporaryReports){
            TemporaryReportDto temporaryReportDto=new TemporaryReportDto();
            if(t.getVerifyupdatetime()==null || "".equals(sdf.format(t.getVerifyupdatetime()))){
                temporaryReportDto.setVerifyupdatetime(null);
            }else {
                temporaryReportDto.setVerifyupdatetime(sdf.format(t.getVerifyupdatetime()));
            }
            temporaryReportDto.setVerifydocname(t.getVerifydocname());
            temporaryReportDto.setVerifydoccode(t.getVerifydoccode());
            temporaryReportDto.setChecknum(t.getChecknum());
            temporaryReportDto.setId(t.getId());
            temporaryReportDto.setRemoteReportId(t.getRemoteReportId());
            temporaryReportDto.setReportImagePath(t.getReportImagePath());
            temporaryReportDto.setWritedoccode(t.getWritedoccode());
            temporaryReportDto.setWritedocname(t.getWritedocname());
            temporaryReportDto.setWriteupdatetime(sdf.format(t.getWriteupdatetime()));
            temporaryReportDtos.add(temporaryReportDto);
        }


        FlowDetailResult<TemporaryReportDto> temporaryReportFlowDetailResult=new FlowDetailResult<TemporaryReportDto>();
        temporaryReportFlowDetailResult.setChecknum(checknum);
        temporaryReportFlowDetailResult.setAge(age);
        temporaryReportFlowDetailResult.setModality(modality);
        temporaryReportFlowDetailResult.setPatname(patname);
        temporaryReportFlowDetailResult.setPattype(pattype);
        temporaryReportFlowDetailResult.setRemotehos(remotehos);
        temporaryReportFlowDetailResult.setStatus(status);
        temporaryReportFlowDetailResult.setSex(sex);
        temporaryReportFlowDetailResult.setList(temporaryReportDtos);
        System.out.println(temporaryReportFlowDetailResult);
        return JSON.toJSONString(temporaryReportFlowDetailResult);
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
//            RemoteReport remoteReport = remoteReportService.getReportById(id);
            RemoteReport remoteReport=remotereportMapper.selectByPrimaryKeyAddSecond(id);
            System.out.println(remoteReport);
            RemoteRegister remoteRegister = remoteRegisterService.getRemoteRegisterByCheckNum(remoteReport.getChecknum());
            RemoteModifyReportPatDetailAdd rmrpd = new RemoteModifyReportPatDetailAdd();
            User u = (User) httpSession.getAttribute("user");



            TemporaryReport temporaryReport=temporaryReportMapper.selectTemporaryReportByRemoteReportId(id);
            System.out.println(id);
            System.out.println(temporaryReport);
            temporaryReport.setVerifydoccode(u.getUsername());
            temporaryReport.setVerifydocname(u.getName());
            temporaryReport.setVerifyupdatetime(new Date());
            temporaryReportMapper.updateIfHadVerified(temporaryReport.getVerifydoccode(),temporaryReport.getVerifydocname(),temporaryReport.getVerifyupdatetime(),id);


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
            rmrpd.setSuggestion(remoteReport.getRemarks());
            System.out.println(rmrpd);
            System.out.println("123");
            return JSON.toJSONString(rmrpd);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    /**
     * @Description: 根据id加载报告病人细节(包含退回以后)
     * @Author: Shalldid
     * @Date: Created in 15:12 2018-05-15
     * @Return:
     **/
    @RequestMapping(value = "/{id}/backedModifyRemoteReportPatDetail" , method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String backedModifyRemoteReportPatDetail(@PathVariable("id") String id, HttpSession httpSession){
        try {
            System.out.println(id);
//            RemoteReport remoteReport = remoteReportService.getReportById(id);
            RemoteReport remoteReport=remotereportMapper.selectByPrimaryKeyAddSecond(id);
            TemporaryReport temporaryReport=remoteReportService.getTemporaryReportById(temporaryReportMapper.selectIdByRemoteReportId(id));
//            System.out.println(temporaryReportMapper.selectIdByRemoteReportId(id));
//            System.out.println(temporaryReport);
            RemoteRegister remoteRegister = remoteRegisterService.getRemoteRegisterByCheckNum(temporaryReport.getChecknum());
            RemoteModifyReportPatDetailAdd rmrpd = new RemoteModifyReportPatDetailAdd();
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
//            rmrpd.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(remoteReport.getChecknum(), sdf.format(remoteReport.getReporttime()), remoteRegister.getRemotehos()));
            rmrpd.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(remoteReport.getChecknum(), sdf.format(new Date()), remoteRegister.getRemotehos()));
            //System.out.println(JSON.toJSONString(rmrpd));
            rmrpd.setSuggestion(remoteReport.getRemarks());
            System.out.println("yq");
            System.out.println(remoteReport);
            System.out.println(rmrpd);
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
            RemoteReport remoteReport = remoteReportService.getReportByIdAdd(id);
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

            remoteReport.setVerifieddoccode(u.getUsername());
            int update_status = remoteReportService.updateReportAdd(remoteReport);

            int update_flag = remoteRegisterService.updateFlagByCheckNum("已审核报告",remoteReportService.getReportById(id).getChecknum());

            if(update_status == 1 && update_flag==1){
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


    /**
     * @Description: 报告退回(远程诊断)
     * @Author: Shalldid
     * @Date: Created in
     * @Return:
     **/
    @RequestMapping(value="/backToWriteReportRemote")
    @ResponseBody
    public String backToWriteReport(@RequestParam("checkNum") String checknum,@RequestParam("id") String id,
                                    HttpSession httpSession) throws IOException {
        try{
//            int rep_ = remoteReportService.deleteReportById(id);
            int rep_ = remoteReportService.updateReportById(id);
            System.out.println(rep_);
            int reg_ =remoteRegisterService.updateFlagByCheckNum("已分配",checknum);
            System.out.println(reg_);
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


    @RequestMapping(value = "/save")
    @ResponseBody
    public String back(@RequestParam("pat_checknum") String checknum,
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
                                     @RequestParam("bgCode") String id,
                       @RequestParam("suggestion") String suggestion, HttpSession httpSession)throws IOException {
            RemoteReport remoteReport = remoteReportService.getReportByIdAdd(id);
            remoteReport.setRemarks(suggestion);
            remotereportMapper.updateRemarkById(id,remoteReport.getRemarks());
            return "1";
    }



    /**
     * @Description: 给医生分配病人时查询病人(search和getSearchResults)
     * @Author: Shalldid
     * @Date: Created in 10:47 2018-07-04
     * @Return:
     **/
    String name=null;
    String modality=null;
    String pat_type=null;
    String number=null;
    String sex=null;
    String age=null;
    @RequestMapping(value="/search",method = RequestMethod.POST)
    @ResponseBody
    public String search(@RequestParam("name") String name,    @RequestParam("modality") String modality,
                         @RequestParam("pat_type") String pat_type,@RequestParam("number") String number,
                         @RequestParam("sex") String sex, @RequestParam("age") String age,HttpSession httpSession)throws Exception{
        this.name=name;
        this.modality=modality;
        this.pat_type=pat_type;
        this.number=number;
        this.sex=sex;
        this.age=age;
        return JSON.toJSONString(number);
    }


    @RequestMapping(value="/getSearchResults",method = RequestMethod.POST)
    @ResponseBody
    public String getSearchResults(Pagination p,HttpSession httpSession)throws Exception {

        System.out.println("lalala");;
        List<RemoteAllocateDto> remoteAllocateDtos=new ArrayList<RemoteAllocateDto>();
        List<RemoteAllocateDto> remoteAllocateDtoss=new ArrayList<RemoteAllocateDto>();
        remoteAllocateDtoss.clear();

        String name=this.name;//patlist
        String modality=this.modality;//remote_register
        String pat_type=this.pat_type;//remote_register
        String number=this.number;//remote_register
        String sex=this.sex;//patlist
        String age=this.age;//patlist
//
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);


        if(name==null&&modality==null&&pat_type==null&&number==null&&sex==null&&age==null){
            List<RemoteRegister> registerss = remoteRegisterService.getRemoteAllocateByFlag("已上传图像");
            for (RemoteRegister remoteRegister : registerss) {
                RemoteAllocateDto remoteAllocateDto = new RemoteAllocateDto();
                remoteAllocateDto.setChecknum(remoteRegister.getChecknum());
                remoteAllocateDto.setPatName(patientService.getPatByIdcard(remoteRegister.getIdcard()).getPatname());
                remoteAllocateDto.setModality(remoteRegister.getModality());
                remoteAllocateDto.setPattype(remoteRegister.getPattype());
                remoteAllocateDto.setPatGender(patientService.getPatByIdcard(remoteRegister.getIdcard()).getPatgender());
                remoteAllocateDto.setAge(patientService.getPatByIdcard(remoteRegister.getIdcard()).getAge() + patientService.getPatByIdcard(remoteRegister.getIdcard()).getAgetype());
                remoteAllocateDtoss.add(remoteAllocateDto);
            }
            int totalRow = remoteAllocateDtoss.size();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            PaginationResult<RemoteAllocateDto> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(remoteAllocateDtoss);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println(paginationResult);
            return JSON.toJSONString(paginationResult);

        }else if(name==""&&modality==""&&pat_type==""&&number==""&&sex==""&&age==""){
            List<RemoteRegister> registerss = remoteRegisterService.getRemoteAllocateByFlag("已上传图像");
            for (RemoteRegister remoteRegister : registerss) {
                RemoteAllocateDto remoteAllocateDto = new RemoteAllocateDto();
                remoteAllocateDto.setChecknum(remoteRegister.getChecknum());
                remoteAllocateDto.setPatName(patientService.getPatByIdcard(remoteRegister.getIdcard()).getPatname());
                remoteAllocateDto.setModality(remoteRegister.getModality());
                remoteAllocateDto.setPattype(remoteRegister.getPattype());
                remoteAllocateDto.setPatGender(patientService.getPatByIdcard(remoteRegister.getIdcard()).getPatgender());
                remoteAllocateDto.setAge(patientService.getPatByIdcard(remoteRegister.getIdcard()).getAge() + patientService.getPatByIdcard(remoteRegister.getIdcard()).getAgetype());
                remoteAllocateDtoss.add(remoteAllocateDto);
            }
            int totalRow = remoteAllocateDtoss.size();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            PaginationResult<RemoteAllocateDto> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(remoteAllocateDtoss);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println(paginationResult);
            return JSON.toJSONString(paginationResult);

        }
        else  {
            remoteAllocateDtos = remoteRegisterService.getRmoteRegister(number, modality, pat_type, name, sex, age);
            System.out.println(remoteAllocateDtos);
            for(RemoteAllocateDto remoteAllocateDto:remoteAllocateDtos){
                System.out.println(remoteAllocateDto.getFlag());
                if(!("已上传图像".equals(remoteAllocateDto.getFlag()))){
                    continue;
                }
                System.out.println("123");
                RemoteAllocateDto remoteAllocateDto1 = new RemoteAllocateDto();
                remoteAllocateDto1.setChecknum(remoteAllocateDto.getChecknum());
                remoteAllocateDto1.setPatName(remoteAllocateDto.getPatName());
                remoteAllocateDto1.setModality(remoteAllocateDto.getModality());
                remoteAllocateDto1.setPattype(remoteAllocateDto.getPattype());
                remoteAllocateDto1.setPatGender(remoteAllocateDto.getPatGender());
                remoteAllocateDto1.setAge(remoteAllocateDto.getAge()+remoteAllocateDto.getAgeType());
                remoteAllocateDtoss.add(remoteAllocateDto1);
            }
            int totalRow = remoteAllocateDtoss.size();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            PaginationResult<RemoteAllocateDto> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(remoteAllocateDtoss);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println(paginationResult);
            return JSON.toJSONString(paginationResult);
        }
}


    @RequestMapping(value="/allocate")
    @ResponseBody
    public String allocate(@RequestParam("checknum") String checknum,@RequestParam("docname") String docname,
                           HttpSession httpSession){

        System.out.println("123");
        User u=(User)httpSession.getAttribute("user");
//        RemoteRegister remoteRegister=remoteRegisterService.getRemoteRegisterByCheckNum(checknum);
        Allocate allocate=new Allocate();


        System.out.println(docname);
        if(userService.ifExsitUserByName(docname)!=null){
            System.out.println("zxc");
            remoteRegisterService.updateFlagByCheckNum("已分配",checknum);
            allocate.setId(UUID.randomUUID().toString());
            allocate.setAllocateddoccode(userService.getUserByUserName(docname).getUsername());
            allocate.setAllocatedoccode(u.getUsername());
            allocate.setAllocatetime(new Date());
            allocate.setChecknum(checknum);
            allocatemapper.insert(allocate);
            return "1";
        }else{
            System.out.println("cxz");
            return "0";
        }
    }


    //给医生下拉框赋值
//    @RequestMapping(value="/getDocSelect",method = RequestMethod.POST,produces = {"application/text;charset=UTF-8"})
//    @ResponseBody
//    public String getDocSelect(@RequestParam("checknum") String checknum){
//        RemoteRegister remoteRegister=remoteRegisterService.getRemoteRegisterByCheckNum(checknum);
//
//        List<SelectPicker> hosInfos = new ArrayList<SelectPicker>();
////        List<Department> departments=new ArrayList<Department>();
//        List<User> users=new ArrayList<User>();
////        List<String> users1=new ArrayList<String>();
//        List<String> majorHosIds=relationMapper.getMajorHosidByJuniorHosid(remoteRegister.getRemotehos());
//
//        for(String s:majorHosIds){
//            List<Department> departments1=departmentMapper.selectByHosid(s);
//            for(Department department:departments1){
//                department.getId();
//                users=userMapper.selectUserByDept(department.getId());
//                for(User user:users){
//                    SelectPicker h = new SelectPicker();
//                    h.setLabel(user.getUsername());
//                    h.setValue( user.getName());
//                    hosInfos.add(h);
////                    users1.add( user.getName());
//                }
//            }
//        }
//        System.out.println(hosInfos);
//        return JSON.toJSONString(hosInfos);
//    }

    @RequestMapping(value="/getDocSelect",method = RequestMethod.POST,produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String getDocSelect(){
//        RemoteRegister remoteRegister=remoteRegisterService.getRemoteRegisterByCheckNum(checknum);


        List<SelectPicker> hosInfos = new ArrayList<SelectPicker>();
        List<String> docNmaes=new ArrayList<String>();
        List<String> docCodes=new ArrayList<String>();
        List<User> user=userService.getAll();
        for(User u: user){
            String docName=u.getName();
            String docCode=u.getUsername();
            docCodes.add(docCode);
            docNmaes.add(docName);
        }
        for(String s:docNmaes){
            SelectPicker h = new SelectPicker();
//            h.setLabel(s);
            h.setValue(s);
            hosInfos.add(h);
        }
        int j=0;
        for(SelectPicker h1:hosInfos){
            int i=0;
            for(String docCode:docCodes){
                h1.setLabel(docCodes.get(j));
                i++;
                j++;
                if(i>0){
                    break;
                }
            }

        }
        System.out.println(hosInfos);
        return JSON.toJSONString(hosInfos);
    }

    @RequestMapping(value="/getHadChecked")
    @ResponseBody
    public String getHadChecked(Pagination p,HttpSession httpSession){

        User u=(User)httpSession.getAttribute("user");

        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        List<RegisterInfoJunior> registerInfoJuniors=registerInfoJuniorMapper.selectByFlag("已检查");

        CompareJunior compareJunior=new CompareJunior();
        List<JuniorCheckedDto> juniorCheckedDtos=new ArrayList<JuniorCheckedDto>();
        for(RegisterInfoJunior registerInfoJunior:registerInfoJuniors) {

            if (compareJuniorMapper.selectByCheckNum(registerInfoJunior.getRecordid()) == null) {


                JuniorCheckedDto juniorCheckedDto = new JuniorCheckedDto();
                juniorCheckedDto.setCheckNum(registerInfoJunior.getRecordid());
                juniorCheckedDto.setExamItemName(registerInfoJunior.getExamitemname());
                juniorCheckedDto.setHosName(hospitalMapper.getHosNameByHosId(departmentMapper.getHosIdbyDeptid(u.getDept())));
                //   remoteWaitForReportTab.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(remoteRegister.getTagpatientid(), simpleDateFormat.format(remoteRegister.getStudydate()), remoteRegister.getRemotehos()));
                juniorCheckedDto.setPatGender(registerInfoJunior.getPatgender());
                juniorCheckedDto.setPatient_Age(registerInfoJunior.getAge() + registerInfoJunior.getAgetype());
                juniorCheckedDto.setPatName(registerInfoJunior.getPatname());
                String checkdate = dicomWorkListJuniorMapper.selectStartDateByPatientId(registerInfoJunior.getChecknum());
                System.out.println(checkdate);
                String checkDateChange = checkdate.substring(0, 4) + "-" + checkdate.substring(4, 6) + "-" + checkdate.substring(6, 8);
                juniorCheckedDto.setCheckDate(checkDateChange);//
                juniorCheckedDto.setImagePath(imageAndReportPathGenerator.getInnerImagePath(registerInfoJunior.getChecknum(), dicomWorkListJuniorMapper.selectStartDateByPatientId(registerInfoJunior.getChecknum()), ""));
                juniorCheckedDtos.add(juniorCheckedDto);
            }
        }
            int totalRow = juniorCheckedDtos.size();
            boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
            int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
            PaginationResult<JuniorCheckedDto> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(juniorCheckedDtos);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println(paginationResult);
            return JSON.toJSONString(paginationResult);

    }

}
