package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao_inner.DicomWorkListInnerMapper;
import org.springmvc.pojo_inner.DicomWorkListInner;
import org.springmvc.service.DicomWorkListInnerService;
import org.springmvc.tool.DataSourceContextHolder;

import java.util.List;

@Service
public class DicomWorkListInnerServiceimpl implements DicomWorkListInnerService {

    @Autowired
    private DicomWorkListInnerMapper dicomWorkListInnerMapper;
    /**
     * @Description: 向院内pacs worklist表插入一条记录
     * @Author: Shalldid
     * @Date: Created in 11:07 2018-05-16
     * @Return:
     **/
    @Override
    public int saveNewDicomWorkList(DicomWorkListInner dicomWorkListInner){
        return dicomWorkListInnerMapper.insert(dicomWorkListInner);
    }


}
