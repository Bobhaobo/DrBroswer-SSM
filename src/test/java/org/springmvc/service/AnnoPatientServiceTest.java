package org.springmvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.dao.AnnoPatientMapper;
import org.springmvc.pojo.AnnoPatient;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class AnnoPatientServiceTest {
    @Autowired
    private AnnoPatientMapper annoPatientMapper;
    @Test
    public void insertOrUpdatePatient() {
        Date d=new Date();
        AnnoPatient annoPatient = new AnnoPatient("1789","name",d,"nv");
//        annoPatient.setPatientId("1789");
//        annoPatient.setPatientBirth(new Date());
//        annoPatient.setPatientName("name");
//        annoPatient.setPatientSex("nv");
        System.out.println(annoPatient);
        annoPatientMapper.insert(annoPatient);

    }
}