package org.springmvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.*;
import org.springmvc.dao_inner.RegisterInfoInnerMapper;
import org.springmvc.dto.CheckedTab;
import org.springmvc.dto.VerifiedTab;
import org.springmvc.dto.WrittedTab;
import org.springmvc.pojo.*;
import org.springmvc.pojo_inner.RegisterInfoInner;
import org.springmvc.service.RegInfoService;
import org.springmvc.service.UserService;
import org.springmvc.tool.*;

import javax.annotation.Resource;
import javax.management.MBeanServer;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RegInfoServiceimpl implements RegInfoService {
    private static final Logger logger = Logger.getLogger(RegInfoServiceimpl.class);

    @Resource
    private SeriesNumGenerator seriesNumGenerator;

    @Autowired
    private RegisterInfoMapper registerInfoMapper;

    @Autowired
    private ExamItemMapper examItemMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DicomWorkListMapper dicomWorkListMapper;

    @Autowired
    private DicomWorkListLocalMapper dicomWorkListLocalMapper;

    @Autowired
    private OrderTableMapper orderTableMapper;

    @Autowired
    private PatientMapper patientMapper;

    @Resource
    private ImageAndReportPathGenerator imageAndReportPathGenerator;

    @Resource
    private UserService userService;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private RegisterInfoInnerMapper registerInfoInnerMapper;

    @Autowired
    private DeviceInfoLocalMapper deviceInfoLocalMapper;

    @Resource
    private BirthGenerator birthGenerator;

    @Resource
    private ChineseConvert chineseConvert;

    @Autowired
    private ExamItemLocalMapper examItemLocalMapper;

    @Autowired
    private RegisterInfoLocalMapper registerInfoLocalMapper;

    @Override
    public RegisterInfoInner insertNewRegInfoLocal(String clinicId, String patName, String patGender, String patBirthdate, String address,
                                                   String YiBaoID, String IdentityId, String Telephone, String PatRoomName, String BedNo,
                                                   String DeptCode, String DeptName, String ExamItemCode, String cardno, String jcbw,
                                                   String sjmd, String sqdbh, String patType, String checkDevice,HttpSession httpSession) throws ParseException {
        RegisterInfoInner r = new RegisterInfoInner();
        r.setPatgender(patGender);
        System.out.println(r);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date pat_birthday = sdf.parse(patBirthdate);
        String[] ageAndType = birthGenerator.getAgeAndType(pat_birthday, new Date());
        r.setPatbirthdate(pat_birthday);
//        System.out.println(r);
        r.setPatnamepy(chineseConvert.convert(patName));
//        System.out.println(r);
        r.setPatname(patName);
//        System.out.println(r);
        ExamItemLocal examItemLocal = examItemLocalMapper.selectByPrimaryKey(ExamItemCode);
        r.setRecordid(seriesNumGenerator.getLocalRecordID(examItemLocal.getExamitemname()));
//        System.out.println(r);
        r.setPatientid(seriesNumGenerator.getLocalPatientID(examItemLocal.getExamitemname()));
//        System.out.println(r);
        r.setAddress(address);
//        System.out.println(r);
        r.setAge(Integer.valueOf(ageAndType[0]));
        r.setAgetype(ageAndType[1]);
        r.setBedno(BedNo);
        r.setCardno(cardno);
        r.setChecknum(seriesNumGenerator.getLocalCheckNum(examItemLocal.getExamitemname()));
        r.setClinicid(clinicId);
        r.setDeptcode(DeptCode);
        r.setDeptname(DeptName);
        User u = (User)httpSession.getAttribute("user");
        r.setExamitemcode(ExamItemCode);
        r.setExamitemname(examItemLocal.getExamitemname());
        r.setFlag("未检查");
        r.setIdentityid(IdentityId);
        r.setJcbw(jcbw);
        r.setRegistertime(new Date());
        r.setSjmd(sjmd);
        r.setSqdbh(sqdbh);
        r.setTelephone(Telephone);
        r.setYibaoid(YiBaoID);
        r.setPatroomname(PatRoomName);

        System.out.println(r);

        DicomWorkList dicomWorkList = new DicomWorkList();
        dicomWorkList.setAccessionn(seriesNumGenerator.getLocalAccessionNLocal(examItemLocal.getExamitemname()));
        System.out.println(seriesNumGenerator.getLocalAccessionN(examItemLocal.getExamitemname()));
        dicomWorkList.setPatientid(r.getPatientid());
        dicomWorkList.setPatientnam(r.getPatnamepy());
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        dicomWorkList.setPatientbir(sf.format(r.getPatbirthdate()).substring(0,8));
        if("男".equals(r.getPatgender())){
            dicomWorkList.setPatientsex("M");
        }else {
            dicomWorkList.setPatientsex("F");
        }
//        dicomWorkList.setReqprocdes("");
        dicomWorkList.setModality(r.getExamitemname());
        dicomWorkList.setScheduleda(checkDevice);
        dicomWorkList.setStartdate(sf.format(new Date()).substring(0,8));
        dicomWorkList.setStarttime(sf.format(new Date()).substring(8,14));
        dicomWorkList.setSchedpsid(r.getPatientid());
        dicomWorkList.setReqprocid(r.getPatientid());
        dicomWorkList.setStudyid(r.getChecknum());
        System.out.println(dicomWorkList);
        try{
            System.out.println();
            registerInfoLocalMapper.insert(r);
            System.out.println("lalla");
            dicomWorkListLocalMapper.insert(dicomWorkList);
            System.out.println("alala");
            return r;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *@Description: 登记新的信息到登记表内
     *@Author: Shalldid
     *@Date:Created in 15:04 2018-04-25
     *@Return 返回生成的检查号
     **/
    @Override
    public String insertNewRegInfo(String clinicID, String bedNo, String check_type, String cardNo, String jcbw,
                                String sjmd, String sqdbh, String deptID, String idcard){
        int s;
        String check_num;
        try {
            check_num = seriesNumGenerator.getCheckNum(check_type);
            RegisterInfo r = new RegisterInfo(check_num, seriesNumGenerator.getRecordID(check_type), new Date(),
                    clinicID, seriesNumGenerator.getPatientID(check_type), bedNo, examItemMapper.getExamCodeByName(check_type), "未检查",
                    departmentMapper.getDeptNamebyDeptid(deptID), check_type, false, cardNo, jcbw, sjmd, sqdbh, deptID, idcard);
            s = registerInfoMapper.insert(r);
        }catch (Exception e){
            logger.error("登记失败： " + idcard + " 失败信息： " + e.getMessage());
            s = -2;
            check_num = "";
        }
        if(s == 1){
            return check_num; //如果插入成功，返回生成的检查号
        }else {
            return "error";
        }
    }
    /**
     * @Description: 通过checkNum返回登记表
     * @Author: Shalldid
     * @Date: Created in 9:10 2018-05-03
     * @Return:
     **/
    @Override
    public RegisterInfo getRegInfoByCheckNum(String checkNum){
        return registerInfoMapper.selectByPrimaryKey(checkNum);
    }

    @Override
    public RegisterInfoInner getRegInfoByCheckNumLocal(String checkNum){
        return registerInfoLocalMapper.selectByPrimaryKeyLocal(checkNum);
    }

    @Override
    public RegisterInfoInner getRegInfoByCheckNumLocalModify(String checkNum){
        return registerInfoLocalMapper.selectByPrimaryKeyLocalModify(checkNum);
    }

//    @Override
//    public RegisterInfo setFlagByCheckNum(String checknum){
//        return registerInfoMapper.updateFlagByChecknum(checknum);
//    }

    @Override
    public int updateRegisterByRegister(RegisterInfo r){return  registerInfoMapper.updateByPrimaryKey(r);}


    @Override
    public int updateRegisterByRegisterLocal(RegisterInfoInner r){return  registerInfoMapper.updateByPrimaryKeyLocal(r);}

    /**
     * @Description: 根据examItemCode返回DeviceList
     * @Author: Shalldid
     * @Date: Created in 9:39 2018-05-03
     * @Return:
     **/
    @Override
    public List<Device> getExamItemDevice(String examItemCode){
        return deviceMapper.getDeviceByExamItemCode(examItemCode);
    }
    @Override
    public List<Device> getExamItemDeviceLocal(String examItemCode){
        return deviceInfoLocalMapper.getDeviceByExamItemCode(examItemCode);
    }
    /**
     * @Description: 插入一条DicomWorkList记录
     * @Author: Shalldid
     * @Date: Created in 16:51 2018-05-03
     * @Return:
     **/
    @Override
    public int insertDicomWorkList(DicomWorkList d){

        return dicomWorkListMapper.insert(d);
    }
    /**
     * @Description: 根据病人ID返回DicomWorkList
     * @Author: Shalldid
     * @Date: Created in 16:51 2018-05-03
     * @Return:
     **/
    @Override
    public DicomWorkList getDicomWorkListByPatId(String patid){
        return dicomWorkListMapper.selectByPatid(patid);
    }
    /**
     * @Description: 根据flag为"已检查"加载已检查的登记表
     * @Author: Shalldid
     * @Date: Created in 17:32 2018-05-03
     * @Return:
     **/
    @Override
    public List<CheckedTab> getCheckedListByFlag(String flag, int currIndex, int pageSize, HttpSession httpSession){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<RegisterInfo> l = registerInfoMapper.getCheckedListByFlag(flag, currIndex, pageSize);
        List<CheckedTab> l_temp = new ArrayList<CheckedTab>();
        OrderTable o;
        Patient p;
        DicomWorkList d;
        User user = (User)httpSession.getAttribute("user");
        for(RegisterInfo r : l){
            CheckedTab c = new CheckedTab();
            o = orderTableMapper.getOrderTableByCheckNum(r.getChecknum());
            //System.out.println(o.getOrderdate());
            c.setCheckDate(sdf.format(o.getOrderdate()));
            c.setCheckNum(r.getChecknum());
            c.setExamItemName(r.getExamitemname());
            p = patientMapper.selectByPrimaryKey(r.getIdcard());
            c.setPatGender(p.getPatgender());
            c.setPatient_Age(p.getAge() + p.getAgetype());
            c.setPatName(p.getPatname());
            try{
                c.setHosName(hospitalMapper.getHosNameByHosId(userService.getHosIdOfUser(user.getDept())));
            }catch (Exception e){
                e.printStackTrace();
            }
            d = dicomWorkListMapper.selectByPatid(r.getPatientid());
            c.setImagePath(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(), o.getOrdersource()));
            l_temp.add(c);
        }
        return l_temp;
    }
    /**
     * @Description: 根据flag为已检查加载已检查的登记表的数量,加载之前查询未检查的记录，然后根据记录调阅院内对应的记录，若为已检查，则更新至本数据库
     * @Author: Shalldid
     * @Date: Created in 17:32 2018-05-03
     * @Return:
     **/
    @Override
    public int countCheckListByFlag(String flag){
        updateRegInfoFromInner();
        return registerInfoMapper.countCheckListByFlag(flag);
    }

    @Override
    public int countCheckListByFlagLocal(String flag){
        updateRegInfoFromInner();
        return registerInfoLocalMapper.countCheckListByFlag(flag);
    }


    /**
     * @Description: 更新reg的flag
     * @Author: Shalldid
     * @Date: Created in 15:39 2018-05-07
     * @Return:
     **/
    @Override
    public int updateRegStatusByChecknumLocal(String status, String checknum){
        return registerInfoLocalMapper.updateRegStatusByChecknum(status, checknum);
    }

    @Override
    public int updateRegStatusByChecknum(String status, String checknum){
        return registerInfoMapper.updateRegStatusByChecknum(status, checknum);
    }


    /**
     * @Description: 根据flag为"已写报告"加载已检查的登记表
     * @Author: Shalldid
     * @Date: Created in 17:32 2018-05-03
     * @Return:
     **/
    public List<WrittedTab> getWrittededListByFlag(String flag, int currIndex, int pageSize){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<RegisterInfo> l = registerInfoMapper.getCheckedListByFlag(flag, currIndex, pageSize);
        System.out.println(l);
        List<WrittedTab> l_temp = new ArrayList<WrittedTab>();
        OrderTable o;
        Patient p;
        DicomWorkList d;
        Report report;
        for(RegisterInfo r : l){
            WrittedTab w = new WrittedTab();
            report = reportMapper.selectByCheckNum(r.getChecknum());
            System.out.println(report);
            d = dicomWorkListMapper.selectByPatid(r.getPatientid());
            System.out.println(r.getPatientid());
            System.out.println(d);
            System.out.println(r.getChecknum());
            o = orderTableMapper.getOrderTableByCheckNum(r.getChecknum());
            System.out.println(o);
            w.setBgCode(report.getReportcode());
            w.setCheckNum(r.getChecknum());
            w.setExamItemName(r.getExamitemname());
            p = patientMapper.selectByPrimaryKey(r.getIdcard());
            w.setPatGender(p.getPatgender());
            w.setPatient_Age(p.getAge() + p.getAgetype());
            w.setPatName(p.getPatname());
            w.setRegisterDate(sdf.format(r.getRegtime()));
            w.setReportDate(sdf.format(report.getReporttime()));
            w.setDocName((userService.getUserByUserId(report.getDoccode())).getName());
            System.out.println("0");
            System.out.println(r.getPatientid());
            System.out.println(d.getStartdate());
            System.out.println(o.getOrdersource());
            System.out.println(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(), o.getOrdersource()));
            w.setImagePath(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(), o.getOrdersource()));
            l_temp.add(w);
        }
        return l_temp;
    }

    /**
     * @Description: 根据flag为"已审核报告"加载已检查的登记表
     * @Author: Shalldid
     * @Date: Created in 17:32 2018-05-03
     * @Return:
     **/
    public List<VerifiedTab> getVerifiedListByFlag(String flag, int currIndex, int pageSize){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<RegisterInfo> l = registerInfoMapper.getCheckedListByFlag(flag, currIndex, pageSize);
        System.out.println(l);
        List<VerifiedTab> l_temp = new ArrayList<VerifiedTab>();
        OrderTable o;
        Patient p;
        DicomWorkList d;
        Report report;
        for(RegisterInfo r : l){
            VerifiedTab w = new VerifiedTab();
            report = reportMapper.selectByCheckNumAdd(r.getChecknum());
            System.out.println(report);
            d = dicomWorkListMapper.selectByPatid(r.getPatientid());
            System.out.println(r.getPatientid());
            System.out.println(d);
            System.out.println(r.getChecknum());
            o = orderTableMapper.getOrderTableByCheckNum(r.getChecknum());
            System.out.println(o);
            w.setBgCode(report.getReportcode());
            w.setCheckNum(r.getChecknum());
            w.setExamItemName(r.getExamitemname());
            p = patientMapper.selectByPrimaryKey(r.getIdcard());
            w.setPatGender(p.getPatgender());
            w.setPatient_Age(p.getAge() + p.getAgetype());
            w.setPatName(p.getPatname());
            w.setRegisterDate(sdf.format(r.getRegtime()));
            w.setReportDate(sdf.format(report.getReporttime()));
            w.setDocName((userService.getUserByUserId(report.getDoccode())).getName());


            System.out.println(report.getVerifydoccode());
            System.out.println((userService.getUserByUserId(report.getVerifydoccode())));
            w.setVerifyDocName((userService.getUserByUserId(report.getVerifydoccode())).getName());

            System.out.println(w.getVerifyDocName());

            try {
                w.setVerifyDate(sdf.format(report.getVerifydate()));
            }
            catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("0");
            System.out.println(r.getPatientid());
            System.out.println(d.getStartdate());
            System.out.println(o.getOrdersource());
            System.out.println(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(), o.getOrdersource()));
            w.setImagePath(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(), o.getOrdersource()));
            l_temp.add(w);
        }
        return l_temp;
    }

    /**
     * @Description: 根据flag为"已写报告"加载已检查的登记表(本地)
     * @Author: Shalldid
     * @Date: Created in 17:32 2018-05-03
     * @Return:
     **/
    public List<WrittedTab> getWrittededListByFlagLocal(String flag, int currIndex, int pageSize,HttpSession httpSession){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<RegisterInfoInner> l = registerInfoLocalMapper.getCheckedListByFlag(flag, currIndex, pageSize);
        System.out.println(l);
        List<WrittedTab> l_temp = new ArrayList<WrittedTab>();
//        OrderTable o;
        Patient p;
        DicomWorkList d;
        Report report;
//        String filepath="F:\\DrBroswer-SSM\\src\\main\\resources\\本地医院名称.ini";
//        ConfigReader configReader=new ConfigReader(filepath);


        User u = (User)httpSession.getAttribute("user");

        for(RegisterInfoInner r : l){
            WrittedTab w = new WrittedTab();
            report = reportMapper.selectByCheckNum(r.getChecknum());
            System.out.println(report);
            d = dicomWorkListLocalMapper.selectByPatid(r.getPatientid());
            System.out.println(r.getPatientid());
            System.out.println(d);
            System.out.println(r.getChecknum());
//            o = orderTableMapper.getOrderTableByCheckNum(r.getChecknum());
//            System.out.println(o);
            w.setBgCode(report.getReportcode());
            w.setCheckNum(r.getChecknum());
            w.setExamItemName(r.getExamitemname());
            p = patientMapper.selectByPrimaryKey(r.getIdentityid());
            w.setPatGender(p.getPatgender());
            w.setPatient_Age(p.getAge() + p.getAgetype());
            w.setPatName(p.getPatname());
            w.setRegisterDate(sdf.format(r.getRegistertime()));
            w.setReportDate(sdf.format(report.getReporttime()));
            w.setDocName((userService.getUserByUserId(report.getDoccode())).getName());
            System.out.println("0");
            System.out.println(r.getPatientid());
            System.out.println(d.getStartdate());
//            System.out.println(o.getOrdersource());
//            System.out.println(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(), "本地医院"));
            w.setImagePath(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(),  departmentMapper.getHosIdbyDeptid(u.getDept())));
            l_temp.add(w);
        }
        return l_temp;
    }


    /**
     * @Description: 根据flag为"已审核报告"加载已检查的登记表(本地)
     * @Author: Shalldid
     * @Date: Created in 17:32 2018-05-03
     * @Return:
     **/
    public List<VerifiedTab> getVerifiedListByFlagLocal(String flag, int currIndex, int pageSize,HttpSession httpSession){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<RegisterInfoInner> l = registerInfoLocalMapper.getCheckedListByFlag(flag, currIndex, pageSize);
        System.out.println(l);
        List<VerifiedTab> l_temp = new ArrayList<VerifiedTab>();
//        OrderTable o;
        Patient p;
        DicomWorkList d;
        Report report;
//        String filepath="F:\\DrBroswer-SSM\\src\\main\\resources\\本地医院名称.ini";
//        ConfigReader configReader=new ConfigReader(filepath);

        User u = (User)httpSession.getAttribute("user");

        for(RegisterInfoInner r : l){
            VerifiedTab w = new VerifiedTab();
            report = reportMapper.selectByCheckNumAdd(r.getChecknum());
            System.out.println(report);
            d = dicomWorkListLocalMapper.selectByPatid(r.getPatientid());
            System.out.println(r.getPatientid());
            System.out.println(d);
            System.out.println(r.getChecknum());
//            o = orderTableMapper.getOrderTableByCheckNum(r.getChecknum());
//            System.out.println(o);
            w.setBgCode(report.getReportcode());
            w.setCheckNum(r.getChecknum());
            w.setExamItemName(r.getExamitemname());
            p = patientMapper.selectByPrimaryKey(r.getIdentityid());
            w.setPatGender(p.getPatgender());
            w.setPatient_Age(p.getAge() + p.getAgetype());
            w.setPatName(p.getPatname());
            w.setRegisterDate(sdf.format(r.getRegistertime()));
            w.setReportDate(sdf.format(report.getReporttime()));
            w.setDocName((userService.getUserByUserId(report.getDoccode())).getName());

            System.out.println("111");
            System.out.println(report.getVerifydoccode());

            w.setVerifyDocName((userService.getUserByUserId(report.getVerifydoccode())).getName());

            System.out.println(w.getVerifyDocName());
            w.setVerifyDate(sdf.format(report.getVerifydate()));

            System.out.println("0");
            System.out.println(r.getPatientid());
            System.out.println(d.getStartdate());
//            System.out.println(o.getOrdersource());
//            System.out.println(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(), "本地医院"));
            w.setImagePath(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(),  departmentMapper.getHosIdbyDeptid(u.getDept())));
            l_temp.add(w);
        }
        return l_temp;
    }





    /**
     * @Description: 从院内数据库登记表内已检查的记录更新至本地数据库
     * @Author: Shalldid
     * @Date: Created in 16:31 2018-05-16
     * @Return:
     **/
    private int updateRegInfoFromInner(){
        int counts = 0;
        List<String> strings = registerInfoMapper.selectByFlagReturnChecknum("未检查");
        for (String s : strings){
            RegisterInfoInner r = registerInfoInnerMapper.selectByPrimaryKey(s);
            if(r == null){
                continue;
            }else if("已检查".equals(r.getFlag())){
                counts += registerInfoMapper.updateRegStatusByChecknum("已检查",s);
            }else {
                continue;
            }
        }
        //System.out.println("Date: " + new Date() + "； 更新条目数： " + counts);
        return counts;
    }

    @Override
    public int getCountByCheckNum(String checknum){return registerInfoMapper.selectCountByCheckNum(checknum);}

    @Override
    public int delete(String checknum){return registerInfoMapper.deleteByCheckNum(checknum);}
}
