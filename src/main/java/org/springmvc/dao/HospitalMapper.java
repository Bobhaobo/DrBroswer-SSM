package org.springmvc.dao;

import org.springmvc.pojo.Hospital;

import java.util.List;

public interface HospitalMapper {
    int deleteByPrimaryKey(String id);

    int insert(Hospital record);

    int insertSelective(Hospital record);

    Hospital selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Hospital record);

    int updateByPrimaryKey(Hospital record);

    String getHosLevelbyHosid(String id);   //根据医院id查询医院等级

    String getHosNameByHosId(String id);    //根据医院ID得到医院名字

    List<String> selectAllHosId();

    List<Hospital> selectAll();

    int selectCount();
}