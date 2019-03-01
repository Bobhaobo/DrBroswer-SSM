package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.AnnoSeriesMapper;
import org.springmvc.pojo.AnnoSeries;
import org.springmvc.service.AnnoSeriesService;

/**
 * @ClassName SeriesServiceImpl
 * @Author Bob
 * @Date 2018-12-05 16:43
 */
@Service
public class AnnoSeriesServiceImpl implements AnnoSeriesService {
    @Autowired
    private AnnoSeriesMapper annoSeriesDao;
    @Override
    public int insertOrUpdateSeries(String seriesInstanceUid, String patientId, String studyInstanceUid) {
        //查询患者序列实例Uid
        AnnoSeries annoSeries = annoSeriesDao.selectByPrimaryKey(seriesInstanceUid);
        if (annoSeries == null) {
            annoSeries = new AnnoSeries();
            annoSeries.setSeriesInstanceUid(seriesInstanceUid);
            annoSeries.setPatientId(patientId);
            annoSeries.setStudyInstanceUid(studyInstanceUid);
            return annoSeriesDao.insert(annoSeries);
        }else{
            return annoSeriesDao.updateByPrimaryKey(annoSeries);
        }

    }
}
