package org.springmvc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.PathologyMapper;
import org.springmvc.pojo.Pathology;
import org.springmvc.service.PathologyService;

import java.util.List;

@Service
public class PathologyServiceimpl implements PathologyService {

    @Autowired
    private PathologyMapper pathologyMapper;

    @Override
    public Pathology getAllInfoByidcard(String idcard){return pathologyMapper.selectAllInfoByidcard(idcard);}

    @Override
    public List<Pathology> getAllInfoByPatName(String patname){return pathologyMapper.selectAllInfoBypatname(patname);}

    @Override
    public Pathology getAllInfoByPatid(String patid){return pathologyMapper.selectAllInfoBypatid((patid));}

    @Override
    public List<Pathology> getAllInfoBydate(String date){return pathologyMapper.selectAllInfoBydate(date);}

    @Override
    public List<Pathology> getAllInfoByEntity(String entity){return pathologyMapper.selectAllInfoByEntity(entity);}
}
