package org.springmvc.dao;

import org.springmvc.pojo.lung;

import javax.xml.ws.Service;
import java.util.List;

public interface lungMapper {
    int deleteByPrimaryKey(String patientid);

    int insert(lung record);

    int insertSelective(lung record);

    lung selectByPrimaryKey(String patientid);

    int updateByPrimaryKeySelective(lung record);

    int updateByPrimaryKey(lung record);

    List<lung> selectLungbyPatid(String patid);
}