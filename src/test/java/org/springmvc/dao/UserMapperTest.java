package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.User;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void T(){
        User u = userMapper.queryByUserId("D00001");
        System.out.println(u.getName());
    }

    @Test
    public void T_1(){
        List<User> list = userMapper.selectAllUser();
        for(User u : list){
            System.out.println(u.getName());
        }
    }

    @Test
    public void Name(){
        User u = userMapper.queryByUserNameAndPwd("D00001","123456");
        System.out.println(u);

    }
}
