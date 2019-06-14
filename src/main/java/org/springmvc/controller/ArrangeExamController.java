package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.log4j.Logger;
import org.dcm4che3.util.UIDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmvc.dto.*;
import org.springmvc.pojo.*;
import org.springmvc.pojo_inner.DicomWorkListInner;
import org.springmvc.pojo_inner.RegisterInfoInner;
import org.springmvc.service.*;
import org.springmvc.tool.SeriesNumGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Description: 加载预约表，安排检查
 * @Author: Shalldid
 * @Date: Created in 11:58 2018-04-28
 * @Return:
 **/
@Controller
@RequestMapping("/arrange")
public class ArrangeExamController {

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Resource
    private OrderTableService orderTableService;

    @Resource
    private RegInfoService regInfoService;

    @Resource
    private PatientService patientService;

    @Resource
    private HospitalService hospitalService;

    @Resource
    private SeriesNumGenerator seriesNumGenerator;

    @Resource
    private UserService userService;

    @Resource
    private DicomWorkListInnerService dicomWorkListInnerService;

    @Resource
    private RegisterInfoInnerService registerInfoInnerService;

    @RequestMapping(value = "/loadReservation")
    @ResponseBody
    public String String(Pagination p){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        int totalRow = orderTableService.getOrderTableCountByStatus("0"); //得到未安排预约的总数量，状态0是未安排；
        System.out.println("0");
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        System.out.println(pageSize);
        System.out.println(totalRow);
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1); //总页数，先判断总页数%每页数量是否等于0，若为0返回totalRow / pageSize，若不为0返回(totalRow / pageSize) + 1
        System.out.println(totalPage);
        List<ReservationTab> reservationTabs = orderTableService.getOrderTabByStatusAndPagination(currIndex, pageSize);
        System.out.println(reservationTabs);
        PaginationResult<ReservationTab> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(reservationTabs);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        //System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }
    /**
     * @Description: 根据检查号返回要安排的检查详细信息
     * @Author: Shalldid
     * @Date: Created in 17:35 2018-05-02
     * @Return:
     **/
    @RequestMapping(value = "/{checkNum}/getArrangeDetail", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getArrangeDetail(@PathVariable("checkNum") String checkNum){
        //System.out.println("checkNum: " + checkNum);
        OrderTable o = orderTableService.getOrderTabByCheckNum(checkNum);
        RegisterInfo r = regInfoService.getRegInfoByCheckNum(checkNum);
        Patient p = patientService.getPatByIdcard(r.getIdcard());
        String hosName = hospitalService.getHosNameByHosId(o.getOrdersource());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ArrangeDetail ad = new ArrangeDetail(checkNum, r.getRecordid(), r.getPatientid(), sdf.format(r.getRegtime()), p.getPatname(),
                p.getPatgender(), p.getAge() + p.getAgetype(), r.getExamitemname(), r.getExamitemcode(), sdf.format(o.getOrderdate()),
                hosName);
        return JSON.toJSONString(ad);
    }
    /**
     * @Description: 根据examItemCode从数据库device_info查询可用检查设备
     * @Author: Shalldid
     * @Date: Created in 9:34 2018-05-03
     * @Return:
     **/
    @RequestMapping(value = "/{examItemCode}/getCheckDeviceList", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getCheckDeviceList(@PathVariable("examItemCode") String examItemCode){
        List<Device> l = regInfoService.getExamItemDevice(examItemCode);    //查询的设备列表
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter("devdesc");    //JSON属性预过滤器，参数为需要用的属性
        //System.out.println(JSON.toJSONString(l, filter));
        return JSON.toJSONString(l, filter);
    }
    /**
     * @Description: 提交安排结果到worklist
     * @Author: Shalldid
     * @Date: Created in 10:19 2018-05-03
     * @Return:
     **/
    @RequestMapping(value = "/{checkNum}/{arrangeDate}/{subModality}/{checkDevItem}/submitWorkList", method = RequestMethod.POST)
    @ResponseBody
    public String submitWorkList(@PathVariable("checkNum") String checkNum, @PathVariable("arrangeDate") String arrangeDate,
                                 @PathVariable("subModality") String subModality, @PathVariable("checkDevItem") String checkDevItem) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        RegisterInfo r = regInfoService.getRegInfoByCheckNum(checkNum);
        Patient p = patientService.getPatByIdcard(r.getIdcard());
        String accession = seriesNumGenerator.getAccessionN(r.getExamitemname());
        DicomWorkList d = new DicomWorkList();
        d.setStudyinsta(UIDUtils.createUID());
        d.setAccessionn(accession);
        d.setPatientid(r.getPatientid());
        d.setPatientnam(p.getPatnamepy());
        d.setPatientbir((sdf.format(p.getPatbrithdate())).substring(0,8));
        d.setPatientsex("男".equals(p.getPatgender()) ? "M" : "F");
        d.setReqphysici("");
        d.setReqprocdes(r.getJcbw());
        d.setModality(r.getExamitemname());
        d.setScheduleda(checkDevItem);
        d.setStartdate(arrangeDate.substring(0, 4)+arrangeDate.substring(5,7)+arrangeDate.substring(8, 10));
        d.setStarttime("000001");
        d.setSchedpsid(accession);
        d.setSchedstati("SJRMYY");
        d.setReqprocid(accession);
        d.setStudyid(r.getPatientid());
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = arrangeDate.substring(0, 4)+ "-" +arrangeDate.substring(5,7)+ "-" +arrangeDate.substring(8, 10) + " 00:00";

        RegisterInfoInner registerInfoInner = new RegisterInfoInner();
        registerInfoInner.setXuhao(r.getChecknum());
        registerInfoInner.setAddress(p.getAddress());
        registerInfoInner.setAge(p.getAge());
        registerInfoInner.setAgetype(p.getAgetype());
        registerInfoInner.setBedno(r.getBedno());
        registerInfoInner.setCardno(r.getCardno());
        registerInfoInner.setChecknum(r.getChecknum());
        registerInfoInner.setClinicid(r.getClinicid());
        registerInfoInner.setDeptcode(r.getDeptid());
        registerInfoInner.setDeptname(r.getDeptname());
        registerInfoInner.setExamitemcode(r.getExamitemcode());
        registerInfoInner.setExamitemname(r.getExamitemname());
        registerInfoInner.setIfusingflag(false);
        registerInfoInner.setJcbw(r.getJcbw());
        registerInfoInner.setSjmd(r.getSjmd());
        registerInfoInner.setSqdbh(r.getSqdbh());
        registerInfoInner.setIdentityid(r.getIdcard());
        registerInfoInner.setYibaoid(p.getYibaoid());
        registerInfoInner.setTelephone(p.getTelephone());
        registerInfoInner.setRegistertime(r.getRegtime());
        registerInfoInner.setFlag("未检查");
        registerInfoInner.setPatientid(r.getPatientid());
        registerInfoInner.setRecordid(r.getRecordid());
        registerInfoInner.setPatname(p.getPatname());
        registerInfoInner.setPatnamepy(p.getPatnamepy());
        registerInfoInner.setPatbirthdate(p.getPatbrithdate());
        registerInfoInner.setPatgender(p.getPatgender());
        try{

            int dicom_worklist = regInfoService.insertDicomWorkList(d);
            int order_tab = orderTableService.changeOrderStatusAndArrangDate(checkNum, "1",sdf.parse(date));
            //System.out.println("**********************");
            int dicom_worklist_inner = dicomWorkListInnerService.saveNewDicomWorkList(new DicomWorkListInner(d));
            int register_info_inner = registerInfoInnerService.saveNewRegisterInfo(registerInfoInner);
            return (dicom_worklist == 1 && order_tab == 1 && dicom_worklist_inner == 1&& register_info_inner == 1) ? "1" : "0";

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @Description: 已经安排过的列表
     * @Author: Shalldid
     * @Date: Created in 14:49 2018-05-03
     * @Return:
     **/
    @RequestMapping(value = "/arrangedlist")
    @ResponseBody
    public String arrangedList(Pagination p, HttpSession httpSession){
        User u = (User)httpSession.getAttribute("user");
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        boolean ifFirst = (p.getPageCurrent() == 1);
        int totalRow = orderTableService.getOrderTableCountByStatus("1"); //得到已安排预约的总数量；
        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        System.out.println("123");
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1); //总页数，先判断总页数%每页数量是否等于0，若为0返回totalRow / pageSize，若不为0返回(totalRow / pageSize) + 1
        System.out.println(totalPage);
        List<ArrangedTab> arrangedTabs = orderTableService.getArrangedTabByStatusAndPagination(currIndex, pageSize, userService.getHosIdOfUser(u.getDept()));
        System.out.println("hxd");
        System.out.println(arrangedTabs);
        PaginationResult<ArrangedTab> paginationResult = new PaginationResult();
        paginationResult.setFirstPage(ifFirst);
        paginationResult.setLastPage(ifLast);
        paginationResult.setList(arrangedTabs);
        paginationResult.setPageNumber(p.getPageCurrent());
        paginationResult.setTotalRow(totalRow);
        paginationResult.setTotalPage(totalPage);
        paginationResult.setPageSize(pageSize);
        System.out.println("lalala");
        System.out.println(JSON.toJSONString(paginationResult));
        return JSON.toJSONString(paginationResult);
    }
    /**
     * @Description: 返回已安排的检查信息
     * @Author: Shalldid
     * @Date: Created in 16:50 2018-05-03
     * @Return:
     **/
    @RequestMapping(value = "/{checknum}/getArrangedDetail", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String getArrangedDetail(@PathVariable("checknum") String checkNum){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            RegisterInfo r = regInfoService.getRegInfoByCheckNum(checkNum);
            Patient p = patientService.getPatByIdcard(r.getIdcard());
            OrderTable o = orderTableService.getOrderTabByCheckNum(checkNum);
            //System.out.println(r.getPatientid());
            DicomWorkList d = regInfoService.getDicomWorkListByPatId(r.getPatientid());
            //System.out.println(d);
            ArrangedDetail a = new ArrangedDetail(checkNum, r.getRecordid(), r.getPatientid(), sdf.format(r.getRegtime()),
                    p.getPatname(), p.getPatgender(), p.getAge() + p.getAgetype(), hospitalService.getHosNameByHosId("H0001"),
                    r.getExamitemname(), sdf.format(o.getOrderdate()), r.getExamitemname(), d.getScheduleda());
            //System.out.println(JSON.toJSONString(a));
            return JSON.toJSONString(a);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
