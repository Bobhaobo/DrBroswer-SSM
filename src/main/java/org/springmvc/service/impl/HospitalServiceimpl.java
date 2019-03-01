package org.springmvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.*;
import org.springmvc.pojo.*;
import org.springmvc.service.HospitalService;

import java.util.List;

/**
 * @Description: 医院服务
 * @Author: Shalldid
 * @Date: Created in 17:02 2018-04-27
 * @Return:
 **/
@Service
public class HospitalServiceimpl implements HospitalService {

    private static final Logger logger = Logger.getLogger(HospitalServiceimpl.class);

    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private ExamItemMapper examItemMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private ExamItemLocalMapper examItemLocalMapper;

    @Autowired
    private ExamMapper examMapper;

    /**
     * @Description: 根据医院id返回其上级医院id
     * @Author: Shalldid
     * @Date: Created in 17:05 2018-04-27
     * @Return:
     **/
    @Override
    public List<String> getApplyHospitalIdByHosid(String hosid){

        List<String> l = relationMapper.getMajorHosidByJuniorHosid(hosid);     //返回上级医院id列表

        return l;
    }
    /**
     * @Description: 通过医院id查询医院名称
     * @Author: Shalldid
     * @Date: Created in 10:29 2018-04-28
     * @Return:
     **/
    @Override
    public String getHosNameByHosId(String hosid){
        return hospitalMapper.getHosNameByHosId(hosid);
    }

    @Override
    public List<ExamItem> getExamItemByHosId(String hosid){
        return examItemMapper.getCheckItemForHos();
    }

    @Override
    public List<Department> getDeptListByHosid(String hosid){
        return  departmentMapper.selectByHosid(hosid);
    }

    @Override
    public List<String> getAllHosId() {
        return hospitalMapper.selectAllHosId();
    }

    @Override
    public List<ExamItemLocal> getExamItemsLocalByHosId(String hosId) {
        return examItemLocalMapper.getCheckItemForHos(hosId);
    }

    @Override
    public List<Hospital> getAll(){
        return hospitalMapper.selectAll();
    }

    @Override
    public List<Department> getall(){return departmentMapper.selectAll();}

    @Override
    public List<Exam> get(){return examMapper.selectAll();}

    @Override
    public int account(){return examMapper.selectCount();}

    @Override
    public int account1(){return departmentMapper.selectCount();}


    @Override
    public int account2(){return hospitalMapper.selectCount();}
}
