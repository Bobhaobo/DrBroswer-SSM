package org.springmvc.service;

import org.springframework.stereotype.Service;

public interface AnnoImageService {

    /**
     * @Description 将患者的图像信息存入到患者图像表中
     * @param sopInstanceUid
     * @param patientId
     * @param studyInstanceUid
     * @param seriesInstanceUid
     * @param annoFlag
     * @return
     */
    int insertOrUpdateImage(String sopInstanceUid, String patientId, String studyInstanceUid,
                            String seriesInstanceUid, String annoFlag, String imageFolder,
                            String imageFilename, String imagePath);
}
