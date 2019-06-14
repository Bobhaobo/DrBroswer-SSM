package org.springmvc.service;

import org.springmvc.pojo.Pathology;

import java.util.List;

public interface PathologyService {

    Pathology getAllInfoByidcard(String idcard);

    List<Pathology> getAllInfoByPatName(String patname);

    Pathology getAllInfoByPatid(String patid);

    List<Pathology> getAllInfoBydate(String date);

    List<Pathology> getAllInfoByEntity(String entity);
}
