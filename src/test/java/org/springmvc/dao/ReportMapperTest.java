package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class ReportMapperTest {

    @Autowired
    private ReportMapper reportMapper;

    @Test
    public void T(){
        Report r = reportMapper.selectByCheckNum("CR000001");
        System.out.println(r);
    }
}
