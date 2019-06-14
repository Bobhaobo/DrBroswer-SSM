package org.springmvc.service;

import org.springmvc.dto.lungDto;
import org.springmvc.pojo.lung;

import java.util.List;

public interface LungService {

    lung getPatInfo(String idcard);
    List<lung> getPatInfo1(String patname);
    lung getPatInfoByPatid(String patientid);
    List<lung> getPatInfoByCheckdate(String checkdate);
    List<lung> getPatInfoByentity(String entity);
}
