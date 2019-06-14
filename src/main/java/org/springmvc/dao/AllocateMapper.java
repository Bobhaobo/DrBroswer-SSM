package org.springmvc.dao;

import org.springmvc.pojo.Allocate;

public interface AllocateMapper {
    int deleteByPrimaryKey(String id);

    int insert(Allocate record);

    int insertSelective(Allocate record);

    Allocate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Allocate record);

    int updateByPrimaryKey(Allocate record);
}