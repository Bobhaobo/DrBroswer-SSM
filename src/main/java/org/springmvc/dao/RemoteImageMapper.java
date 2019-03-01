package org.springmvc.dao;

import org.springmvc.pojo.RemoteImage;

public interface RemoteImageMapper {
    int deleteByPrimaryKey(String accessionn);

    int insert(RemoteImage record);

    int insertSelective(RemoteImage record);

    RemoteImage selectByPrimaryKey(String accessionn);

    int updateByPrimaryKeySelective(RemoteImage record);

    int updateByPrimaryKey(RemoteImage record);
}