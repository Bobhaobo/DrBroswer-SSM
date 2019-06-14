package org.springmvc.dao;

import org.springmvc.pojo.Pathology;

import java.util.List;

public interface PathologyMapper {
    int deleteByPrimaryKey(String idcard);

    int insert(Pathology record);

    int insertSelective(Pathology record);

    Pathology selectByPrimaryKey(String idcard);

    int updateByPrimaryKeySelective(Pathology record);

    int updateByPrimaryKey(Pathology record);

    Pathology selectAllInfoByidcard(String idcard);

    List<Pathology> selectAllInfoBypatname(String patname);

    Pathology selectAllInfoBypatid(String patid);

    List<Pathology> selectAllInfoBydate(String date);

    List<Pathology> selectAllInfoByEntity(String entity);
}