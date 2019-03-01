package org.springmvc.dao;

import org.springmvc.pojo.AnnoStudy;

public interface AnnoStudyMapper {
    int deleteByPrimaryKey(String studyInstanceUid);

    int insert(AnnoStudy record);

    int insertSelective(AnnoStudy record);

    AnnoStudy selectByPrimaryKey(String studyInstanceUid);

    int updateByPrimaryKeySelective(AnnoStudy record);

    int updateByPrimaryKey(AnnoStudy record);
}