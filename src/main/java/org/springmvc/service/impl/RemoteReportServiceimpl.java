package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.RemoteReportMapper;
import org.springmvc.dao.TemporaryReportMapper;
import org.springmvc.dao.UserMapper;
import org.springmvc.dto.RemoteSearchReportTab;
import org.springmvc.pojo.HisInfo;
import org.springmvc.pojo.RemoteReport;
import org.springmvc.pojo.TemporaryReport;
import org.springmvc.pojo.User;
import org.springmvc.service.RemoteReportService;
import org.springmvc.service.UserService;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RemoteReportServiceimpl implements RemoteReportService {

    @Autowired
    private RemoteReportMapper remoteReportMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TemporaryReportMapper temporaryReportMapper;
    /**
     * @Description: 插入新的报告
     * @Author: Shalldid
     * @Date: Created in 16:28 2018-05-10
     * @Return:
     **/
    @Override
    public int insertNewReport(RemoteReport remoteReport){
        return remoteReportMapper.insert(remoteReport);
    }


    @Override
    public  int insertNewReportToTemporaryReport(TemporaryReport temporaryReport){return temporaryReportMapper.insert(temporaryReport);}
    /**
     * @Description: 根据检查号加载报告
     * @Author: Shalldid
     * @Date: Created in 16:58 2018-05-10
     * @Return:
     **/
    @Override
    public RemoteReport getReportByChecknum(String checknum){
        return remoteReportMapper.selectBycheckNum(checknum);
    }
    /**
     * @Description: 根据id加载报告
     * @Author: Shalldid
     * @Date: Created in 16:58 2018-05-10
     * @Return:
     **/
    @Override
    public RemoteReport getReportById(String id){
        return remoteReportMapper.selectByPrimaryKey(id);
    }



    @Override
    public RemoteReport getReportByIdAdd(String id){
        return remoteReportMapper.selectByPrimaryKeyAdd(id);
    }



    @Override
    public  TemporaryReport getTemporaryReportById(String id){return temporaryReportMapper.selectByPrimaryKey(id);}

    /**
     * @Description: 报告回退时删除报告
     * @Author: Shalldid
     * @Date: Created in 16:56 2018-05-14
     * @Return:
     **/

    @Override
    public int deleteReportById(String id){return remoteReportMapper.deleteById(id);}


    @Override
    public int updateReportById(String id){return remoteReportMapper.updateById(id);}

    /**
     * @Description: 更新报告
     * @Author: Shalldid
     * @Date: Created in 16:56 2018-05-14
     * @Return:
     **/
    @Override
    public int updateReport(RemoteReport remoteReport){
        return remoteReportMapper.updateByPrimaryKey(remoteReport);
    }


    @Override
    public int updateReportAdd(RemoteReport remoteReport){return remoteReportMapper.updateByPrimaryKeyAdd(remoteReport);}
    /**
     * @Description: 加载当天报告
     * @Author: Shalldid
     * @Date: Created in 16:56 2018-05-14
     * @Return:
     **/
    public List<RemoteSearchReportTab> getTodayReport(int currIndex, int pageSize, HttpSession httpSession){
        User user = (User)httpSession.getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d_temp = new Date();
        String d_temp_string = (sdf.format(d_temp)).substring(0,10);
        Date datestart = null;
        Date dateend = null;
        try {
            datestart = sdf.parse(d_temp_string + " 00:00:00");
            dateend = sdf.parse(d_temp_string + " 23:59:59");
            List<RemoteReport> remoteReports = remoteReportMapper.getTodayReportListByPagination(datestart, dateend, currIndex, pageSize,userService.getHosIdOfUser(user.getDept()));
            List<RemoteSearchReportTab> remoteSearchReportTabs = new ArrayList<RemoteSearchReportTab>();
            for (RemoteReport r : remoteReports){
                RemoteSearchReportTab remoteSearchReportTab = new RemoteSearchReportTab();
                remoteSearchReportTab.setCheck_number(r.getChecknum());
                remoteSearchReportTab.setModality(r.getModality());
                remoteSearchReportTab.setPatient_Age(r.getPatage());
                remoteSearchReportTab.setPatient_Name(r.getPatname());
                remoteSearchReportTab.setPatient_Sex(r.getPatsex());
                remoteSearchReportTab.setReport_Date(sdf.format(r.getReporttime()));
                remoteSearchReportTabs.add(remoteSearchReportTab);
            }
            return remoteSearchReportTabs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @Description: get Report by Condition
     * @Author: Shalldid
     * @Date: Created in 14:22 2018-05-15
     * @Return:
     **/
    public List<RemoteSearchReportTab> getRemoteReportByCondition(int currIndex, int pageSize, String modality, String pat_type,
                                                           String dateBegin, String dateEnd, String name, String id,
                                                           String number, String clinic_id, HttpSession httpSession) throws ParseException {
        User u = (User)httpSession.getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append(" 1=1 ");
        sb.append(" and juniorhos='" + userService.getHosIdOfUser(u.getDept()) + "'");
        if(!"".equals(modality)){
            sb.append(" and modality='" + modality +"'");
        }
        if(!"".equals(name)){
            sb.append(" and patname='" + name + "'");
        }
//        if(!"".equals(id)){
//            sb.append(" and patientID='" + id + "'");
//        }
        //病人ID
        if(!"".equals(number)){
            sb.append(" and checknum='" + number + "'");
        }
        if(!"".equals(clinic_id)) {
            sb.append(" and clinicid='" + clinic_id + "'");
        }
        if(!"".equals(dateBegin) || !"".equals(dateEnd)){
            Calendar c = Calendar.getInstance();
            if("".equals(dateBegin) && !"".equals(dateEnd)){
                Date date_end_temp = sdf.parse(dateEnd);
                c.setTime(date_end_temp);
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                sb.append(" and reporttime between '" + "2000-01-01" + "' and '" + sdf.format(c.getTime()) + "'");
            }else if("".equals(dateEnd) && !"".equals(dateBegin)){
                Date date_start_temp = sdf.parse(dateBegin);
                Date today = new Date();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, 1);
                sb.append(" and reporttime between '" + dateBegin + "' and '" + sdf.format(c.getTime()) + "'");
            }else {
                Date date_end_temp = sdf.parse(dateEnd);
                c.setTime(date_end_temp);
                c.add(Calendar.DAY_OF_MONTH, 1);
                sb.append(" and reporttime between '" + dateBegin + "' and '" + sdf.format(c.getTime()) + "'");
            }
        }
        System.out.println(sb.toString());
        try {
            List<RemoteReport> remoteReports = remoteReportMapper.getReportListByCondition(currIndex, pageSize, sb.toString());
            List<RemoteSearchReportTab> remoteSearchReportTabs = new ArrayList<RemoteSearchReportTab>();
            for (RemoteReport r : remoteReports) {
                RemoteSearchReportTab remoteSearchReportTab = new RemoteSearchReportTab();
                remoteSearchReportTab.setCheck_number(r.getChecknum());
                remoteSearchReportTab.setModality(r.getModality());
                remoteSearchReportTab.setPatient_Age(r.getPatage());
                remoteSearchReportTab.setPatient_Name(r.getPatname());
                remoteSearchReportTab.setPatient_Sex(r.getPatsex());
                remoteSearchReportTab.setReport_Date(sdf.format(r.getReporttime()));
                remoteSearchReportTabs.add(remoteSearchReportTab);
            }
            return remoteSearchReportTabs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
