package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.dto.RemoteAllocateDto;
import org.springmvc.dto.RemoteWaitForReportTab;
import org.springmvc.pojo.RemoteRegister;

import java.util.List;

public interface RemoteRegisterMapper {

    int deleteByPrimaryKey(String id);

    int insert(RemoteRegister record);

    int insertSelective(RemoteRegister record);

    RemoteRegister selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RemoteRegister record);

    int updateByPrimaryKey(RemoteRegister record);

    String getRemoteCheckNum(@Param("modality") String modality, @Param("param") String param);

    List<RemoteRegister> getRemoteRegisterPageByFlag(@Param("flag") String flag, @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    RemoteRegister selcetByChecknum(String checknum);

    int updateFlagByChecknum(@Param("flag") String flag, @Param("checknum") String checknum);

    int updateFlagById(@Param("flag") String flag,@Param("id") String id);

    List<RemoteAllocateDto> selectAllocateSearch(@Param("checknum") String checknum, @Param("modality") String modality, @Param("pattype") String pattype,@Param("name") String name, @Param("sex") String sex, @Param("age") String age);

    RemoteRegister selectAllocateSearchById(String id);

    List<RemoteRegister> selectRemoteAllocateByFlag(String flag);

    List<RemoteRegister> getAll();

    List<RemoteRegister> getRemoteByCheckNum(String checknum);


//    List<RemoteAllocateDto> selectByName(String name);
}