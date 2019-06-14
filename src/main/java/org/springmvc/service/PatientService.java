package org.springmvc.service;

import org.springmvc.pojo.Patient;
//import org.springmvc.dto.PatientDto;

import java.text.ParseException;
import java.util.List;

/**
 *@Description: 与病人信息相关接口
 *@Author: Shalldid
 *@Date:Created in 14:54 2018-04-24
 **/
public interface PatientService {
    /**
     *@Description: 根据病人插入或者更新病人对象
     *@Author: Shalldid
     *@Date:Created in 15:03 2018-04-24
     **/
    int insertOrUpdatePat(String pat_name, String pat_id_card, String pat_gender, String brithday,
                          String pat_address, String pat_soicalId, String pat_phone) throws ParseException;
    /**
     * @Description: 根据病人身份证号加载病人
     * @Author: Shalldid
     * @Date: Created in 17:32 2018-05-02
     * @Return:
     **/
    Patient getPatByIdcard(String idcard);
//    List<Patient> getPatByIdcard1(String idcard);
    List<Patient> getAll();
    int account();

    List<Patient> getRemoteRegisterAllocate(String name, String sex, String age);
}
