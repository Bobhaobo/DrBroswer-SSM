package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.lungMapper;
import org.springmvc.pojo.lung;
import org.springmvc.service.LungService;

import java.util.List;

@Service
public class LungServiceimpl implements LungService{

    @Autowired
    private lungMapper lungMapper;

    @Override
    public List<lung> getPatInfo(String patid){
        return lungMapper.selectLungbyPatid(patid);
    }
}
