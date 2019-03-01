package org.springmvc.dao;

import org.springmvc.pojo.Relation;

import java.util.List;

public interface RelationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

    List<String> getMajorHosidByJuniorHosid(String hosid);  //根据下级医院id返回所属上级医院id列表
}