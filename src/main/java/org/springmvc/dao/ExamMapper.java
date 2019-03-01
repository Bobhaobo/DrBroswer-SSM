package org.springmvc.dao;

import org.springmvc.pojo.Exam;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(String examClassCode);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(String examClassCode);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    List<Exam> selectAll();

    int selectCount();

}