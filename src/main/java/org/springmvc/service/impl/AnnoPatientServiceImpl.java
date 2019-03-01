package org.springmvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.AnnoPatientMapper;
import org.springmvc.pojo.AnnoPatient;
import org.springmvc.service.AnnoPatientService;

import java.util.Date;

/**
 * @ClassName AnnoPatientServiceImpl
 * @Author Bob
 * @Date 2018-12-17 15:28
 */
@Service
public class AnnoPatientServiceImpl implements AnnoPatientService {

    private static final Logger logger = Logger.getLogger(AnnoPatientServiceImpl.class);

    @Autowired
    private AnnoPatientMapper annoPatientDao;

    @Override
    public int insertOrUpdatePatient(String patientId, String patientName, Date patientBirth, String patientSex) {
        //查询患者ID
        AnnoPatient annoPatient = annoPatientDao.selectByPrimaryKey(patientId);
        if (annoPatient == null){
            annoPatient = new AnnoPatient();
            annoPatient.setPatientId(patientId);
            annoPatient.setPatientName(patientName);
            annoPatient.setPatientBirth(patientBirth);
            annoPatient.setPatientSex(patientSex);
            return annoPatientDao.insert(annoPatient);
        }else{
            return annoPatientDao.updateByPrimaryKey(annoPatient);
        }

    }
}
