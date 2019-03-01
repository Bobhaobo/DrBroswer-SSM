package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class RegisterInfoMapperTest {

    @Autowired
    private RegisterInfoMapper registerInfoMapper;

    @Test
    public void T(){
        List<String> list = registerInfoMapper.selectByFlagReturnChecknum("未检查");
        for (String s : list){
            System.out.println(s);
        }
    }
}
