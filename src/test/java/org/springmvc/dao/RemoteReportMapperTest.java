package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.pojo.RemoteReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class RemoteReportMapperTest {

    @Autowired
    private RemoteReportMapper remoteReportMapper;
    /**
     * @Description: 测试完成
     * @Author: Shalldid
     * @Date: Created in 17:07 2018-05-14
     * @Return:
     **/
    @Test
    public void T() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d_temp = new Date();
        String d_temp_string = (sdf.format(d_temp)).substring(0,10);
        Date datestart = sdf.parse(d_temp_string + " 00:00:00");
        Date dateend = sdf.parse(d_temp_string + " 23:59:59");
        List<RemoteReport> remoteReports = remoteReportMapper.getTodayReportListByPagination(datestart, dateend,0,30,"H0002");
        for (RemoteReport r : remoteReports){
            System.out.println(r);
        }
    }
}
