package org.springmvc.service;

import org.springframework.stereotype.Service;

import java.util.Date;

public interface AnnoPatientService {
    /**
     * @Description 将患者的信息存入到患者信息表中
     * @param patientId
     * @param patientName
     * @param patientBirth
     * @param patientSex
     * @return
     */
    int insertOrUpdatePatient(String patientId, String patientName, Date patientBirth, String patientSex);

}
