package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dto.ClinicDto;
import org.springmvc.pojo.Clinic;
import org.springmvc.service.ClinicService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;


@RequestMapping("/clinic")
@Controller
public class ClinicController {
    @Resource
    private ClinicService clinicService;

    @RequestMapping(value = "/{idcard}/getClinicInfo", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getClinicInfo(@PathVariable("idcard") String idcard){
//        System.out.println("#################1");
//        System.out.println(idcard);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Clinic c = clinicService.getAllInfo(idcard);
        System.out.println(c);
            ClinicDto clinic=new ClinicDto(c.getIdcard(),c.getYibaoid(),c.getPatname(),c.getAge(),c.getPatgender(),c.getClinicdoc(),sdf.format(c.getPatbirthdate()),c.getTelephone(),c.getAddress(),sdf.format(c.getUpdatetime()),c.getEntity(),c.getMainsuit(),c.getPersonalhis(),c.getMaritalhis(),c.getFamilyhis(),c.getPastillnesshis(),c.getPresentillnesshis(),c.getSpecialitycheck(),c.getTentativediagnosis());
//            System.out.println("#################");
//        System.out.println("@@@@@@@@@@@@@@@@@@");
        System.out.println(clinic);
        System.out.println(JSON.toJSONString(clinic));
        return JSON.toJSONString(clinic);
    }
}
