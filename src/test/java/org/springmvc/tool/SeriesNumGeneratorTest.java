package org.springmvc.tool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class SeriesNumGeneratorTest {

    @Resource
    private SeriesNumGenerator seriesNumGenerator;
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 10:22 2018-05-10
     * @Return:
     **/
    @Test
    public void T(){
        System.out.println(seriesNumGenerator.getRemoteChecknum("CT"));
    }
}
