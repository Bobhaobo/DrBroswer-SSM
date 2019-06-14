package org.springmvc.dao;

import org.springmvc.pojo.Clinic;

import java.util.List;

public interface ClinicMapper {
    int deleteByPrimaryKey(String idcard);

    int insert(Clinic record);

    int insertSelective(Clinic record);

    Clinic selectByPrimaryKey(String idcard);

    int updateByPrimaryKeySelective(Clinic record);

    int updateByPrimaryKey(Clinic record);

    List<Clinic> selectAll();

    int getAccount();

    List<Clinic> selectAllInfo(String idcard);

    List<Clinic> selectAllInfoBypatname(String patname);

    List<Clinic> selectAllInfoBypatientid(String patientid);

    List<Clinic> selectAllInfoByUpdateTime(String updatetime);

    List<Clinic> selectAllInfoByentity(String entity);
}