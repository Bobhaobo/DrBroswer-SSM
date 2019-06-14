package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dto.*;
import org.springmvc.pojo.Pathology;
import org.springmvc.pojo.lung;
import org.springmvc.service.PathologyService;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/pathology")
@Controller
public class PathologyController {

    @Resource
    private PathologyService pathologyService;

    @RequestMapping(value = "getPathologyInfo",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getPathologyInfo(Pagination p, @RequestParam("idcard022") String idcard,
                               HttpSession httpSession) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Pathology pathology = new Pathology();
        pathology = pathologyService.getAllInfoByidcard(idcard);
        System.out.println(idcard);
        PathologyDto pathologydto = new PathologyDto(pathology.getIdcard(),pathology.getPatname(),pathology.getSex(),pathology.getAge(),sdf.format(pathology.getReportDate()),pathology.getSendHospital(),pathology.getSendDept(),pathology.getCheckDoc(),pathology.getSpecimenName(),pathology.getClinicDiagnosis(),pathology.getEyeView(),pathology.getGlassView(),pathology.getPathologyDiagnosis(),pathology.getPatientid(),pathology.getEntity());
        System.out.println(pathologydto);
        System.out.println("hxd");
        PathologypaginationResult pathologypaginationResult = new PathologypaginationResult();
        pathologypaginationResult.setPathologydto(pathologydto);
        File src = new File("C:\\Users\\li\\Desktop\\111.jpg");
        InputStream in = null;
        in = new FileInputStream(src);
        byte[] data = null;
        data = new byte[in.available()];
        in.read(data);
        in.close();
        BASE64Encoder encoder = new BASE64Encoder();
        pathologypaginationResult.setImage("data:image/jpeg;base64,"+encoder.encode(data));
        System.out.println(pathologypaginationResult);
        return JSON.toJSONString(pathologypaginationResult);
    }



