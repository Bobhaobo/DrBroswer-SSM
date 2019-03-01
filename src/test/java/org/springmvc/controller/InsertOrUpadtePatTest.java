package org.springmvc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.Patient;
import org.springmvc.service.PatientService;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class InsertOrUpadtePatTest {
    @Resource
    private PatientService patientService;

    /**
     *@Description: 测试完成
     *@Author: Shalldid
     *@Date:Created in 16:02 2018-04-24
     **/
    @Test
    public void test_1(){

    }
}
