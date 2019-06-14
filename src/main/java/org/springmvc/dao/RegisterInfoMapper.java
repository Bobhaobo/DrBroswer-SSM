package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.pojo.RegisterInfo;
import org.springmvc.pojo_inner.RegisterInfoInner;

import java.util.List;

public interface RegisterInfoMapper {
    int deleteByPrimaryKey(String checknum);

    int insert(RegisterInfo record);

    int insertSelective(RegisterInfo record);

    RegisterInfo selectByPrimaryKey(String checknum);

    int updateByPrimaryKeySelective(RegisterInfo record);

    int updateByPrimaryKey(RegisterInfo record);

    String getCheckNum(@Param("modality") String modality, @Param("param") String param);

    String getPatientID(@Param("modality") String modality, @Param("param") String param);

    String getRecordID(@Param("modality") String modality, @Param("param") String param);

    List<RegisterInfo> getCheckedListByFlag(@Param("flag") String flag, @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    int countCheckListByFlag(String flag);

    int updateRegStatusByChecknum(@Param("status") String status, @Param("checknum") String checknum);

    int updateRegStatusAndClinicIdByChecknum(@Param("status") String status, @Param("checknum") String checknum, @Param("clinicId") String clinicId);

    List<String> selectByFlagReturnChecknum(String flag);

    int selectCountByCheckNum(String checknum);

    int deleteByCheckNum(String checknum);

    int updateByPrimaryKeyLocal(RegisterInfoInner r);

//    RegisterInfo updateFlagByChecknum(String checknum);
}