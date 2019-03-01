package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dto.UserAccount;
import org.springmvc.enums.LoginInfo;
import org.springmvc.enums.UserLevel;
import org.springmvc.pojo.AuthList;
import org.springmvc.pojo.User;
import org.springmvc.service.AuthService;
import org.springmvc.service.HospitalService;
import org.springmvc.service.UserService;
import org.springmvc.tool.AuthCodeImageUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);
    private ByteArrayInputStream byteArrayInputStream;      //初始化验证码图片输入流

    @Resource
    private UserService userService;

    @Resource
    private HospitalService hospitalService;

    @Resource
    private AuthService authService;

    /**
     *@Description: 通过登陆界面查询用户是否存在，并根据用户类型重定向不同的主界面
     *@Author: Shalldid
     *@Date: Created in 10:41 2018-04-18
     **/
    //     login/queryUser/D00001/123456/DF2H/login_auth
    @RequestMapping(value = "/queryUser/{username}/{password}/{authcode}/login_auth", method = RequestMethod.POST)
    @ResponseBody
    public String existUser (@PathVariable("username") String username, @PathVariable("password") String password,
                             @PathVariable("authcode") String authcode, Model model, HttpSession httpSession){
        LoginInfo loginInfo;
        String auth_code = (String)httpSession.getAttribute("auth_code");   //获取session内的authcode
        if(authcode != null && auth_code.toUpperCase().equals(authcode.toUpperCase())){
            try{
                User user = userService.ifExsitUser(username,password);
                httpSession.setAttribute("user", user);
                if(user != null){
                    UserLevel userLevel = userService.getUserLevel(user);
                    //System.out.println(userLevel.getLevel());
                    if(userLevel.getLevel() == 1){
                        loginInfo = LoginInfo.Major;
                    }else if(userLevel.getLevel() == 2){
                        loginInfo = LoginInfo.Junior;
                    }else if(userLevel.getLevel() == 3){
                        loginInfo = LoginInfo.Super;
                    }else {
                        loginInfo = LoginInfo.User_Error;
                    }
                }else{
                    loginInfo = LoginInfo.User_Error;
                }
            }catch (Exception e){
                e.printStackTrace();
                loginInfo = LoginInfo.Server_Error;
            }
        }else {
            loginInfo = LoginInfo.AuthCode_Error;
        }
//        System.out.println(loginInfo.getIndex() + "");
        return loginInfo.getIndex() + "";   //返回错误数字代码
    }

    /**
     *@Description: 返回登陆界面验证码图片，并将验证码存放入session内
     *@Author: Shalldid
     *@Date:Created in 10:42 2018-04-18
     **/
    @RequestMapping(value="/{time_now}/authcode")
    @ResponseBody
    public void getAuthCode(@PathVariable("time_now") String time_now, HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse, HttpSession httpSession) throws IOException {
        //System.out.println(time_now);
        AuthCodeImageUtil authCodeImageUtil = AuthCodeImageUtil.Instance();     //取得验证码产生类的对象
        this.byteArrayInputStream = authCodeImageUtil.getImage();   //getImage返回byteArrayInputStream对象
        httpSession.setAttribute("auth_code",authCodeImageUtil.getString());    //将生成的随机字符串放入session，以便以后验证时读取
        httpServletResponse.setContentType("image/JPEG");   //响应类型设置为jpeg格式图像

        OutputStream os = httpServletResponse.getOutputStream();

        byte[] auth_img = new byte[1024];  //定义一个byte数组，数组大小为1024
        try{
            while (this.byteArrayInputStream.read(auth_img) > 0){
                os.write(auth_img);    //向输出流输出数组
            }
        }catch (IOException e){
            logger.error("生成验证码图片失败：" + e.getMessage());
            e.printStackTrace();
        }finally {
            os.flush();     //强制刷新输出流
            os.close();     //关闭输出流
        }
    }
    /**
     * @Description: 加载用户主页面header部用户信息
     * @Author: Shalldid
     * @Date: Created in 11:25 2018-04-28
     * @Return:
     **/
    @RequestMapping(value = "/loadUserAccount",method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getLoginUserAccount(HttpSession httpSession){
        User u = (User) httpSession.getAttribute("user");
        UserAccount ua = new UserAccount();
        ua.setHos(hospitalService.getHosNameByHosId(userService.getHosIdOfUser(u.getDept())));
        ua.setId(u.getId());
        ua.setName(u.getName());
        AuthList authList = authService.getAuthByAuthCode(u.getMain_page());
        System.out.println(authList);
        ua.setMainPageUrl(authList.getUrl());
        ua.setMainPageTitle(authList.getAuthName());
        ua.setMainPageId(authList.getHtml_id());
        return JSON.toJSONString(ua);
    }
    /**
     * @Description: 用户登出
     * @Author: Shalldid
     * @Date: Created in 11:44 2018-04-28
     * @Return:
     **/
    @RequestMapping(value = "/logout",produces="text/html; charset=UTF-8")
    @ResponseBody
    public String logout(HttpSession httpSession){
        httpSession.invalidate();   //使session内所有对象清空
        return "1";
    }
}
