package org.springmvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.Device;
import org.springmvc.pojo.RegisterInfo;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class RegInfoServiceTest {

    @Resource
    private RegInfoService regInfoService;

    /**
     *@Description: 测试插入数据，符合预期
     *@Author: Shalldid
     *@Date:Created in 15:50 2018-04-25
     **/
    @Test
    public void insertNewRegInfo_test(){
        String i = regInfoService.insertNewRegInfo("1","2","CT","5",
                "6","7","8","DP0002","410102199403230093");
        System.out.println(i);
    }
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 10:02 2018-05-03
     * @Return:
     **/
    @Test
    public void getDeviceInfo(){
        for(Device d : regInfoService.getExamItemDevice("001")){
            System.out.println(d);
        }
    }
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 16:50 2018-05-03
     * @Return:
     **/
    @Test
    public void getCheckDateTest() throws ParseException {
        String date = "2016-06-18 00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf.parse(date));
    }
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 16:51 2018-05-03
     * @Return:
     **/
    @Test
    public void T_1(){
        System.out.println(regInfoService.getDicomWorkListByPatId("CT000005"));
    }
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 17:40 2018-05-03
     * @Return:
     **/
    @Test
    public  void T_2(){
        System.out.println(regInfoService.countCheckListByFlag("已检查"));
    }
}
