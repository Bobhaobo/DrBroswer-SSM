package org.springmvc.service;

import org.springmvc.dto.CheckedTab;
import org.springmvc.dto.VerifiedTab;
import org.springmvc.dto.WrittedTab;
import org.springmvc.pojo.Device;
import org.springmvc.pojo.DicomWorkList;
import org.springmvc.pojo.RegisterInfo;
import org.springmvc.pojo_inner.RegisterInfoInner;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 *@Description: 与登记表相关接口
 *@Author: Shalldid
 *@Date:Created in 15:04 2018-04-25
 **/
public interface RegInfoService {

    RegisterInfoInner insertNewRegInfoLocal(String clinicId, String patName, String patGender, String patBirthdate,
                                            String address, String YiBaoID, String IdentityId, String Telephone,
                                            String PatRoomName, String BedNo, String DeptCode,
                                            String DeptName, String ExamItemName,
                                            String cardno, String jcbw, String sjmd, String sqdbh, String patType,String checkDevice,
                                            HttpSession httpSession) throws ParseException;
    /**
     *@Description: 登记新的信息到登记表内
     *@Author: Shalldid
     *@Date:Created in 15:04 2018-04-25
     *@Return 返回生成的检查号
     **/
    String insertNewRegInfo(String clinicID, String bedNo, String check_type, String cardNo, String jcbw,
                         String sjmd, String sqdbh, String deptID, String idcard);
    /**
     * @Description: 通过checkNum返回登记表
     * @Author: Shalldid
     * @Date: Created in 9:10 2018-05-03
     * @Return:
     **/
    RegisterInfo getRegInfoByCheckNum(String checkNum);


    RegisterInfoInner getRegInfoByCheckNumLocal(String checkNum);
    /**
     * @Description: 根据examItemCode返回DeviceList
     * @Author: Shalldid
     * @Date: Created in 9:39 2018-05-03
     * @Return:
     **/
//    RegisterInfo setFlagByCheckNum(String checknum);

    int updateRegisterByRegister(RegisterInfo r);

    int updateRegisterByRegisterLocal(RegisterInfoInner r);

    List<Device> getExamItemDevice(String examItemCode);
    /**
     * @Description: 根据examItemCode返回DeviceList
     * @Author: Shalldid
     * @Date: Created in 9:39 2018-05-03
     * @Return:
     **/
    List<Device> getExamItemDeviceLocal(String examItemCode);
    /**
     * @Description: 插入dicomwork一条记录
     * @Author: Shalldid
     * @Date: Created in 10:59 2018-05-03
     * @Return:
     **/
    int insertDicomWorkList(DicomWorkList d);
    /**
     * @Description: 根据病人ID得到一条dicomworklist
     * @Author: Shalldid
     * @Date: Created in 16:08 2018-05-03
     * @Return:
     **/
    DicomWorkList getDicomWorkListByPatId(String patid);
    /**
     * @Description: 根据flag为已检查加载已检查的登记表
     * @Author: Shalldid
     * @Date: Created in 17:29 2018-05-03
     * @Return:
     **/
    List<CheckedTab> getCheckedListByFlag(String flag, int currIndex, int pageSize, HttpSession httpSession);
    /**
     * @Description: 根据flag为已检查加载已检查的登记表的数量
     * @Author: Shalldid
     * @Date: Created in 17:30 2018-05-03
     * @Return:
     **/
    int countCheckListByFlag(String flag);


    int countCheckListByFlagLocal(String flag);

    /**
     * @Description: 根据检查号修改登记信息的flag
     * @Author: Shalldid
     * @Date: Created in 15:35 2018-05-07
     * @Return:
     **/
    int updateRegStatusByChecknum(String status, String checknum);
    /**
     * @Description: 根据flag为"已写报告"加载已检查的登记表
     * @Author: Shalldid
     * @Date: Created in 17:32 2018-05-03
     * @Return:
     **/
    List<WrittedTab> getWrittededListByFlag(String flag, int currIndex, int pageSize);

    List<VerifiedTab> getVerifiedListByFlag(String flag, int currIndex, int pageSize);

    List<WrittedTab> getWrittededListByFlagLocal(String flag, int currIndex, int pageSize,HttpSession httpSession);

    List<VerifiedTab> getVerifiedListByFlagLocal(String flag, int currIndex, int pageSize,HttpSession httpSession);
    
    int getCountByCheckNum(String checknum);

    int delete(String checknum);

    int updateRegStatusByChecknumLocal(String status, String checknum);

    RegisterInfoInner getRegInfoByCheckNumLocalModify(String checknum);
}
