package org.springmvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.PatientMapper;
//import org.springmvc.dto.PatientDto;
import org.springmvc.pojo.Patient;
import org.springmvc.service.PatientService;
import org.springmvc.tool.BirthGenerator;
import org.springmvc.tool.ChineseConvert;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *@Description: 与病人服务相关实现类
 *@Author: Shalldid
 *@Date:Created in 14:55 2018-04-24
 **/
@Service
public class PatientServiceimpl implements PatientService {

    private static final Logger logger = Logger.getLogger(PatientServiceimpl.class);

    @Autowired
    private PatientMapper patientMapper;

    @Resource
    private ChineseConvert chineseConvert;

    @Resource
    private BirthGenerator birthGenerator;
    /**
     *@Description: 根据病人插入病人列表记录或者更新病人信息
     *@Author: Shalldid
     *@Date:Created in 15:02 2018-04-24
     **/
    @Override
    public int insertOrUpdatePat(String pat_name, String pat_id_card, String pat_gender, String birthday,
                                 String pat_address, String pat_soicalId, String pat_phone) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date pat_birthday = sdf.parse(birthday);
        String[] ageAndType = birthGenerator.getAgeAndType(pat_birthday, new Date());
        Patient patient = new Patient(pat_id_card, pat_name, pat_gender, pat_address, pat_soicalId, pat_phone, Integer.valueOf(ageAndType[0]), ageAndType[1]);   //初始化Patient
        System.out.println(patient);
        System.out.println("123321123321");
        try {
            patient.setPatbrithdate(pat_birthday);
            System.out.println(patient);
            //System.out.println(birthGenerator.getBirth(pat_age,pat_agetype));
        } catch (Exception e) {
            e.printStackTrace();
        }
        patient.setPatnamepy(chineseConvert.convert(pat_name));
        int status; //更新状态
        Patient patient_db = patientMapper.selectByPrimaryKey(patient.getIdcard()); //先查询数据库内有无此病人信息
        System.out.println(patient_db);
        if(patient_db != null){
            patient.setUpdatetime(new Date());
            //System.out.println("patient_db != null");
            patient_db.update(patient);
            try{
                status = patientMapper.updateByPrimaryKey(patient_db);
            }catch (Exception e){
                logger.error("更新病人信息出现错误: " + e.getMessage());
                logger.error("错误病人信息>>> " + "病人身份证号：" + patient_db.getIdcard());
                e.printStackTrace();
                status = -2;
            }
        }else {
            Date date = new Date();
            patient.setRegtime(date);
            patient.setUpdatetime(date);
            //System.out.println("patient_db == null");
            try{
                status = patientMapper.insert(patient);
            }catch (Exception e){
                logger.error("插入病人信息出现错误: " + e.getMessage());
                logger.error("错误病人信息>>> " + "病人身份证号：" + patient_db.getIdcard());
                e.printStackTrace();
                status = -2;
            }
        }
        return status;
    }
    /**
     * @Description: 根据病人身份证号加载病人
     * @Author: Shalldid
     * @Date: Created in 17:34 2018-05-02
     * @Return:
     **/
    @Override
    public Patient getPatByIdcard(String id){
        return patientMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<Patient> getAll(){return patientMapper.selectAll();}

    @Override
    public int account(){return patientMapper.selectCount();}


//    @Override
//    public List<Patient> getPatByIdcard1(String idcard){return  patientMapper.selectByPrimaryKey1(idcard);}

    @Override
    public  List<Patient> getRemoteRegisterAllocate(String name, String sex, String age){return patientMapper.selectAllocateSearch(name,sex,age);}
}
