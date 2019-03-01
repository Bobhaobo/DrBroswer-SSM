package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dto.*;
import org.springmvc.pojo.HisInfo;
import org.springmvc.service.AuthService;
import org.springmvc.service.HospitalService;
import org.springmvc.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/userRegister")
public class UserRegisterController {

    @Resource
    private UserService userService;

    @Resource
    private HospitalService hospitalService;
    @Resource
    private AuthService authService;

    @RequestMapping(value = "/loaduserlist")
    @ResponseBody
    public String loadUserList(Pagination p){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        List<UserListTab> userListTabs = userService.getUserList();
        int totalRow = userListTabs.size();
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        PaginationResult<UserListTab> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(userListTabs);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        //System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }

    @RequestMapping(value = "/loadUserRegHos", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String loadUserRegHos() {
        try {
            List<String> hosidlist = hospitalService.getAllHosId();
            List<SelectPicker> hosInfos = new ArrayList<SelectPicker>();
            for (String s : hosidlist) {
                SelectPicker h = new SelectPicker();
                h.setLabel(hospitalService.getHosNameByHosId(s));
                h.setValue(s);
                hosInfos.add(h);
            }
            return JSON.toJSONString(hosInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/loadUserRegAuth", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String loadUserRegAuth() {
        try {
            List<String> authlist = authService.getAuthCode();
            List<SelectPicker> authInfos = new ArrayList<SelectPicker>();
            for (String s : authlist) {
                SelectPicker h = new SelectPicker();
                h.setLabel(authService.getAuthNameByCode(s));
                h.setValue(s);
                authInfos.add(h);
            }
            return JSON.toJSONString(authInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
