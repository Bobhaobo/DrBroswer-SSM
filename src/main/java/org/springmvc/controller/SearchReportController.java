package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dto.Pagination;
import org.springmvc.dto.PaginationResult;
import org.springmvc.dto.TodayReportTab;
import org.springmvc.dto.WrittedTab;
import org.springmvc.pojo.HisInfo;
import org.springmvc.pojo.User;
import org.springmvc.service.HisInfoService;
import org.springmvc.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

/**
 * @Description: 报告检索
 * @Author: Shalldid
 * @Date: Created in 15:18 2018-05-08
 * @Return:
 **/
@Controller
@RequestMapping("/search")
public class SearchReportController {
    private static final Logger logger = Logger.getLogger(SearchReportController.class);

    @Resource
    private HisInfoService hisInfoService;

    @Resource
    private UserService userService;

    /**
     * @Description: 加载当日的报告列表
     * @Author: Shalldid
     * @Date: Created in 14:56 2018-05-09
     * @Return:
     **/
    @RequestMapping(value = "/loadTodayReport")
    @ResponseBody
    public String loadTodayReport(Pagination p, HttpSession httpSession){
        User u = (User)httpSession.getAttribute("user");
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        int totalRow = hisInfoService.countReportToday(userService.getHosIdOfUser(u.getDept())); //得到今日出报告的总数量；
        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        try {
            List<TodayReportTab> todayReportTabs = hisInfoService.getTodayReportList(currIndex, pageSize,userService.getHosIdOfUser(u.getDept()));
            PaginationResult<TodayReportTab> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(todayReportTabs);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            //System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @Description: 输出报告图像
     * @Author: Shalldid
     * @Date: Created in 14:56 2018-05-09
     * @Return:
     **/
    @RequestMapping(value = "/{checknum}/{randomNum}/loadReportImage")
    public void loadReportImage(@PathVariable("checknum") String checknum, @PathVariable("randomNum") String randomNum, HttpServletResponse httpServletResponse){
        httpServletResponse.setContentType("image/*");
        try{
            //System.out.println(checknum);
            HisInfo h = hisInfoService.getHisInfoByCheckNum(checknum);
            //System.out.println(h);
            OutputStream o = httpServletResponse.getOutputStream();     //bug
            File f = new File(h.getReportpath());
            FileInputStream fileInputStream = new FileInputStream(f);
            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            o.write(b);
            o.flush();
            fileInputStream.close();
            o.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * @Description: 条件查询
     * @Author: Shalldid
     * @Date: Created in 16:07 2018-05-09
     * @Return:
     **/
    @RequestMapping(value = "/getReportListByCondition")
    @ResponseBody
    public String getReportListByCondition(@RequestParam("modality") String modality, @RequestParam("pat_type") String pat_type,
                                           @RequestParam("dateBegin") String dateBegin, @RequestParam("dateEnd") String dateEnd,
                                           @RequestParam("name") String name, @RequestParam("ID") String id,
                                           @RequestParam("number") String number, @RequestParam("clinic_id") String clinic_id,
                                           Pagination p) throws ParseException {
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        List<TodayReportTab> conditionReportTabs = hisInfoService.getTodaReportListByCondition(currIndex,pageSize,modality,pat_type,dateBegin,dateEnd,name,id,number,clinic_id);
        int totalRow = conditionReportTabs.size();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        PaginationResult<TodayReportTab> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(conditionReportTabs);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        //System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }
}
