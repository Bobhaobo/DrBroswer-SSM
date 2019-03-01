package org.springmvc.dao;

import org.springmvc.pojo.AnnoInfo;

public interface AnnoInfoMapper {
    int deleteByPrimaryKey(String annoId);

    int insert(AnnoInfo record);

    int insertSelective(AnnoInfo record);

    AnnoInfo selectByPrimaryKey(String annoId);

    int updateByPrimaryKeySelective(AnnoInfo record);

    int updateByPrimaryKey(AnnoInfo record);

    /**
     * 通过sopUid删除关于该sopUid的
     * @param sopUid
     * @return
     */
    int deleteAnnoInfoBySOPUid(String sopUid);

    /**
     * 获取ID
     * @param param
     * @return
     */
    String getAnnoId(String param);
}