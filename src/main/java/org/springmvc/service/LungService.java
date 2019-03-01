package org.springmvc.service;

import org.springmvc.pojo.lung;

import java.util.List;

public interface LungService {

    List<lung> getPatInfo(String patid);
}
