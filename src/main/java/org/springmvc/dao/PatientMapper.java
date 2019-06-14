package org.springmvc.dao;

//import org.springmvc.dto.PatientDto;
import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.Patient;

import java.util.List;

public interface PatientMapper {
    int deleteByPrimaryKey(String idcard);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(String idcard);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

    List<Patient> selectAll();

    int selectCount();

//    List<Patient> selectByPrimaryKey1 (String idcard);

    List<Patient> selectAllocateSearch(@Param("name") String name,@Param("sex") String sex,@Param("age") String age);
}