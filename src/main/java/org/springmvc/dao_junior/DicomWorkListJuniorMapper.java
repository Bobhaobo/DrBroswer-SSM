package org.springmvc.dao_junior;

import org.springmvc.pojo_junior.DicomWorkListJunior;

public interface DicomWorkListJuniorMapper {
    int deleteByPrimaryKey(String accessionn);

    int insert(DicomWorkListJunior record);

    int insertSelective(DicomWorkListJunior record);

    DicomWorkListJunior selectByPrimaryKey(String accessionn);

    int updateByPrimaryKeySelective(DicomWorkListJunior record);

    int updateByPrimaryKey(DicomWorkListJunior record);

    String selectStartDateByPatientId(String patientid);

    DicomWorkListJunior selectByAccessionN(String c);
}