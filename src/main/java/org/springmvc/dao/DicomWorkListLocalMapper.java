package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.DicomWorkList;
import org.springmvc.pojo_inner.DicomWorkListInner;

public interface DicomWorkListLocalMapper {

    int deleteByPrimaryKey(String accessionn);

    int insert(DicomWorkList record);

    int insertSelective(DicomWorkList record);

    DicomWorkList selectByPrimaryKey(String accessionn);

    int updateByPrimaryKeySelective(DicomWorkList record);

    int updateByPrimaryKey(DicomWorkList record);

    String getAccessionN(@Param("modality") String modality, @Param("param") String param);

    DicomWorkList selectByPatid(String patid);

    String getAccessionNLocal(@Param("modality") String modality, @Param("param") String param);
}
