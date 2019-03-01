package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class HosMapperTest {

    @Autowired
    private HospitalMapper hospitalMapper;
/**
 * @Description: 测试成功
 * @Author: Shalldid
 * @Date: Created in 17:33 2018-04-27
 * @Return:
 **/
    @Test
    public void T(){
        System.out.println(hospitalMapper.getHosNameByHosId("H0001"));
    }

}
