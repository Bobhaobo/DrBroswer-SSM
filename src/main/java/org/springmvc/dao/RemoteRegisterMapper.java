package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
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
}