package org.springmvc.dao_inner;

import org.springmvc.pojo_inner.RegisterInfoInner;

import java.util.List;

public interface RegisterInfoInnerMapper {
    int deleteByPrimaryKey(String checknum);

    int insert(RegisterInfoInner record);

    int insertSelective(RegisterInfoInner record);

    RegisterInfoInner selectByPrimaryKey(String checknum);

    RegisterInfoInner selectByClinicId(String checknum);

    int updateByPrimaryKeySelective(RegisterInfoInner record);

    int updateByPrimaryKey(RegisterInfoInner record);

}