package org.springmvc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.AnnoInfoMapper;
import org.springmvc.dao.AnnoSeriesMapper;
import org.springmvc.pojo.AnnoInfo;
import org.springmvc.pojo.AnnoSeries;
import org.springmvc.service.AnnoService;

/**
 * @ClassName AnnoServiceImpl
 * @Author Bob
 * @Date 2018-11-21 15:59
 */
@Service
public class AnnoServiceImpl implements AnnoService {
    @Autowired
    private AnnoInfoMapper annoAnnoInfoDao;

    @Override
    public int insertAnnoInfo(String annoId, String patientId, String studyInstanceUid, String seriesInstanceUid , String sopInstanceUid, Float x1Left, Float y1Left, Float x2Right, Float y2Right) {
        AnnoInfo annoInfo = new AnnoInfo();
        annoInfo.setAnnoId(annoId);
        annoInfo.setPatientId(patientId);
        annoInfo.setStudyInstanceUid(studyInstanceUid);
        annoInfo.setSeriesInstanceUid(seriesInstanceUid);
        annoInfo.setSopInstanceUid(sopInstanceUid);
        annoInfo.setX1Left(x1Left);
        annoInfo.setY1Left(y1Left);
        annoInfo.setX2Right(x2Right);
        annoInfo.setY2Right(y2Right);
        return annoAnnoInfoDao.insert(annoInfo);
    }

    @Override
    public float[][] getCoordinateInfo(String[] array) {
        //接收到数组的长度
        int len = array.length;
        //每四个一组，得到的组数
        int num = (int)len/4;
        //新建一个二维数组
        float[][] xOy=  new float[num][4];
        for (int i=0;i<num;i++) {
            for (int j=0;j<4;j++) {
                xOy[i][j] = Float.parseFloat(array[i*4+j]);
            }
        }
        return xOy;
    }

    @Override
    public int deleteAnnoInfo(String sopUid) {
        return  annoAnnoInfoDao.deleteAnnoInfoBySOPUid(sopUid);
    }
}
