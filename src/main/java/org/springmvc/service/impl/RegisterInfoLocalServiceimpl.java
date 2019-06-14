package org.springmvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.*;
import org.springmvc.dao_inner.RegisterInfoInnerMapper;
import org.springmvc.dto.CheckedTab;
import org.springmvc.pojo.*;
import org.springmvc.pojo_inner.RegisterInfoInner;
import org.springmvc.service.RegisterInfoLocalService;
import org.springmvc.service.UserService;
import org.springmvc.tool.ConfigReader;
import org.springmvc.tool.ImageAndReportPathGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterInfoLocalServiceimpl implements RegisterInfoLocalService {
    private static final Logger logger = Logger.getLogger(RegInfoServiceimpl.class);

    @Autowired
    private RegisterInfoMapper registerInfoMapper;
    @Autowired
    private OrderTableMapper orderTableMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private HospitalServiceimpl hospitalMapper;
    @Resource
    private UserService userService;
    @Autowired
    private DicomWorkListMapper dicomWorkListMapper;
    @Resource
    private ImageAndReportPathGenerator imageAndReportPathGenerator;
    @Autowired
    private RegisterInfoLocalMapper registerInfoLocalMapper;
    @Autowired
    private RegisterInfoInnerMapper registerInfoInnerMapper;

    @Resource
    private ConfigReader configReader;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Resource
    private HospitalServiceimpl hospitalService;


    @Override
    public List<CheckedTab> getCheckedListByFlag(String flag, int currIndex, int pageSize, HttpSession httpSession){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("l");
        List<RegisterInfoInner> l = registerInfoLocalMapper.getCheckedListByFlag(flag, currIndex, pageSize);
        System.out.println(l);
        List<CheckedTab> l_temp = new ArrayList<CheckedTab>();
        Patient p;
        DicomWorkList d;
        User user = (User)httpSession.getAttribute("user");
//        String filepath="F:\\DrBroswer-SSM\\src\\main\\resources\\本地医院名称.ini";
//        ConfigReader configReader=new ConfigReader(filepath);
        for(RegisterInfoInner r : l){
            CheckedTab c = new CheckedTab();
            //System.out.println(o.getOrderdate());
            c.setCheckDate(sdf.format(r.getUpdatetime()));
            c.setCheckNum(r.getChecknum());
            c.setExamItemName(r.getExamitemname());
            p = patientMapper.selectByPrimaryKey(r.getIdentityid());
            System.out.println(p);
            c.setPatGender(p.getPatgender());
            c.setPatient_Age(p.getAge() + p.getAgetype());
            c.setPatName(p.getPatname());
            try{
//                c.setHosName(hospitalMapper.getHosNameByHosId(userService.getHosIdOfUser(user.getDept())));
//                c.setHosName("本地医院");
                c.setHosName(hospitalService.getHosNameByHosId( departmentMapper.getHosIdbyDeptid(user.getDept())));
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("123");
            d = dicomWorkListMapper.selectByPatidLocal(r.getPatientid());
//            System.out.println(d);
            System.out.println("321");
//            c.setImagePath(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(),"本地医院"));
            c.setImagePath(imageAndReportPathGenerator.getInnerImagePath(r.getPatientid(), d.getStartdate(),c.getHosName()));
            l_temp.add(c);
        }
        return l_temp;
    }

    @Override
    public int updateRegisterByRegisterLocal(RegisterInfoInner r){return registerInfoLocalMapper.updateByPrimaryKeyLocal(r);}


    @Override
    public int countCheckListByFlag(String flag){
        System.out.println("lalala");
        return registerInfoLocalMapper.getCountByFlag(flag);}
}