    @RequestMapping(value = "/getPathologyInfo1",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getPathologyInfo1(Pagination p, @RequestParam("idcard020") String idcard,
                                    @RequestParam("patname020") String patname,
                                    @RequestParam("patientid020") String patientid,
                                    @RequestParam("reportdate020") String reportdate,
                                    @RequestParam("entity020") String entity,
                                    HttpSession httpSession) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Pathology pathology=new Pathology();
        List<Pathology> pathologyList = new ArrayList<Pathology>();
        if (idcard != null && !("".equals(idcard))) {
            System.out.println("hahaha");
            pathology = pathologyService.getAllInfoByidcard(idcard);
            System.out.println(pathology);
            PathologyDto pathologyDto = new PathologyDto(pathology.getIdcard(),pathology.getPatname(),pathology.getSex(),pathology.getAge(),sdf.format(pathology.getReportDate()),pathology.getSendHospital(),pathology.getSendDept(),pathology.getCheckDoc(),pathology.getSpecimenName(),pathology.getClinicDiagnosis(),pathology.getEyeView(),pathology.getGlassView(),pathology.getPathologyDiagnosis(),pathology.getPatientid(),pathology.getEntity());
            return JSON.toJSONString(pathologyDto);
        } else if (patname != null && !("".equals(patname))) {
            System.out.println("lalala");
            pathologyList = pathologyService.getAllInfoByPatName(patname);
            System.out.println(patname);
            List<PathologyDto> pathologyDtoList=new ArrayList<PathologyDto>();
            System.out.println("xhd");
            for(int i=0;i<pathologyList.size();i++){
                PathologyDto pathologyDto=new PathologyDto();
                pathologyDto.setAge(pathologyList.get(i).getAge());
                pathologyDto.setReportDate(sdf.format(pathologyList.get(i).getReportDate()));
                pathologyDto.setCheckDoc(pathologyList.get(i).getCheckDoc());
                pathologyDto.setClinicDiagnosis(pathologyList.get(i).getClinicDiagnosis());
                pathologyDto.setEyeView(pathologyList.get(i).getEyeView());
                pathologyDto.setIdcard(pathologyList.get(i).getIdcard());
                pathologyDto.setGlassView(pathologyList.get(i).getGlassView());
                pathologyDto.setPathologyDiagnosis(pathologyList.get(i).getPathologyDiagnosis());
                pathologyDto.setPatientid(pathologyList.get(i).getPatientid());
                pathologyDto.setPatname(pathologyList.get(i).getPatname());
                pathologyDto.setSendHospital(pathologyList.get(i).getSendHospital());
                pathologyDto.setSendDept(pathologyList.get(i).getSendDept());
                pathologyDto.setSpecimenName((pathologyList.get(i).getSpecimenName()));
                pathologyDto.setSex(pathologyList.get(i).getSex());
                pathologyDto.setEntity(pathologyList.get(i).getEntity());
                pathologyDtoList.add(pathologyDto);
            }
            System.out.println(pathologyDtoList);
            return JSON.toJSONString(pathologyDtoList);
        }
        else if(patientid!=null && !"".equals(patientid)){
            System.out.println("qqq");
            pathology=pathologyService.getAllInfoByPatid(patientid);
            System.out.println(patientid);
            System.out.println("lalala");
            PathologyDto pathologyDto = new PathologyDto(pathology.getIdcard(),pathology.getPatname(),pathology.getSex(),pathology.getAge(),sdf.format(pathology.getReportDate()),pathology.getSendHospital(),pathology.getSendDept(),pathology.getCheckDoc(),pathology.getSpecimenName(),pathology.getClinicDiagnosis(),pathology.getEyeView(),pathology.getGlassView(),pathology.getPathologyDiagnosis(),pathology.getPatientid(),pathology.getEntity());
            System.out.println(pathologyDto);
            return JSON.toJSONString(pathologyDto);
        }else if(reportdate!=null && !("".equals(reportdate))){
            System.out.println("hxd");
            pathologyList=pathologyService.getAllInfoBydate(reportdate);
            List<PathologyDto> pathologyDtoList=new ArrayList<PathologyDto>();
            for(int i=0;i<pathologyList.size();i++){
                PathologyDto pathologyDto=new PathologyDto();
                pathologyDto.setAge(pathologyList.get(i).getAge());
                pathologyDto.setReportDate(sdf.format(pathologyList.get(i).getReportDate()));
                pathologyDto.setCheckDoc(pathologyList.get(i).getCheckDoc());
                pathologyDto.setClinicDiagnosis(pathologyList.get(i).getClinicDiagnosis());
                pathologyDto.setEyeView(pathologyList.get(i).getEyeView());
                pathologyDto.setIdcard(pathologyList.get(i).getIdcard());
                pathologyDto.setGlassView(pathologyList.get(i).getGlassView());
                pathologyDto.setPathologyDiagnosis(pathologyList.get(i).getPathologyDiagnosis());
                pathologyDto.setPatientid(pathologyList.get(i).getPatientid());
                pathologyDto.setPatname(pathologyList.get(i).getPatname());
                pathologyDto.setSendHospital(pathologyList.get(i).getSendHospital());
                pathologyDto.setSendDept(pathologyList.get(i).getSendDept());
                pathologyDto.setSpecimenName((pathologyList.get(i).getSpecimenName()));
                pathologyDto.setSex(pathologyList.get(i).getSex());
                pathologyDto.setEntity(pathologyList.get(i).getEntity());
                pathologyDtoList.add(pathologyDto);
            }
            System.out.println(pathologyDtoList);
            return JSON.toJSONString(pathologyDtoList);
        }
        else if (entity != null && !("".equals(entity))) {
            System.out.println("lalala");
            pathologyList = pathologyService.getAllInfoByEntity(entity);
            System.out.println(patname);
            List<PathologyDto> pathologyDtoList=new ArrayList<PathologyDto>();
            System.out.println("xhd");
            for(int i=0;i<pathologyList.size();i++){
                PathologyDto pathologyDto=new PathologyDto();
                pathologyDto.setAge(pathologyList.get(i).getAge());
                pathologyDto.setReportDate(sdf.format(pathologyList.get(i).getReportDate()));
                pathologyDto.setCheckDoc(pathologyList.get(i).getCheckDoc());
                pathologyDto.setClinicDiagnosis(pathologyList.get(i).getClinicDiagnosis());
                pathologyDto.setEyeView(pathologyList.get(i).getEyeView());
                pathologyDto.setIdcard(pathologyList.get(i).getIdcard());
                pathologyDto.setGlassView(pathologyList.get(i).getGlassView());
                pathologyDto.setPathologyDiagnosis(pathologyList.get(i).getPathologyDiagnosis());
                pathologyDto.setPatientid(pathologyList.get(i).getPatientid());
                pathologyDto.setPatname(pathologyList.get(i).getPatname());
                pathologyDto.setSendHospital(pathologyList.get(i).getSendHospital());
                pathologyDto.setSendDept(pathologyList.get(i).getSendDept());
                pathologyDto.setSpecimenName((pathologyList.get(i).getSpecimenName()));
                pathologyDto.setSex(pathologyList.get(i).getSex());
                pathologyDto.setEntity(pathologyList.get(i).getEntity());
                pathologyDtoList.add(pathologyDto);
            }
            System.out.println(pathologyDtoList);
            return JSON.toJSONString(pathologyDtoList);
        }
        else{
            return JSON.toJSONString(0);
        }
    }
}
