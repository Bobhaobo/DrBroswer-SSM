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
    public int insertOrUpdateSeries(String seriesInstanceUid, String patientId, String studyInstanceUid,String seriesAnnoInfo) {
        //查询患者序列实例Uid
        AnnoSeries annoSeries = annoSeriesDao.selectByPrimaryKey(seriesInstanceUid);
        if (annoSeries == null) {
            annoSeries = new AnnoSeries();
            annoSeries.setSeriesInstanceUid(seriesInstanceUid);
            annoSeries.setPatientId(patientId);
            annoSeries.setStudyInstanceUid(studyInstanceUid);
            annoSeries.setSeriesAnnoInfo(seriesAnnoInfo);
            return annoSeriesDao.insert(annoSeries);
        }else{
            annoSeries.setSeriesAnnoInfo(seriesAnnoInfo);
            return annoSeriesDao.updateByPrimaryKey(annoSeries);
        }

    }

    @Override
    public AnnoSeries getById(String seriesInstanceUid) {
        //查询患者序列实例Uid
        AnnoSeries annoSeries = annoSeriesDao.selectByPrimaryKey(seriesInstanceUid);
        return annoSeries;
    }
}
