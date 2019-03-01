package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.HisInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class HisInfoMapperTest {

    @Autowired
    private HisInfoMapper hisInfoMapper;
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 15:59 2018-05-08
     * @Return:
     **/
    @Test
    public void T() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d_temp = new Date();
        String d_temp_string = (simpleDateFormat.format(d_temp)).substring(0,10);
        String d_temp_start = d_temp_string + " 00:00:00";
        String d_temp_end = d_temp_string + " 23:59:59";
        Date datestart = simpleDateFormat.parse(d_temp_start);
        Date dateend = simpleDateFormat.parse(d_temp_end);
        System.out.println(hisInfoMapper.countReportToday(datestart, dateend,""));
    }

    @Test
    public void T_1() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d_temp = new Date();
        String d_temp_string = (simpleDateFormat.format(d_temp)).substring(0,10);
        String d_temp_start = d_temp_string + " 00:00:00";
        String d_temp_end = d_temp_string + " 23:59:59";
        Date datestart = simpleDateFormat.parse(d_temp_start);
        Date dateend = simpleDateFormat.parse(d_temp_end);
        List<HisInfo> l = hisInfoMapper.getTodayReportListByPagination(datestart,dateend, "",0,30);
        for (HisInfo h : l){
            System.out.println(l);
        }
    }

    @Test
    public void T_2(){
        HisInfo h = hisInfoMapper.selectByCheckNum("CT000002");
        System.out.println(h.getReportpath());
    }
}
