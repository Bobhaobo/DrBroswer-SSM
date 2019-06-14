package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.lungMapper;
import org.springmvc.dto.lungDto;
import org.springmvc.pojo.lung;
import org.springmvc.service.LungService;

import java.util.List;

@Service
public class LungServiceimpl implements LungService{

    @Autowired
    private lungMapper lungMapper;

    @Override
    public lung getPatInfo(String idcard){
        return lungMapper.selectLungbyIdcard(idcard);
    }

    @Override
    public List<lung> getPatInfo1(String patname){return lungMapper.selectLungbyPatname(patname);}

    @Override
    public lung getPatInfoByPatid(String patientid){return lungMapper.selectLungByPatid(patientid);}

    @Override
    public List<lung> getPatInfoByCheckdate(String checkdate){return lungMapper.selectLungByCheckDate(checkdate);}

    @Override
    public List<lung> getPatInfoByentity(String entity){return lungMapper.selectLungByEntity(entity);}
}
