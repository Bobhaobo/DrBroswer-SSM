package org.springmvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.dto.ReservationTab;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class OrderTableServiceTest {

    @Resource
    private OrderTableService orderTableService;

    @Test
    public void l(){
        orderTableService.insertNewOrderTable("CT00001","2018-04-28", "H0001");
    }
    /**
     * @Description: 测试成功
     * @Author: Shalldid
     * @Date: Created in 15:39 2018-05-02
     * @Return:
     **/
    @Test
    public void T_1(){
        List<ReservationTab> l = orderTableService.getOrderTabByStatusAndPagination(3, 3);
        for(ReservationTab r : l){
            System.out.println(r.toString());
        }
    }
    /**
     * @Description: 测试成功，更新成功返回1，更新失败返回0
     * @Author: Shalldid
     * @Date: Created in 10:58 2018-05-03
     * @Return:
     **/
    @Test
    public void T(){
        System.out.println(orderTableService.changeOrderStatusAndArrangDate("CT000006","0",new Date()));
    }
}
