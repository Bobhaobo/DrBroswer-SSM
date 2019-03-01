package org.springmvc.service;

import org.springframework.stereotype.Service;

import java.util.Date;

public interface AnnoStudyService {
    /**
     * @Description 将患者的检查信息存入到患者检查表中
     * @param studyInstanceUid
     * @param patientId
     * @param modality
     * @param studyDate
     * @return
     */
    int insertOrUpdateStudy(String studyInstanceUid, String patientId, String modality, Date studyDate);
}
