package org.springmvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class TemplateServiceTest {

    @Resource
    private TemplateService templateService;

    @Test
    public void T_1(){
        System.out.println(templateService.getTemplateIntro());
    }
}
