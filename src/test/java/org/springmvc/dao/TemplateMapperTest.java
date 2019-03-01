package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.Template;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class TemplateMapperTest {

    @Autowired
    private TemplateMapper templateMapper;
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 14:51 2018-05-04
     * @Return:
     **/
    @Test
    public void T_1(){
        List<Template> l_1 = templateMapper.loadLevelItem(3);
        for (Template t : l_1){
            System.out.println("l_1>>>" + t);
        }
        List<Template> l_2 = templateMapper.findByParentId("b8a2b83f-f955-4cef-b0e7-9c36025cad7b");
        for (Template t : l_2){
            System.out.println("l_2>>>" + t);
        }
//        Template l = templateMapper.selectByPrimaryKey("194222f1-edf0-4a16-a5b4-dcb62708eafb");
//        System.out.println(l);
    }
}
