package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.*;
import org.springmvc.dto.RemoteWaitForReportTab;
import org.springmvc.dto.RemoteWrittedReportTab;
import org.springmvc.pojo.Patient;
import org.springmvc.pojo.RemoteRegister;
import org.springmvc.pojo.RemoteReport;
import org.springmvc.pojo.User;
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
        List<RemoteRegister> l = remoteRegisterMapper.getRemoteRegisterPageByFlag("已上传图像", currIndex, pageSize);
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
}
