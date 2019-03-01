package org.springmvc.dao;

import org.springmvc.pojo.AnnoPatient;

public interface AnnoPatientMapper {
    int deleteByPrimaryKey(String patientId);

    int insert(AnnoPatient record);

    int insertSelective(AnnoPatient record);

    AnnoPatient selectByPrimaryKey(String patientId);

    int updateByPrimaryKeySelective(AnnoPatient record);

    int updateByPrimaryKey(AnnoPatient record);
}