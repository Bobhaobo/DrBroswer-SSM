package org.springmvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.RemoteRegister;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class RemoteRegisterServiceTest {

    @Resource
    private RemoteRegisterService remoteRegisterService;

    @Test
    public void T(){
        RemoteRegister remoteRegister = new RemoteRegister();
        remoteRegister.setChecknum("RCR20180510001");
        remoteRegister.setRemotehos("H0004");
        remoteRegister.setRegdate(new Date());
        remoteRegister.setPattype("住院");
        remoteRegister.setTagpatientid("P00001");
        remoteRegister.setModality("CR");
        remoteRegister.setIdcard("410102199403230093");
        remoteRegister.setFlag("未上传图像");
        remoteRegister.setId(UUID.randomUUID().toString());
        try {
            remoteRegisterService.insertNewRegister(remoteRegister);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
