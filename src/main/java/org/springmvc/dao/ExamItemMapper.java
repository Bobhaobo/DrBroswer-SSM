package org.springmvc.dao;

import org.springmvc.pojo.ExamItem;

import java.util.List;

public interface ExamItemMapper {
    int deleteByPrimaryKey(String examitemcode);

    int insert(ExamItem record);

    int insertSelective(ExamItem record);

    ExamItem selectByPrimaryKey(String examitemcode);

    int updateByPrimaryKeySelective(ExamItem record);

    int updateByPrimaryKey(ExamItem record);

    String getExamNameByCode(String examitemcode);

    String getExamCodeByName(String examitemname);

    List<ExamItem> getCheckItemForHos();    //加载所有可用的检查设备
}