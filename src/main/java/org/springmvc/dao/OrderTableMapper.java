package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.OrderTable;

import java.util.Date;
import java.util.List;

public interface OrderTableMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderTable record);

    int insertSelective(OrderTable record);

    OrderTable selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderTable record);

    int updateByPrimaryKey(OrderTable record);

    List<OrderTable> getOrderTableByStatus(@Param("arrangestatus") String arrangeStatus, @Param("pageSize") int pageSize, @Param("currIndex") int formIndex);    //根据arrangeStatus及分页情况加载所有预约表

    int getOrderTableCountByStatus(String arrangestatus);   //查询符合arrangeStatus状态的数据总数；

    OrderTable getOrderTableByCheckNum(String checkNum);    //根据检查号返回预约表

    int updateOrderStatus(@Param("checknum") String checknum, @Param("status") String status, @Param("date")Date date);  //根据检查号更新表内字段

    List<OrderTable> getOrderTableByStatusAndHosid(@Param("arrangestatus") String arrangeStatus, @Param("pageSize") int pageSize, @Param("currIndex") int formIndex, @Param("hosid") String hosid);    //根据arrangeStatus及分页情况加载所有预约表
}