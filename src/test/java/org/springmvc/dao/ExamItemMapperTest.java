package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.ExamItem;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class ExamItemMapperTest {

    @Autowired
    private ExamItemMapper examItemMapper;
/**
 *@Description: 测试符合预期
 *@Author: Shalldid
 *@Date:Created in 16:03 2018-04-25
 **/
    @Test
    public void Test(){
        System.out.println(examItemMapper.getExamCodeByName("CT"));
        System.out.println(examItemMapper.getExamNameByCode("003"));
    }

    @Test
    public void T(){
        List<ExamItem> l = examItemMapper.getCheckItemForHos();
        for (ExamItem e: l) {
            System.out.println(e);
        }
    }
}
