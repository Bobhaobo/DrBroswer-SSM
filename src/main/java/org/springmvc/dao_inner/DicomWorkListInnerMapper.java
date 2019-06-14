package org.springmvc.dao_inner;

import org.springmvc.pojo_inner.DicomWorkListInner;

import java.util.List;

public interface DicomWorkListInnerMapper {
    int deleteByPrimaryKey(String accessionn);

    int insert(DicomWorkListInner record);

    int insertSelective(DicomWorkListInner record);

    DicomWorkListInner selectByPrimaryKey(String accessionn);

    int updateByPrimaryKeySelective(DicomWorkListInner record);

    int updateByPrimaryKey(DicomWorkListInner record);
}