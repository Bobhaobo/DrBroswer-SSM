package org.springmvc.dao;

import org.springmvc.pojo.AnnoSeries;

public interface AnnoSeriesMapper {
    int deleteByPrimaryKey(String seriesInstanceUid);

    int insert(AnnoSeries record);

    int insertSelective(AnnoSeries record);

    AnnoSeries selectByPrimaryKey(String seriesInstanceUid);

    int updateByPrimaryKeySelective(AnnoSeries record);

    int updateByPrimaryKey(AnnoSeries record);
}