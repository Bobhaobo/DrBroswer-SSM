package org.springmvc.tool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class ImagePathGeneratorTest {

    @Resource
    private ImageAndReportPathGenerator imagePathGenerator;
    /**
     * @Description: 测试通过Spring注入属性成功,不需要setter也能注入，原理待查
     * @Author: Shalldid
     * @Date: Created in 9:42 2018-05-04
     * @Return:
     **/
    @Test
    public void T(){
//        System.out.println(imagePathGenerator.getInnerImagePath());
//        System.out.println(imagePathGenerator.getInnerImageUseSMB());
//        System.out.println(imagePathGenerator.getInnerReportPath());
//        System.out.println(imagePathGenerator.getInnerReportUseSMB());
//        System.out.println(imagePathGenerator.getRemoteImagePath());
//        System.out.println(imagePathGenerator.getRemoteImageUseSMB());
//        System.out.println(imagePathGenerator.getRemoteReportUseSMB());
//        System.out.println(imagePathGenerator.getRemoteReportPath());
    }
}
