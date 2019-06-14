package org.springmvc.dao;

import org.springmvc.dto.lungDto;
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

    lung selectLungbyIdcard(String idcard);

    List<lung> selectLungbyPatname(String patname);

    lung selectLungByPatid(String patientid);

    List<lung> selectLungByCheckDate(String checkdate);

    List<lung> selectLungByEntity(String entity);
}