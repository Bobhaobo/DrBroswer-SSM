package org.springmvc.dao_junior;

import org.springmvc.pojo_junior.RegisterInfoJunior;

import java.util.List;

public interface RegisterInfoJuniorMapper {
    int deleteByPrimaryKey(String checknum);

    int insert(RegisterInfoJunior record);

    int insertSelective(RegisterInfoJunior record);

    RegisterInfoJunior selectByPrimaryKey(String checknum);

    int updateByPrimaryKeySelective(RegisterInfoJunior record);

    int updateByPrimaryKey(RegisterInfoJunior record);

    List<RegisterInfoJunior> selectByFlag(String flag);

    RegisterInfoJunior selectByRecordId(String checknum);
}