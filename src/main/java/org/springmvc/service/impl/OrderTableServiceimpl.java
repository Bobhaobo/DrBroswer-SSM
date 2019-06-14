package org.springmvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dao.HospitalMapper;
import org.springmvc.dao.OrderTableMapper;
import org.springmvc.dao.PatientMapper;
import org.springmvc.dao.RegisterInfoMapper;
import org.springmvc.dto.ArrangedTab;
import org.springmvc.dto.Pagination;
import org.springmvc.dto.ReservationTab;
import org.springmvc.pojo.OrderTable;
import org.springmvc.pojo.Patient;
import org.springmvc.pojo.RegisterInfo;
import org.springmvc.service.OrderTableService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 病人预约表相关实现类
 * @Author: Shalldid
 * @Date: Created in 11:21 2018-04-26
 * @Return:
 **/
@Service
public class OrderTableServiceimpl implements OrderTableService {

    private static final Logger logger = Logger.getLogger(PatientServiceimpl.class);

    @Autowired
    private OrderTableMapper orderTableMapper;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private RegisterInfoMapper registerInfoMapper;

    @Autowired
    private PatientMapper patientMapper;

    /**
     * @Description: 新插入预约表
     * @Author: Shalldid
     * @Date: Created in 11:23 2018-04-26
     * @Param: 检查号，预约日期，预约医院id
     * @Return: 返回插入状态码
     **/
    @Override
    public int insertNewOrderTable(String check_num, String date, String orderSource){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderTable o = new OrderTable();
        o.setId(UUID.randomUUID().toString());
        if(orderSource != null){
            o.setOrdersource(orderSource);
        }else {
            o.setOrdersource("HHHHH");
        }
        try {
            o.setOrderdate(sdf.parse(date));
        } catch (ParseException e) {
            logger.error("预约日期转换失败： " + date + "  预约检查号： " + check_num + " 尝试预约时间： " + new Date().toString());
            e.printStackTrace();
        }
        o.setAdddate( new Date());
        o.setArrangestatus("0");    //0：未安排，1：已安排
        o.setChecknum(check_num);
        return orderTableMapper.insert(o);
    }
    /**
     * @Description: 跟据Pagination返回查询的预约数据
     * @Author: Shalldid
     * @Date: Created in 14:55 2018-05-02
     * @Return:
     **/
    public List<ReservationTab> getOrderTabByStatusAndPagination(int currIndex, int pageSize){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<OrderTable> orderTables = orderTableMapper.getOrderTableByStatus("0", pageSize, currIndex);  //查询,未安排的状态是0
        List<ReservationTab> list = new ArrayList<ReservationTab>();
        Patient patient;
        for(OrderTable o : orderTables){
            ReservationTab reservationTab = new ReservationTab();
            reservationTab.setCheck_number(o.getChecknum());
            reservationTab.setOrder_Date(sdf.format(o.getOrderdate()));
            reservationTab.setOrderSource(hospitalMapper.getHosNameByHosId(o.getOrdersource()));    //通过预约源医院查询医院的名字
            patient = patientMapper.selectByPrimaryKey(registerInfoMapper.selectByPrimaryKey(o.getChecknum()).getIdcard()); //通过检查号查询出病人的基本信息
            reservationTab.setPatient_Age(patient.getAge() + patient.getAgetype());
            reservationTab.setPatient_Name(patient.getPatname());
            reservationTab.setPatient_Sex(patient.getPatgender());
            reservationTab.setRegister_Date(sdf.format(o.getAdddate()));
            list.add(reservationTab);
        }
        return list;
    }
    /**
     * @Description:  返回在符合arrangeStatus状态的数据数目
     * @Author: Shalldid
     * @Date: Created in 15:52 2018-05-02
     * @Return:
     **/
    @Override
    public int getOrderTableCountByStatus(String arrangeStatus){
        return orderTableMapper.getOrderTableCountByStatus(arrangeStatus);
    }
    /**
     * @Description: 根据检查号返回预约表
     * @Author: Shalldid
     * @Date: Created in 17:37 2018-05-02
     * @Return:
     **/
    @Override
    public OrderTable getOrderTabByCheckNum(String checkNum){
        return orderTableMapper.getOrderTableByCheckNum(checkNum);
    }
    /**
     * @Description: 根据检查号更新预约表内status
     * @Author: Shalldid
     * @Date: Created in 10:56 2018-05-03
     * @Return:
     **/
    @Override
    public int changeOrderStatusAndArrangDate(String checkNum, String status, Date date){
        return orderTableMapper.updateOrderStatus(checkNum, status,date);
    }
    /**
     * @Description: 跟据Pagination返回查询的已经安排过检查设备的数据
     * @Author: Shalldid
     * @Date: Created in 15:02 2018-05-03
     * @Return:
     **/
    @Override
    public List<ArrangedTab> getArrangedTabByStatusAndPagination(int currIndex, int pageSize, String hosid){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<OrderTable> orderTables = orderTableMapper.getOrderTableByStatusAndHosid("1", pageSize, currIndex, hosid);  //查询
        List<ArrangedTab> list = new ArrayList<ArrangedTab>();
        Patient p;
        RegisterInfo r;
        for(OrderTable o : orderTables){
            ArrangedTab a = new ArrangedTab();
            a.setArranged_Date(sdf.format(o.getOrderdate()));
            a.setCheck_number(o.getChecknum());
            a.setCheckHos(hospitalMapper.getHosNameByHosId("H0001"));   //以下两处需做修改，目前只有一家上级医院，写死了
            a.setCheckHosId("H0001");
            r = registerInfoMapper.selectByPrimaryKey(o.getChecknum());
//            System.out.println(r);
            a.setModality(r.getExamitemname());
            a.setRecordId(r.getRecordid());
            p = patientMapper.selectByPrimaryKey(r.getIdcard());
//            System.out.println(o);
//            System.out.println(a);
            a.setPatient_Age(p.getAge() + p.getAgetype());
//            System.out.println(a.getPatient_Age());
//            System.out.println("bbb");
            a.setPatient_Name(p.getPatname());
            a.setPatient_Sex(p.getPatgender());
            list.add(a);
            System.out.println(a);
        }
        return list;
    }
}
