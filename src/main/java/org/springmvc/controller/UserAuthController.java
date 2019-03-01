package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.pojo.AuthList;
import org.springmvc.pojo.User;
import org.springmvc.service.AuthService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("/userAuth")
public class UserAuthController {

    @Resource
    private AuthService authService;

    @RequestMapping(value = "/getUserAuth")
    @ResponseBody
    public String getUserAuth(HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");
        String auth = user.getAuth();
        String[] auths = auth.split(",");
        Map<String, List<AuthList>> authMap = new HashMap<String, List<AuthList>>();

        for (String s : auths) {
            //System.out.println(s);
            AuthList a = authService.getAuthByAuthCode(s);

            if (authMap.containsKey(a.getPre_menu())) {
                authMap.get(a.getPre_menu()).add(a);
            } else {
                List<AuthList> b = new ArrayList<AuthList>();
                b.add(a);
                authMap.put(a.getPre_menu(), b);
            }
        }
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        String string=new String();
        String string1=new String();
        StringBuilder stringBuilder=new StringBuilder();
        for (Map.Entry<String, List<AuthList>> entry : authMap.entrySet()) {
            JSONObject ja_1 = new JSONObject(true);
            JSONArray ja_1_children = new JSONArray();
            for (AuthList authList : entry.getValue()) {
                JSONObject js_1_1 = new JSONObject();
                js_1_1.put("id", authList.getHtml_id());
                js_1_1.put("name", authList.getAuthName());
                js_1_1.put("target", "navtab");
                js_1_1.put("url", authList.getUrl());
                ja_1_children.add(js_1_1);
            }
            ja_1.put("children", ja_1_children);
            ja_1.put("name", entry.getKey());
//            jsonArray.add(ja_1);
//        }
//        System.out.println(jsonArray);
            if (ja_1.getString("name").equals("系统设置")) {
                jsonArray.add(ja_1);
            } else {
                jsonArray1.add(ja_1);
            }
            String s=jsonArray.toString();
//            System.out.println("@@@@@@@@@@@@@");
//            System.out.println(s);
            string=s;
//            System.out.println(string);
            String s1=jsonArray1.toString();
            string1=s1;
//            System.out.println(string1);
        }
        string1=string1.substring(1,string1.length());
//        System.out.println(string1);
        string=string.substring(0,string.length()-1)+",";
//        System.out.println(string);
        stringBuilder.append(string+string1);
        String ss=stringBuilder.toString();
//        System.out.println(ss);
            jsonArray.clear();
            jsonArray=JSONArray.parseArray(ss);
//        System.out.println(jsonArray);
        return JSON.toJSONString(jsonArray);
    }

    @RequestMapping(value = "/getUserAuthCode")
    @ResponseBody
    public String getUserAuthCode(HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");
        String auth = user.getAuth();
        String[] auths = auth.split(",");

        return JSON.toJSONString(auths);

    }
}
