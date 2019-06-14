package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springmvc.dto.*;
import org.springmvc.pojo.Department;
import org.springmvc.pojo.ExamItem;
import org.springmvc.pojo.Patient;
import org.springmvc.pojo.User;
import org.springmvc.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Description: 这个Controller主要对应登记信息
 *@Author: Shalldid
 *@Date:Created in 14:21 2018-04-24
 **/
@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Resource
    private PatientService patientService;

    @Resource
    private RegInfoService regInfoService;

    @Resource
    private OrderTableService orderTableService;

    @Resource
    private UserService userService;

    @Resource
    private HospitalService hospitalService;
    /**
     *@Description: 登记信息到reg_info表和patlist表，以后要更新到worklist表
     *@Author: Shalldid
     *@Date:Created in 9:51 2018-04-25
     **/
    @RequestMapping(value = "/patient_check_register", method = RequestMethod.POST)
    @ResponseBody
    public String registerInfo(@RequestParam("pat_name") String pat_name, @RequestParam("pat_id_card") String pat_id_card,
                               @RequestParam("pat_gender") String pat_gender, @RequestParam("brithday") String brithday,
                               @RequestParam("pat_address") String pat_address,
                               @RequestParam("pat_soicalId") String pat_soicalId, @RequestParam("pat_phone") String pat_phone,
                               @RequestParam("check_hos") String check_hos, @RequestParam("apply_date") String apply_date,
                               @RequestParam("check_type") String check_type, @RequestParam("jcbw") String jcbw,
                               @RequestParam("clinicID") String clinicID, @RequestParam("sqdbh") String sqdbh,
                               @RequestParam("sqks") String sqks, @RequestParam("bedroom") String bedroom,
                               @RequestParam("bedNo") String bedNo, @RequestParam("sjmd") String sjmd,
                               @RequestParam("card_num") String card_num, Model model, HttpSession httpSession) throws ParseException {
        int status_pat = patientService.insertOrUpdatePat(pat_name,pat_id_card,pat_gender,brithday,pat_address,pat_soicalId,
                pat_phone); //插入病人信息
        String check_num = regInfoService.insertNewRegInfo(clinicID,bedNo,check_type,card_num,jcbw,sjmd,sqdbh,sqks,
                pat_id_card);   //插入登记信息
        int status_order = orderTableService.insertNewOrderTable(check_num, apply_date, userService.getHosIdOfUser(((User)httpSession.getAttribute("user")).getDept()));
        ReturnStatus rs = new ReturnStatus();
        if(status_pat > 0 && !"error".equals(check_num) && status_order > 0){
            rs.setStatusCode("200");
            rs.setMessage("操作成功！");
        }else if(status_pat < 0){
            rs.setStatusCode("300");
            rs.setMessage("生成病人信息失败！");
        }else if("error".equals(check_num)){
            rs.setStatusCode("300");
            rs.setMessage("生成登记信息失败！");
        }else if(status_order < 0){
            rs.setStatusCode("300");
            rs.setMessage("生成预约信息失败！");
        }else{
            rs.setStatusCode("300");
            rs.setMessage("未知错误！");
        }
        return JSON.toJSONString(rs);
    }
    /**
     * @Description: 登记信息时，加载可申请的检查医院
     * @Author: Shalldid
     * @Date: Created in 16:57 2018-04-27
     * @Return:
     **/
    @RequestMapping(value = "/load_check_hos", method = RequestMethod.POST, produces="text/html; charset=UTF-8")    //这里produces不能设置为text/json，因为返回的不是严格的json，前端会出错
    @ResponseBody
    public String loadCheckHospital(HttpSession httpSession, HttpServletResponse response) {
        User u = (User) httpSession.getAttribute("user");
        String hos_id_u = userService.getHosIdOfUser(u.getDept());
        List<String> major_hos_list = hospitalService.getApplyHospitalIdByHosid(hos_id_u);
        List<Hos4Reg> l = new ArrayList<Hos4Reg>();
        Hos4Reg h4r = new Hos4Reg();
        for(String s : major_hos_list){
            h4r.setId(s);
            h4r.setName(hospitalService.getHosNameByHosId(s));
            l.add(h4r);
        }
        //System.out.println(JSON.toJSONString(l));
        return JSON.toJSONString(l);    //返回json字符串
    }
    /**
     * @Description: 加载医院可用检查设备
     * @Author: Shalldid
     * @Date: Created in 11:18 2018-04-28
     * @Return:
     **/
    @RequestMapping(value = "/loadCheckItem/{hosId}", produces="text/html; charset=UTF-8")
    @ResponseBody
    public String loadCheckItemByHosId(@PathVariable("hosId") String hosId){

        System.out.println("sdasd");
        List<ExamItem> examItems = hospitalService.getExamItemByHosId(hosId);
        List<SelectPicker> list = new ArrayList<SelectPicker>();
        //ExamItemInfo examItemInfo = new ExamItemInfo();   //不能在外面新建对象，具体参考ArrayList的add过程
        for(ExamItem e : examItems){
            SelectPicker examItemInfo = new SelectPicker();
            examItemInfo.setLabel(e.getExamitemname());
            examItemInfo.setValue(e.getExamitemname());
            list.add(examItemInfo);
        }
        return JSON.toJSONString(list);
    }

    /**
     * @Description: 加载医院可申请科室
     * @Author: Shalldid
     * @Date: Created in 15:42 2018-07-04
     * @Return:
     **/
    @RequestMapping(value = "/loadApplyDept/{hosid}",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String loadApplyDept(@PathVariable("hosid") String hosid){

        List<Department> departments = hospitalService.getDeptListByHosid(hosid);
        List<SelectPicker> list = new ArrayList<SelectPicker>();
        //ExamItemInfo examItemInfo = new ExamItemInfo();   //不能在外面新建对象，具体参考ArrayList的add过程
        for(Department d: departments){
            SelectPicker examItemInfo = new SelectPicker();
            examItemInfo.setLabel(d.getDeptname());
            examItemInfo.setValue(d.getId());
            list.add(examItemInfo);
        }
        return JSON.toJSONString(list);
    }
    /**
     * @Description: 登记时申请科室列表
     * @Author: Shalldid
     * @Date: Created in 15:20 2018-05-17
     * @Return:
     **/
    @RequestMapping(value = "/loadDeptList", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String loadDeptList(){
        List<Department> departments = hospitalService.getDeptListByHosid("H0001"); //目前就一家上级医院 先写定为H0001
        List<SelectPicker> list = new ArrayList<SelectPicker>();
        for (Department department : departments){
            SelectPicker deptInfo = new SelectPicker();
            deptInfo.setValue(department.getId());
            deptInfo.setLabel(department.getDeptname());
            list.add(deptInfo);
        }
        return JSON.toJSONString(list);
    }
}
