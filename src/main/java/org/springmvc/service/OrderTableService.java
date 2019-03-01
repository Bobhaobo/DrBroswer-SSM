package org.springmvc.service;

import org.springmvc.dto.ArrangedTab;
import org.springmvc.dto.Pagination;
import org.springmvc.dto.ReservationTab;
import org.springmvc.pojo.OrderTable;

import java.util.Date;
import java.util.List;

public interface OrderTableService {

    int insertNewOrderTable(String check_num, String date, String orderSource);

    List<ReservationTab> getOrderTabByStatusAndPagination(int currIndex, int pageSize);   //跟据Pagination返回查询的预约数据

    int getOrderTableCountByStatus(String arrangeStatus);   //查询符合arrangeStatus状态的数据总数;

    OrderTable getOrderTabByCheckNum(String checkNum);  //通过检查号得到预约表

    int changeOrderStatusAndArrangDate(String checkNum, String status, Date date);  //通过检查号更改预约状态

    List<ArrangedTab> getArrangedTabByStatusAndPagination(int currIndex, int pageSize, String hosid); //跟据Pagination返回查询的已经安排过检查设备的数据
}
