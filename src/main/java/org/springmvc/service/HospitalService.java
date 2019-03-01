package org.springmvc.service;

import org.springmvc.pojo.*;

import java.util.List;

public interface HospitalService {

    List<String> getApplyHospitalIdByHosid(String hosid);

    String getHosNameByHosId(String hosid);

    List<ExamItem> getExamItemByHosId(String hosid);

    List<ExamItemLocal> getExamItemsLocalByHosId(String hosId);

    List<Department> getDeptListByHosid(String hosid);

    List<String> getAllHosId();

    List<Hospital> getAll();

    List<Department> getall();

    List<Exam> get();

    int account();
//
    int account1();

    int account2();


}
