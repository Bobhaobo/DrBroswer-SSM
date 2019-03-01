package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.ExamItemLocal;

import java.util.List;

public interface ExamItemLocalMapper {
    int deleteByPrimaryKey(String examitemcode);

    int insert(ExamItemLocal record);

    int insertSelective(ExamItemLocal record);

    ExamItemLocal selectByPrimaryKey(String examitemcode);

    int updateByPrimaryKeySelective(ExamItemLocal record);

    int updateByPrimaryKey(ExamItemLocal record);

    String getExamNameByCode(String examitemcode);

    String getExamCodeByName(String examitemname);

    List<ExamItemLocal> getCheckItemForHos(String hosId);    //加载所有可用的检查设备

    String getCheckItemCodeForHosAndName(@Param("examitemname") String examitemname, @Param("hosId") String hosId);
}