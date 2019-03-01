package org.springmvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springmvc.dto.ReservationTab;
import org.springmvc.pojo.OrderTable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-MyBatis.xml"})
public class OrderTableMapperTest {
    @Autowired
    private OrderTableMapper orderTableMapper;

    /**
     *@Description: 测试插入一条新预约记录成功
     *@Author: Shalldid
     *@Date:Created in 11:04 2018-04-26
     **/
    @Test
    public void Test(){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(uuid.length());
        OrderTable orderTable = new OrderTable();
        orderTable.setId(uuid);
        orderTable.setAdddate(new Date());
        orderTable.setArrangestatus("0");
        orderTable.setChecknum("1111");
        orderTable.setOrderdate(new Date());
        orderTable.setOrdersource("h0001");
        int i = orderTableMapper.insert(orderTable);
        System.out.println(i);
    }
    /**
     * @Description: 测试分页查询OrderTable成功
     * @Author: Shalldid
     * @Date: Created in 14:41 2018-05-02
     * @Return:
     **/
    @Test
    public void T(){
        List<OrderTable> l = orderTableMapper.getOrderTableByStatus("0",3,6);
        for (OrderTable o : l){
            System.out.println(o.getChecknum());
        }
    }
    /**
     * @Description:
     * @Author: Shalldid
     * @Date: Created in 15:31 2018-05-02
     * @Return:
     **/
    @Test
    public void T_1(){
        System.out.println(orderTableMapper.getOrderTableCountByStatus("0"));
    }
}
