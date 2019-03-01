package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.RemoteReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class RemoteRegisterMapperTest {

    @Autowired
    private RemoteRegisterMapper remoteRegisterMapper;

    @Autowired
    private RemoteReportMapper remoteReportMapper;
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 15:16 2018-05-10
     * @Return:
     **/
    @Test
    public void T(){
        System.out.println(remoteRegisterMapper.getRemoteRegisterPageByFlag("未上传图像",0,30).size());
    }
    @Test
    public void T_1(){
        RemoteReport r = remoteReportMapper.selectBycheckNum("RCT20180510001");
        System.out.println(r.getReporttime());
    }
}
