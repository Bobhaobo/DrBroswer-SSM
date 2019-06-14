package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.*;
import org.springmvc.dto.RemoteAllocateDto;
import org.springmvc.dto.RemoteVerifiedReportTab;
import org.springmvc.dto.RemoteWaitForReportTab;
import org.springmvc.dto.RemoteWrittedReportTab;
import org.springmvc.pojo.*;
import org.springmvc.service.RemoteRegisterService;
import org.springmvc.service.UserService;
import org.springmvc.tool.ImageAndReportPathGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteRegisterServiceimpl implements RemoteRegisterService {

    @Autowired
    private RemoteRegisterMapper remoteRegisterMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private HospitalMapper hospitalMapper;
    @Resource
    private ImageAndReportPathGenerator imageAndReportPathGenerator;
    @Autowired
    private UserService userService;
    @Autowired
    private RemoteReportMapper remoteReportMapper;

    @Autowired
    private TemporaryReportMapper temporaryReportMapper;

    /**
     * @Description: 插入一条新的登记表
     * @Author: Shalldid
     * @Date: Created in 15:05 2018-05-10
     * @Return:
     **/
    @Override
    public int insertNewRegister(RemoteRegister remoteRegister){
        return remoteRegisterMapper.insert(remoteRegister);
    }
    /**
     * @Description: 根据flag和分页查询remoteregister
     * @Author: Shalldid
     * @Date: Created in 15:05 2018-05-10
     * @Return:
     **/
    @Override
    public List<RemoteWaitForReportTab> getWaitForReprotByFlag(int currIndex, int pageSize, HttpSession httpSession){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        List<RemoteRegister> l = remoteRegisterMapper.getRemoteRegisterPageByFlag("已分配", currIndex, pageSize);
        List<RemoteWaitForReportTab> remoteWaitForReportTabs = new ArrayList<RemoteWaitForReportTab>();
        Patient p;
        User u = (User) httpSession.getAttribute("user");
        String hosName_m = hospitalMapper.getHosNameByHosId(userService.getHosIdOfUser(u.getDept()));
        try {
            for(RemoteRegister r : l){
                //System.out.println(r);
                RemoteWaitForReportTab remoteWaitForReportTab = new RemoteWaitForReportTab();
                remoteWaitForReportTab.setCheckNum(r.getChecknum());
                remoteWaitForReportTab.setExamItemName(r.getModality());
                remoteWaitForReportTab.setHosName(hospitalMapper.getHosNameByHosId(r.getRemotehos()));
                //System.out.println(imageAndReportPathGenerator.getRemoteImagePath(r.getChecknum(),simpleDateFormat.format(r.getRegDate()),r.getRemoteHos()));
                remoteWaitForReportTab.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(r.getTagpatientid(),simpleDateFormat.format(r.getStudydate()),r.getRemotehos()));
                p = patientMapper.selectByPrimaryKey(r.getIdcard());
                remoteWaitForReportTab.setPatGender(p.getPatgender());
                remoteWaitForReportTab.setPatient_Age(p.getAge() + p.getAgetype());
                remoteWaitForReportTab.setPatName(p.getPatname());
                remoteWaitForReportTab.setRegisterDate(sdf.format(r.getRegdate()));
                remoteWaitForReportTab.setHosName_m(hosName_m);
                remoteWaitForReportTabs.add(remoteWaitForReportTab);
            }
        }catch (Exception r){
            r.printStackTrace();
        }
        return remoteWaitForReportTabs;
    }
    /**
     * @Description: 根据checknum返回remoteRegister
     * @Author: Shalldid
     * @Date: Created in 16:22 2018-05-10
     * @Return:
     **/
    @Override
    public RemoteRegister getRemoteRegisterByCheckNum(String checknum){
        return remoteRegisterMapper.selcetByChecknum(checknum);
    }
    /**
     * @Description: 根据checknum更改flag
     * @Author: Shalldid
     * @Date: Created in 16:35 2018-05-10
     * @Return:
     **/
    @Override
    public int updateFlagByCheckNum(String flag, String checkNum){
        return remoteRegisterMapper.updateFlagByChecknum(flag,checkNum);
    }

    /**
     * @Description: 报告回退时更新状态
     * @Author: Shalldid
     * @Date: Created in 16:35 2018-05-10
     * @Return:
     **/
    @Override
    public int updateFlagById(String flag, String id){
        System.out.println(flag);
        return remoteRegisterMapper.updateFlagById(flag,id);
    }


    /**
     * @Description: 加载已写报告
     * @Author: Shalldid
     * @Date: Created in 16:52 2018-05-10
     * @Return:
     **/
    @Override
    public List<RemoteWrittedReportTab> getWrittedReportByFlag(int currIndex, int pageSize, HttpSession httpSession){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        List<RemoteRegister> l = remoteRegisterMapper.getRemoteRegisterPageByFlag("已写报告", currIndex, pageSize);
        List<RemoteWrittedReportTab> remoteWrittedReportTabs = new ArrayList<RemoteWrittedReportTab>();
        RemoteReport remoteReport;
        try{
            for (RemoteRegister remoteRegister : l){
                RemoteWrittedReportTab remoteWrittedReportTab = new RemoteWrittedReportTab();
                remoteWrittedReportTab.setCheckNum(remoteRegister.getChecknum());
                remoteReport = remoteReportMapper.selectBycheckNum(remoteRegister.getChecknum());
                remoteWrittedReportTab.setDocName(userService.getUserByUserId(remoteReport.getDoccode()).getName());
                remoteWrittedReportTab.setExamItemName(remoteRegister.getModality());
                remoteWrittedReportTab.setHosName(hospitalMapper.getHosNameByHosId(remoteRegister.getRemotehos()));
                remoteWrittedReportTab.setId(remoteReport.getId());
                remoteWrittedReportTab.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(remoteRegister.getTagpatientid(),simpleDateFormat.format(remoteRegister.getStudydate()),remoteRegister.getRemotehos()));
                remoteWrittedReportTab.setPatGender(remoteReport.getPatsex());
                remoteWrittedReportTab.setPatient_Age(remoteReport.getPatage());
                remoteWrittedReportTab.setPatName(remoteReport.getPatname());
                remoteWrittedReportTab.setRegisterDate(sdf.format(remoteRegister.getRegdate()));
                remoteWrittedReportTab.setReportDate(sdf.format(remoteReport.getReporttime()));
                remoteWrittedReportTabs.add(remoteWrittedReportTab);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return remoteWrittedReportTabs;
    }


    @Override
    public List<RemoteWrittedReportTab> getBackWaitForReprotByFlag(int currIndex, int pageSize, HttpSession httpSession){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        List<RemoteRegister> l = remoteRegisterMapper.getRemoteRegisterPageByFlag("已分配", currIndex, pageSize);
        List<RemoteWrittedReportTab> remoteWrittedReportTabs = new ArrayList<RemoteWrittedReportTab>();
        Patient p ;
        User u = (User) httpSession.getAttribute("user");
        RemoteReport remoteReport;
        TemporaryReport temporaryReport;
        try{
            for (RemoteRegister remoteRegister : l){
                p = patientMapper.selectByPrimaryKey(remoteRegister.getIdcard());
                RemoteWrittedReportTab remoteWrittedReportTab = new RemoteWrittedReportTab();
                remoteWrittedReportTab.setCheckNum(remoteRegister.getChecknum());
                System.out.println(remoteRegister.getChecknum());
                remoteReport = remoteReportMapper.selectBycheckNum(remoteRegister.getChecknum());
                temporaryReport=temporaryReportMapper.selectBycheckNum(remoteRegister.getChecknum());
//                remoteWrittedReportTab.setDocName(userService.getUserByUserId(remoteReport.getDoccode()).getName());
                remoteWrittedReportTab.setExamItemName(remoteRegister.getModality());
                remoteWrittedReportTab.setHosName(hospitalMapper.getHosNameByHosId(remoteRegister.getRemotehos()));
//                System.out.println(temporaryReport);
//                System.out.println(remoteRegister);
                if(temporaryReport==null){
                    remoteWrittedReportTab.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(remoteRegister.getTagpatientid(), simpleDateFormat.format(remoteRegister.getStudydate()), remoteRegister.getRemotehos()));
//                    remoteWrittedReportTab.setPatGender(remoteReport.getPatsex());
//                    remoteWrittedReportTab.setPatient_Age(remoteReport.getPatage());
//                    remoteWrittedReportTab.setPatName(remoteReport.getPatname());
//                    remoteWrittedReportTab.setRegisterDate(sdf.format(remoteRegister.getRegdate()));
                    remoteWrittedReportTab.setId("");
                    remoteWrittedReportTab.setPatGender(p.getPatgender());
                    remoteWrittedReportTab.setPatient_Age(p.getAge() + p.getAgetype());
                    remoteWrittedReportTab.setPatName(p.getPatname());
                    remoteWrittedReportTab.setRegisterDate(sdf.format(remoteRegister.getRegdate()));

//                remoteWrittedReportTab.setReportDate(sdf.format(remoteReport.getReporttime()));
                    remoteWrittedReportTabs.add(remoteWrittedReportTab);
                }else {
                    remoteWrittedReportTab.setId(temporaryReport.getRemoteReportId());//
                    remoteWrittedReportTab.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(remoteRegister.getTagpatientid(), simpleDateFormat.format(remoteRegister.getStudydate()), remoteRegister.getRemotehos()));
                    System.out.println(remoteReport);
                    remoteWrittedReportTab.setPatGender(remoteReport.getPatsex());
                    remoteWrittedReportTab.setPatient_Age(remoteReport.getPatage());
                    remoteWrittedReportTab.setPatName(remoteReport.getPatname());
                    remoteWrittedReportTab.setRegisterDate(sdf.format(remoteRegister.getRegdate()));
//                remoteWrittedReportTab.setReportDate(sdf.format(remoteReport.getReporttime()));
                    remoteWrittedReportTabs.add(remoteWrittedReportTab);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return remoteWrittedReportTabs;
    }

    /**
     * @Description: 加载已审核报告
     * @Author: Shalldid
     * @Date: Created in 16:52 2018-05-10
     * @Return:
     **/
    @Override
    public List<RemoteVerifiedReportTab> getVerifiedReportByFlag(int currIndex, int pageSize, HttpSession httpSession){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        List<RemoteRegister> l = remoteRegisterMapper.getRemoteRegisterPageByFlag("已审核报告", currIndex, pageSize);
        List<RemoteVerifiedReportTab> remoteVerifiedReportTabs = new ArrayList<RemoteVerifiedReportTab>();
        RemoteReport remoteReport;
        try{
            for (RemoteRegister remoteRegister : l){
                RemoteVerifiedReportTab remoteVerifiedReportTab = new RemoteVerifiedReportTab();
                remoteVerifiedReportTab.setCheckNum(remoteRegister.getChecknum());
                remoteReport = remoteReportMapper.selectBycheckNum(remoteRegister.getChecknum());
                remoteVerifiedReportTab.setDocName(userService.getUserByUserId(remoteReport.getDoccode()).getName());
                remoteVerifiedReportTab.setExamItemName(remoteRegister.getModality());
                remoteVerifiedReportTab.setHosName(hospitalMapper.getHosNameByHosId(remoteRegister.getRemotehos()));
                remoteVerifiedReportTab.setId(remoteReport.getId());
                remoteVerifiedReportTab.setImagePath(imageAndReportPathGenerator.getRemoteImagePath(remoteRegister.getTagpatientid(),simpleDateFormat.format(remoteRegister.getStudydate()),remoteRegister.getRemotehos()));
                remoteVerifiedReportTab.setPatGender(remoteReport.getPatsex());
                remoteVerifiedReportTab.setPatient_Age(remoteReport.getPatage());
                remoteVerifiedReportTab.setPatName(remoteReport.getPatname());
                remoteVerifiedReportTab.setRegisterDate(sdf.format(remoteRegister.getRegdate()));
                remoteVerifiedReportTab.setReportDate(sdf.format(remoteReport.getReporttime()));

                remoteVerifiedReportTab.setVerifyDate(sdf.format(remoteReportMapper.selectBycheckNumAdd(remoteRegister.getChecknum()).getVerifieddate()));
                remoteVerifiedReportTab.setVerifyDocName(userService.getUserByUserId(remoteReportMapper.selectBycheckNumAdd(remoteRegister.getChecknum()).getVerifieddoccode()).getName());


                remoteVerifiedReportTabs.add(remoteVerifiedReportTab);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return remoteVerifiedReportTabs;
    }

//    @Override
//    public   List<RemoteAllocateDto> getsearchResults(String name){return remoteRegisterMapper.selectByName(name);}

    @Override
    public List<RemoteAllocateDto> getRmoteRegister(String checknum, String modality, String pattype,String name,String sex,String age){
            return remoteRegisterMapper.selectAllocateSearch(checknum, modality, pattype,name,sex,age);
    }

    @Override
    public  RemoteRegister getRemoteRegisterById(String id){return remoteRegisterMapper.selectAllocateSearchById(id);}

    @Override
    public List<RemoteRegister> getRemoteAllocateByFlag(String flag){return remoteRegisterMapper.selectRemoteAllocateByFlag(flag);}
}
