package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.HisInfoMapper;
import org.springmvc.dao.PatientMapper;
import org.springmvc.dao.RegisterInfoMapper;
import org.springmvc.dto.TodayReportTab;
import org.springmvc.pojo.HisInfo;
import org.springmvc.pojo.Patient;
import org.springmvc.pojo.RegisterInfo;
import org.springmvc.service.HisInfoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HisInfoServiceimpl implements HisInfoService {

    @Autowired
    private HisInfoMapper hisInfoMapper;

    @Autowired
    private RegisterInfoMapper registerInfoMapper;

    @Autowired
    private PatientMapper patientMapper;

    /**
     * @Description: 保存新的HisInfo
     * @Author: Shalldid
     * @Date: Created in 10:59 2018-05-07
     * @Return:
     **/
    @Override
    public int saveNewHisInfo(HisInfo hisInfo){
        return hisInfoMapper.insertSelective(hisInfo);
    }
    /**
     * @Description: 根据病人ID更新报告产生的日期和图像地址
     * @Author: Shalldid
     * @Date: Created in 11:59 2018-05-08
     * @Return:
     **/
    @Override
    public int updateHisInfoTime(String patid, Date date, String imagePath){
        return hisInfoMapper.updateHisInfoDateByPatid(patid,date,imagePath);
    }
    /**
     * @Description: 获取当天的报告数量
     * @Author: Shalldid
     * @Date: Created in 15:31 2018-05-08
     * @Return:
     **/
    @Override
    public int countReportToday(String hosId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d_temp = new Date();
        String d_temp_string = (sdf.format(d_temp)).substring(0,10);
        Date datestart = null;
        Date dateend = null;
        try {
            datestart = sdf.parse(d_temp_string + " 00:00:00");
            dateend = sdf.parse(d_temp_string + " 23:59:59");
            return hisInfoMapper.countReportToday(datestart,dateend,hosId);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * @Description: 分页加载当天的报告列表
     * @Author: Shalldid
     * @Date: Created in 16:14 2018-05-08
     * @Return:
     **/
    @Override
    public List<TodayReportTab> getTodayReportList(int currIndex, int pageSize,String hosId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d_temp = new Date();
        String d_temp_string = (sdf.format(d_temp)).substring(0,10);
        Date datestart = null;
        Date dateend = null;
        try {
            datestart = sdf.parse(d_temp_string + " 00:00:00");
            dateend = sdf.parse(d_temp_string + " 23:59:59");
            List<HisInfo> hisInfos = hisInfoMapper.getTodayReportListByPagination(datestart, dateend, hosId,currIndex, pageSize);
            List<TodayReportTab> todayReportTabs = new ArrayList<TodayReportTab>();
            RegisterInfo r;
            Patient p;
            for(HisInfo h : hisInfos){
                TodayReportTab t = new TodayReportTab();
                t.setCheck_number(h.getChecknum());
                t.setModality(h.getModality());
                t.setPat_type(h.getPattype());
                r = registerInfoMapper.selectByPrimaryKey(h.getChecknum());
                p = patientMapper.selectByPrimaryKey(r.getIdcard());
                t.setPatient_Age(p.getAge() + p.getAgetype());
                t.setPatient_Name(p.getPatname());
                t.setPatient_Sex(p.getPatgender());
                t.setReport_Date(sdf.format(h.getReportdate()));
                t.setSqks(h.getSqks());
                todayReportTabs.add(t);
            }
            return todayReportTabs;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @Description: 通过checknum加载hisinfo
     * @Author: Shalldid
     * @Date: Created in 15:16 2018-05-09
     * @Return:
     **/
    @Override
    public HisInfo getHisInfoByCheckNum(String checkNum){
        return hisInfoMapper.selectByCheckNum(checkNum);
    }

    @Override
    public List<TodayReportTab> getTodaReportListByCondition(int currIndex, int pageSize, String modality, String pat_type, String dateBegin, String dateEnd,
                                                             String name, String id, String number, String clinic_id) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append(" 1=1 ");
        if(!"".equals(modality)){
            sb.append(" and modality='" + modality +"'");
        }
        if(!"".equals(pat_type)){
            sb.append(" and patType='" + pat_type + "'");
        }
        if(!"".equals(name)){
            sb.append(" and patname='" + name + "'");
        }
        if(!"".equals(id)){
            sb.append(" and patientID='" + id + "'");
        }
        if(!"".equals(number)){
            sb.append(" and checkNum='" + number + "'");
        }
        if(!"".equals(clinic_id)) {
            sb.append(" and clinicID='" + clinic_id + "'");
        }
        if(!"".equals(dateBegin) || !"".equals(dateEnd)){
            Calendar c = Calendar.getInstance();
            if("".equals(dateBegin) && !"".equals(dateEnd)){
                Date date_end_temp = sdf.parse(dateEnd);
                c.setTime(date_end_temp);
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                sb.append(" and reportDate between '" + "2000-01-01" + "' and '" + sdf.format(c.getTime()) + "'");
            }else if("".equals(dateEnd) && !"".equals(dateBegin)){
                Date date_start_temp = sdf.parse(dateBegin);
                Date today = new Date();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, 1);
                sb.append(" and reportDate between '" + dateBegin + "' and '" + sdf.format(c.getTime()) + "'");
            }else {
                Date date_end_temp = sdf.parse(dateEnd);
                c.setTime(date_end_temp);
                c.add(Calendar.DAY_OF_MONTH, 1);
                sb.append(" and reportDate between '" + dateBegin + "' and '" + sdf.format(c.getTime()) + "'");
            }
        }
        System.out.println(sb.toString());
        List<HisInfo> hisInfos = hisInfoMapper.getReportListByCondition(currIndex, pageSize, sb.toString());
        List<TodayReportTab> todayReportTabs = new ArrayList<TodayReportTab>();
        RegisterInfo r;
        Patient p;
        for(HisInfo h : hisInfos){
            TodayReportTab t = new TodayReportTab();
            t.setCheck_number(h.getChecknum());
            t.setModality(h.getModality());
            t.setPat_type(h.getPattype());
            r = registerInfoMapper.selectByPrimaryKey(h.getChecknum());
            p = patientMapper.selectByPrimaryKey(r.getIdcard());
            t.setPatient_Age(p.getAge() + p.getAgetype());
            t.setPatient_Name(p.getPatname());
            t.setPatient_Sex(p.getPatgender());
            t.setReport_Date(sdf.format(h.getReportdate()));
            t.setSqks(h.getSqks());
            todayReportTabs.add(t);
        }
        return todayReportTabs;
    }

    @Override
    public int delete(String checknum){return hisInfoMapper.deleteByCheckNum(checknum);}

    @Override
    public int getCountByCheckNum(String checknum){return hisInfoMapper.selectCountByCheckNum(checknum);}
}
