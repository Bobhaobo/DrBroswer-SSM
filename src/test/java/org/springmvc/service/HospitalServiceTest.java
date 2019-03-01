package org.springmvc.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.dto.Hos4Reg;
import org.springmvc.pojo.Department;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class HospitalServiceTest {

    @Resource
    private HospitalService hospitalService;

    @Test
    public void T(){
        List<String> major_hos_list = hospitalService.getApplyHospitalIdByHosid("H0002");
        System.out.println(major_hos_list.size());
        List<Hos4Reg> l = new ArrayList<Hos4Reg>();
        Hos4Reg h4r = new Hos4Reg();
        for(String s : major_hos_list){
            System.out.println(s);
            h4r.setId(s);
            h4r.setName(hospitalService.getHosNameByHosId(s));
            l.add(h4r);
        }
        System.out.println(JSON.toJSONString(l));
    }

    @Test
    public void T_1(){
        List<Department> departments = hospitalService.getDeptListByHosid("H0001");
        for (Department department : departments){
            System.out.println(department.getDeptname());
        }
    }
}
