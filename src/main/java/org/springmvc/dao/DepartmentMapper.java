package org.springmvc.dao;

import org.springmvc.pojo.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    String getHosIdbyDeptid(String deptid);    //根据科室id查询所属医院id

    String getDeptNamebyDeptid(String id);  //根据科室id查询所属科室名称

    List<Department> selectByHosid(String hosid);   //根据医院id返回科室列表

    List<Department> selectAll();

    int selectCount();
}