package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.dto.RegisterInfoInnerDto;
import org.springmvc.pojo.RegisterInfo;
import org.springmvc.pojo.RegisterInfoLocal;
import org.springmvc.pojo_inner.RegisterInfoInner;

import java.util.List;

public interface RegisterInfoLocalMapper {
    int deleteByPrimaryKey(String checknum);

    int insert(RegisterInfoInner record);

    int insertSelective(RegisterInfoInner record);

    RegisterInfoInner selectByPrimaryKey(String checknum);

    RegisterInfoInner selectByPrimaryKeyLocal(String checknum);

    int updateByPrimaryKeySelective(RegisterInfoInner record);

    int updateByPrimaryKey(RegisterInfoInner record);

    String getCheckNum(@Param("modality") String modality, @Param("param") String param);

    String getPatientID(@Param("modality") String modality, @Param("param") String param);

    String getRecordID(@Param("modality") String modality, @Param("param") String param);

    List<RegisterInfoInner> getCheckedListByFlag(@Param("flag") String flag, @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    int countCheckListByFlag(String flag);

    int updateRegStatusByChecknum(@Param("status") String status, @Param("checknum") String checknum);

    List<String> selectByFlagReturnChecknum(String flag);

    List<RegisterInfoInner> getAllInfoByFlag(String flag);

    int getCountByFlag(String flag);

    RegisterInfoInner selectByPrimaryKeyLocalModify(String checkNum);

    int updateByPrimaryKeyLocal(RegisterInfoInner r);
}