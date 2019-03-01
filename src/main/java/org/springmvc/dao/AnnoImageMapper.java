package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.AnnoImage;

public interface AnnoImageMapper {
    int deleteByPrimaryKey(String sopInstanceUid);

    int insert(AnnoImage record);

    int insertSelective(AnnoImage record);

    AnnoImage selectByPrimaryKey(String sopInstanceUid);

    int updateByPrimaryKeySelective(AnnoImage record);

    int updateByPrimaryKey(AnnoImage record);
}