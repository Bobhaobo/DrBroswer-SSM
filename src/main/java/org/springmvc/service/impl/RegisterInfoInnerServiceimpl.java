package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao_inner.RegisterInfoInnerMapper;
import org.springmvc.pojo_inner.DicomWorkListInner;
import org.springmvc.pojo_inner.RegisterInfoInner;
import org.springmvc.service.RegisterInfoInnerService;
import org.springmvc.tool.DataSourceContextHolder;

import java.util.List;

@Service
public class RegisterInfoInnerServiceimpl implements RegisterInfoInnerService {

    @Autowired
    private RegisterInfoInnerMapper registerInfoInnerMapper;
    /**
     * @Description: 向院内pacs登记表插入一条数据
     * @Author: Shalldid
     * @Date: Created in 11:07 2018-05-16
     * @Return:
     **/
    @Override
    public int saveNewRegisterInfo(RegisterInfoInner registerInfoInner){
        return registerInfoInnerMapper.insert(registerInfoInner);
    }

}
