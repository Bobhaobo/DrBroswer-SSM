package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.tool.SeriesNumGenerator;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class DicomWorkListMapperTest {

    @Autowired
    private DicomWorkListMapper dicomWorkListMapper;

    @Resource
    private SeriesNumGenerator accessionNGenerator;

    /**
     *@Description: 测试完成，有结果时返回返回预期结果，无结果时返回null
     *@Author: Shalldid
     *@Date:Created in 10:27 2018-04-25
     **/
    @Test
    public void getAccessioN(){
        String accessionN = dicomWorkListMapper.getAccessionN("CT","CT20180425___");
        System.out.println(accessionN);
    }

    /**
     *@Description: 测试完成，有结果时返回结果+1，无结果时返回001，注意此处用法同service相同，不再使用new形式
     *@Author: Shalldid
     *@Date:Created in 10:52 2018-04-25
     **/
    @Test
    public void getGeneratorAccesionN(){
        System.out.println(accessionNGenerator.getAccessionN("MR"));
    }
    /**
     *@Description: 测试完成,均符合预期效果
     *@Author: Shalldid
     *@Date:Created in 11:13 2018-04-25
     **/
    @Test
    public void getCheckNum(){
        System.out.println(accessionNGenerator.getCheckNum("CT"));
        System.out.println(accessionNGenerator.getPatientID("CT"));
        System.out.println(accessionNGenerator.getRecordID("CT"));
        System.out.println(accessionNGenerator.getReportCode());
    }
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 16:52 2018-05-03
     * @Return:
     **/
    @Test
    public void getDicom(){
        System.out.println(dicomWorkListMapper.selectByPatid("CT000005"));
    }
}
