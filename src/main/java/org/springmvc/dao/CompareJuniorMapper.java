package org.springmvc.dao;

import org.springmvc.pojo.CompareJunior;

public interface CompareJuniorMapper {
    int deleteByPrimaryKey(String id);

    int insert(CompareJunior record);

    int insertSelective(CompareJunior record);

    CompareJunior selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CompareJunior record);

    int updateByPrimaryKey(CompareJunior record);

    CompareJunior selectByCheckNum(String checknum);
}