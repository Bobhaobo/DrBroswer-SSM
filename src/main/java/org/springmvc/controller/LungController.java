package org.springmvc.controller;


import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dto.*;
import org.springmvc.pojo.Patient;
import org.springmvc.pojo.lung;
import org.springmvc.service.LungService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/lung")
@Controller
public class LungController {

    @Resource
    private LungService lungService;

    @RequestMapping(value = "/getLungInfo1", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getLungInfo1(Pagination p, @RequestParam("idcard122") String idcard,
                              @RequestParam("patname122") String patname,
                              @RequestParam("patientid122") String patientid,
                              @RequestParam("checkdate122") String checkdate,
                              @RequestParam("entity122") String entity,
                              HttpSession httpSession) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        lung lungs = new lung();
        List<lung> lungs1 = new ArrayList<lung>();
        if (idcard != null && !("".equals(idcard))) {
            System.out.println("hahaha");
            lungs = lungService.getPatInfo(idcard);
            lungDto lungDtos = new lungDto(lungs.getPatientid(), lungs.getPatname(), lungs.getPatgender(), lungs.getAge(), lungs.getExamdoc(), lungs.getImageclass(), lungs.getCheckpart(), lungs.getScanmethod(), lungs.getScanway(), lungs.getClinicdiagnosis(), sdf.format(lungs.getCheckdate()), sdf.format(lungs.getReportdate()), lungs.getImageview(), lungs.getImagesuggestion(), lungs.getIdcard(),lungs.getEntity());
            System.out.println(lungDtos);
            return JSON.toJSONString(lungDtos);
        } else if (patname != null && !("".equals(patname))) {
            System.out.println("lalala");
                lungs1 = lungService.getPatInfo1(patname);
                List<lungDto> lungDtos=new ArrayList<lungDto>();
                for(int i=0;i<lungs1.size();i++){
                    lungDto l=new lungDto();
                    l.setAge(lungs1.get(i).getAge());
                    l.setCheckdate(sdf.format(lungs1.get(i).getCheckdate()));
                    l.setCheckpart(lungs1.get(i).getCheckpart());
                    l.setClinicdiagnosis(lungs1.get(i).getClinicdiagnosis());
                    l.setExamdoc(lungs1.get(i).getExamdoc());
                    l.setIdcard(lungs1.get(i).getIdcard());
                    l.setImageclass(lungs1.get(i).getImageclass());
                    l.setImagesuggestion(lungs1.get(i).getImagesuggestion());
                    l.setImageview(lungs1.get(i).getImageview());
                    l.setPatgender(lungs1.get(i).getPatgender());
                    l.setPatientid(lungs1.get(i).getPatientid());
                    l.setPatname(lungs1.get(i).getPatname());
                    l.setReportdate(sdf.format(lungs1.get(i).getReportdate()));
                    l.setScanmethod(lungs1.get(i).getScanmethod());
                    l.setScanway(lungs1.get(i).getScanway());
                    l.setEntity(lungs1.get(i).getEntity());
                    lungDtos.add(l);
                }
            System.out.println(lungs1);
            System.out.println(lungDtos);
                return JSON.toJSONString(lungDtos);
            }
            else if(patientid!=null && !"".equals(patientid)){
            System.out.println("qqq");
                lungs=lungService.getPatInfoByPatid(patientid);
            System.out.println(patientid);
            System.out.println("lalala");
            System.out.println(lungs);
            lungDto lungDtos = new lungDto(lungs.getPatientid(), lungs.getPatname(), lungs.getPatgender(), lungs.getAge(), lungs.getExamdoc(), lungs.getImageclass(), lungs.getCheckpart(), lungs.getScanmethod(), lungs.getScanway(), lungs.getClinicdiagnosis(), sdf.format(lungs.getCheckdate()), sdf.format(lungs.getReportdate()), lungs.getImageview(), lungs.getImagesuggestion(), lungs.getIdcard());
            System.out.println(lungDtos);
            return JSON.toJSONString(lungDtos);
        }else if(checkdate!=null && !("".equals(checkdate))){
//            System.out.println("hxd");
                lungs1=lungService.getPatInfoByCheckdate(checkdate);
//            System.out.println(checkdate);
//            System.out.println(lungs1);
            List<lungDto> lungDtos=new ArrayList<lungDto>();
            for(int i=0;i<lungs1.size();i++){
                lungDto l=new lungDto();
                l.setAge(lungs1.get(i).getAge());
                l.setCheckdate(sdf.format(lungs1.get(i).getCheckdate()));
                l.setCheckpart(lungs1.get(i).getCheckpart());
                l.setClinicdiagnosis(lungs1.get(i).getClinicdiagnosis());
                l.setExamdoc(lungs1.get(i).getExamdoc());
                l.setIdcard(lungs1.get(i).getIdcard());
                l.setImageclass(lungs1.get(i).getImageclass());
                l.setImagesuggestion(lungs1.get(i).getImagesuggestion());
                l.setImageview(lungs1.get(i).getImageview());
                l.setPatgender(lungs1.get(i).getPatgender());
                l.setPatientid(lungs1.get(i).getPatientid());
                l.setPatname(lungs1.get(i).getPatname());
                l.setReportdate(sdf.format(lungs1.get(i).getReportdate()));
                l.setScanmethod(lungs1.get(i).getScanmethod());
                l.setScanway(lungs1.get(i).getScanway());
                l.setEntity(lungs1.get(i).getEntity());
                lungDtos.add(l);
                System.out.println(i);
                System.out.println(lungDtos);
            }
            return JSON.toJSONString(lungDtos);
        }else if (entity != null && !("".equals(entity))) {
            System.out.println("lalala");
            lungs1 = lungService.getPatInfoByentity(entity);
            List<lungDto> lungDtos=new ArrayList<lungDto>();
            for(int i=0;i<lungs1.size();i++){
                lungDto l=new lungDto();
                l.setAge(lungs1.get(i).getAge());
                l.setCheckdate(sdf.format(lungs1.get(i).getCheckdate()));
                l.setCheckpart(lungs1.get(i).getCheckpart());
                l.setClinicdiagnosis(lungs1.get(i).getClinicdiagnosis());
                l.setExamdoc(lungs1.get(i).getExamdoc());
                l.setIdcard(lungs1.get(i).getIdcard());
                l.setImageclass(lungs1.get(i).getImageclass());
                l.setImagesuggestion(lungs1.get(i).getImagesuggestion());
                l.setImageview(lungs1.get(i).getImageview());
                l.setPatgender(lungs1.get(i).getPatgender());
                l.setPatientid(lungs1.get(i).getPatientid());
                l.setPatname(lungs1.get(i).getPatname());
                l.setReportdate(sdf.format(lungs1.get(i).getReportdate()));
                l.setScanmethod(lungs1.get(i).getScanmethod());
                l.setScanway(lungs1.get(i).getScanway());
                l.setEntity(lungs1.get(i).getEntity());
                lungDtos.add(l);
            }
            System.out.println(lungs1);
            System.out.println(lungDtos);
            return JSON.toJSONString(lungDtos);
        }
            else{
            return JSON.toJSONString(0);
        }
    }


