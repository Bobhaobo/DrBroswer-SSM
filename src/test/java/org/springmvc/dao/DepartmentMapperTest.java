package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class DepartmentMapperTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     *@Description: 测试成功
     *@Author: Shalldid
     *@Date:Created in 16:17 2018-04-25
     **/
    @Test
    public void Test(){
        System.out.println(departmentMapper.getDeptNamebyDeptid("DP0004"));
    }
}
