package org.springmvc.controller;


import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dto.Dcm2jpgIOStreamOutput;
import org.springmvc.dto.Pagination;
import org.springmvc.dto.PaginationResult;
import org.springmvc.pojo.Patient;
import org.springmvc.pojo.lung;
import org.springmvc.service.LungService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/lung")
@Controller
public class LungController {

    @Resource
    private LungService lungService;

    @RequestMapping(value = "/getLungInfo",produces="text/html;charset=utf-8")
    @ResponseBody
    public String getLungInfo(Pagination p, @RequestParam("numberll") String patid,
                              HttpSession httpSession)throws IOException
    {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow =6;
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;
        int totalPage = 10;
        List<lung> lungs =new ArrayList<lung>();
        lungs=lungService.getPatInfo(patid);
        PaginationResult<lung> paginationResult = new PaginationResult<lung>();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(lungs);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        System.out.println(lungs);
        //编译Dicom图片
        //返回的base64编码的字符串
        String base64Url = null;
        try{
            //File src = new File("E:\\1.dcm");
            File src = new File("C:\\Users\\li\\Desktop\\2.dcm");
//			File src = new File("E:\\I170.dcm");
            Dcm2jpgIOStreamOutput dcm2jpg = new Dcm2jpgIOStreamOutput();
            //,windowWidth,windowCenter
            //返回的base64编码的字符串
            base64Url = dcm2jpg.convert(src);
        }catch (Exception e) {
            e.printStackTrace();
        }
        paginationResult.setBase64Url(base64Url);


        System.out.println(JSON.toJSONString(paginationResult));

        return JSON.toJSONString(paginationResult);
    }
}
