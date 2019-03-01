package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class RelationMapperTest {

    @Autowired
    private RelationMapper relationMapper;
/**
 * @Description: 测试成功
 * @Author: Shalldid
 * @Date: Created in 17:15 2018-04-27
 * @Return:
 **/
    @Test
    public void Test(){
        List<String> l = relationMapper.getMajorHosidByJuniorHosid("H0002");
        for (String s : l){
            System.out.println(s);
        }
    }
}
