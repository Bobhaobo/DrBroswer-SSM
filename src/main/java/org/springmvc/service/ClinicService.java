package org.springmvc.service;

import org.springmvc.pojo.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic getAllInfo(String idcard);

    List<Clinic> getAll();

    int account();

    List<Clinic> getAllInfo1(String idcard);

    List<Clinic> getAllInfoByPatname(String patname);

    List<Clinic> getAllInfoByPatientid(String patientid);

    List<Clinic> getAllInfoByUpdateTime(String checkdate);

    List<Clinic> getAllInfoByentity(String entity);
}
