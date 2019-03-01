package org.springmvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.AnnoStudyMapper;
import org.springmvc.pojo.AnnoStudy;
import org.springmvc.service.AnnoStudyService;

import java.util.Date;

/**
 * @ClassName StudyServiceImpl
 * @Author Bob
 * @Date 2018-12-05 16:18
 */
@Service
public class AnnoStudyServiceImpl implements AnnoStudyService {
    private static final Logger logger = Logger.getLogger(AnnoStudyServiceImpl.class);
    @Autowired
    private AnnoStudyMapper annoStudyDao;
    @Override
    public int insertOrUpdateStudy(String studyInstanceUid, String patientId, String modality, Date studyDate) {
        //查询检查实例Uid
        AnnoStudy annoStudy = annoStudyDao.selectByPrimaryKey(studyInstanceUid);
        if (annoStudy == null) {
            annoStudy = new AnnoStudy();
            annoStudy.setStudyInstanceUid(studyInstanceUid);
            annoStudy.setPatientId(patientId);
            annoStudy.setModality(modality);
            annoStudy.setStudyDate(studyDate);
            return annoStudyDao.insert(annoStudy);
        }else {
            return annoStudyDao.updateByPrimaryKey(annoStudy);
        }
    }
}