    @RequestMapping(value = "/getLungInfo", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getLungInfo(Pagination p, @RequestParam("idcard122") String idcard,
                               HttpSession httpSession) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        lung lungs = new lung();
        List<lung> lungs1 = new ArrayList<lung>();
            lungs = lungService.getPatInfo(idcard);
            lungDto lungDtos = new lungDto(lungs.getPatientid(), lungs.getPatname(), lungs.getPatgender(), lungs.getAge(), lungs.getExamdoc(), lungs.getImageclass(), lungs.getCheckpart(), lungs.getScanmethod(), lungs.getScanway(), lungs.getClinicdiagnosis(), sdf.format(lungs.getCheckdate()), sdf.format(lungs.getReportdate()), lungs.getImageview(), lungs.getImagesuggestion(), lungs.getIdcard(),lungs.getEntity());
            LungPaginationResult lungPaginationResult = new LungPaginationResult();
            lungPaginationResult.setLungDto(lungDtos);
            //编译Dicom图片
            //返回的base64编码的字符串
            String base64Url = null;
            try {
                //File src = new File("E:\\1.dcm");
                File src = new File("C:\\Users\\li\\Desktop\\2.dcm");
//			File src = new File("E:\\I170.dcm");
                Dcm2jpgIOStreamOutput dcm2jpg = new Dcm2jpgIOStreamOutput();
                //,windowWidth,windowCenter
                //返回的base64编码的字符串
                base64Url = dcm2jpg.convert(src);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        paginationResult.setBase64Url(base64Url);
            lungPaginationResult.setBase64Url(base64Url);
//        System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(lungPaginationResult);

    }
}
