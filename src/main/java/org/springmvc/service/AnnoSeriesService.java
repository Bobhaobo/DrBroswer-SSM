package org.springmvc.service;

import org.springframework.stereotype.Service;

public interface AnnoSeriesService {


    /**
     * @Description 将患者的序列信息存入到患者序列表中
     * @param seriesInstanceUid
     * @param patientId
     * @param studyInstanceUid
     * @return
     */
    int insertOrUpdateSeries(String seriesInstanceUid, String patientId, String studyInstanceUid);
}
