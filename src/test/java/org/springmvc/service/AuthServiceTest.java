package org.springmvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.AuthList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class AuthServiceTest {

    @Autowired
   private AuthService authService;

    @Test
    public void getAuthByAuthCode() {
        AuthList authList = authService.getAuthByAuthCode("1");
        System.out.println(authList);
    }
}