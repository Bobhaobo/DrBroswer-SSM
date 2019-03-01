package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springmvc.dto.ReturnStatus;
import org.springmvc.dto.SelectPicker;
import org.springmvc.pojo.*;
import org.springmvc.pojo_inner.DicomWorkListInner;
import org.springmvc.pojo_inner.RegisterInfoInner;
import org.springmvc.service.HospitalService;
import org.springmvc.service.RegInfoService;
import org.springmvc.service.UserService;
import org.springmvc.tool.SeriesNumGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    /**
     * @Description: 医院本地设备登记
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
        RegisterInfoInner register = regInfoService.insertNewRegInfoLocal(clinicID,PatName,patGender,birthday,address,societyID,PatIDCard,tel,bedroom,bedNo,"DEFAULT","默认",
                checkType,card_num,jcbw,sjmd,sqdbh,PatType,checkDevice,httpSession);
        ReturnStatus rs = new ReturnStatus();
        if(register != null){
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
}
