package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.service.TemplateService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/template")
public class TemplateLoadController {

    @Resource
    private TemplateService templateService;
    /**
     * @Description: 写报告时首先先加载模板的全部简介信息
     * @Author: Shalldid
     * @Date: Created in 15:35 2018-05-04
     * @Return:
     **/
    @RequestMapping(value = "/loadTemplate", method = RequestMethod.GET)
    @ResponseBody
    public String loadTemplate(){
        System.out.println(templateService.getTemplateIntro());
        return templateService.getTemplateIntro();
    }
    /**
     * @Description: 请求得到的template_id，通过这个id加载模板的详细信息
     * @Author: Shalldid
     * @Date: Created in 15:35 2018-05-04
     * @Return:
     **/
    @RequestMapping(value = "/{template_id}/getTemplateDetail", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String loadTemplateDetail(@PathVariable("template_id") String template_id){
        System.out.println(template_id);
        SimplePropertyPreFilter s = new SimplePropertyPreFilter("examdesc", "examdiagnosis");
        System.out.println(JSON.toJSONString(templateService.getTemplateDetailById(template_id),s));
        return JSON.toJSONString(templateService.getTemplateDetailById(template_id),s);
    }

}
