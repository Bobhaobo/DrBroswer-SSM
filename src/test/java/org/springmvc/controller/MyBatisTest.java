package org.springmvc.controller;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.enums.UserLevel;
import org.springmvc.pojo.User;
import org.springmvc.service.UserService;

import javax.annotation.Resource;
import java.sql.Date;

/**
 *
 *
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class MyBatisTest {

    @Resource
    private UserService userService;

    @Test
    public void test_1(){

        //User b = userService.ifExsitUser("D00001","123456");

        UserLevel userLevel = userService.getUserLevel(new User());
        System.out.println(userLevel);
    }

    @Test
    public void T_2(){
        System.out.println(new java.util.Date());
    }

}
