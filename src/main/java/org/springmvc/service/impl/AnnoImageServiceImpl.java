package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.AnnoImageMapper;
import org.springmvc.pojo.AnnoImage;
import org.springmvc.service.AnnoImageService;

/**
 * @ClassName ImageServiceImpl
 * @Author Bob
 * @Date 2018-12-05 17:16
 */
@Service
public class AnnoImageServiceImpl implements AnnoImageService {
    @Autowired
    private AnnoImageMapper annoImageDao;
    @Override
    public int insertOrUpdateImage(String sopInstanceUid, String patientId, String studyInstanceUid,
                                   String seriesInstanceUid, String annoFlag, String imageFolder,
                                   String imageFilename, String imagePath) {
        AnnoImage annoImage = annoImageDao.selectByPrimaryKey(sopInstanceUid);
        if (annoImage == null) {
            annoImage = new AnnoImage();
            annoImage.setSopInstanceUid(sopInstanceUid);
            annoImage.setPatientId(patientId);
            annoImage.setStudyInstanceUid(studyInstanceUid);
            annoImage.setSeriesInstanceUid(seriesInstanceUid);
            annoImage.setAnnoFlag(annoFlag);
            annoImage.setImageFolder(imageFolder);
            annoImage.setImageFilename(imageFilename);
            annoImage.setImagePath(imagePath);
            return annoImageDao.insert(annoImage);
        }else {
            return annoImageDao.updateByPrimaryKey(annoImage);
        }
    }
}
