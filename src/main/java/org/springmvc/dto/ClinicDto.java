package org.springmvc.dto;

import java.util.Date;

public class ClinicDto {
    private String idcard;

    private String yibaoid;

    private String patname;

    private Integer age;

    private String patgender;

    private String clinicdoc;

    private String patbirthdate;

    private String telephone;

    private String address;

    private String updatetime;

    private String entity;

    private String mainsuit;

    private String personalhis;

    private String maritalhis;

    private String familyhis;

    private String pastillnesshis;

    private String presentillnesshis;

    private String specialitycheck;

    private String tentativediagnosis;

    private String patientid;

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getClinicdoc() {
        return clinicdoc;
    }

    public void setClinicdoc(String clinicdoc) {
        this.clinicdoc = clinicdoc;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getYibaoid() {
        return yibaoid;
    }

    public void setYibaoid(String yibaoid) {
        this.yibaoid = yibaoid;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPatgender() {
        return patgender;
    }

    public void setPatgender(String patgender) {
        this.patgender = patgender;
    }

    public String getPatbirthdate() {
        return patbirthdate;
    }

    public void setPatbirthdate(String patbirthdate) {
        this.patbirthdate = patbirthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getMainsuit() {
        return mainsuit;
    }

    public void setMainsuit(String mainsuit) {
        this.mainsuit = mainsuit;
    }

    public String getPersonalhis() {
        return personalhis;
    }

    public void setPersonalhis(String personalhis) {
        this.personalhis = personalhis;
    }

    public String getMaritalhis() {
        return maritalhis;
    }

    public void setMaritalhis(String maritalhis) {
        this.maritalhis = maritalhis;
    }

    public String getFamilyhis() {
        return familyhis;
    }

    public void setFamilyhis(String familyhis) {
        this.familyhis = familyhis;
    }

    public String getPastillnesshis() {
        return pastillnesshis;
    }

    public void setPastillnesshis(String pastillnesshis) {
        this.pastillnesshis = pastillnesshis;
    }

    public String getPresentillnesshis() {
        return presentillnesshis;
    }

    public void setPresentillnesshis(String presentillnesshis) {
        this.presentillnesshis = presentillnesshis;
    }

    public String getSpecialitycheck() {
        return specialitycheck;
    }

    public void setSpecialitycheck(String specialitycheck) {
        this.specialitycheck = specialitycheck;
    }

    public String getTentativediagnosis() {
        return tentativediagnosis;
    }

    public void setTentativediagnosis(String tentativediagnosis) {
        this.tentativediagnosis = tentativediagnosis;
    }

    public ClinicDto(String idcard, String yibaoid, String patname, Integer age, String patgender, String clinicdoc, String patbirthdate, String telephone, String address, String updatetime, String entity, String mainsuit, String personalhis, String maritalhis, String familyhis, String pastillnesshis, String presentillnesshis, String specialitycheck, String tentativediagnosis, String patientid) {
        this.idcard = idcard;
        this.yibaoid = yibaoid;
        this.patname = patname;
        this.age = age;
        this.patgender = patgender;
        this.clinicdoc = clinicdoc;
        this.patbirthdate = patbirthdate;
        this.telephone = telephone;
        this.address = address;
        this.updatetime = updatetime;
        this.entity = entity;
        this.mainsuit = mainsuit;
        this.personalhis = personalhis;
        this.maritalhis = maritalhis;
        this.familyhis = familyhis;
        this.pastillnesshis = pastillnesshis;
        this.presentillnesshis = presentillnesshis;
        this.specialitycheck = specialitycheck;
        this.tentativediagnosis = tentativediagnosis;
        this.patientid = patientid;
    }

    public ClinicDto(String idcard, String yibaoid, String patname, Integer age, String patgender, String patbirthdate, String telephone, String address, String updatetime, String entity, String mainsuit, String personalhis, String maritalhis, String familyhis, String pastillnesshis, String presentillnesshis, String specialitycheck, String tentativediagnosis) {
        this.idcard = idcard;
        this.yibaoid = yibaoid;
        this.patname = patname;
        this.age = age;
        this.patgender = patgender;
        this.patbirthdate = patbirthdate;
        this.telephone = telephone;
        this.address = address;
        this.updatetime = updatetime;
        this.entity = entity;
        this.mainsuit = mainsuit;
        this.personalhis = personalhis;
        this.maritalhis = maritalhis;
        this.familyhis = familyhis;
        this.pastillnesshis = pastillnesshis;
        this.presentillnesshis = presentillnesshis;
        this.specialitycheck = specialitycheck;
        this.tentativediagnosis = tentativediagnosis;
    }

    public ClinicDto(String idcard, String yibaoid, String patname, Integer age, String patgender, String clinicdoc, String patbirthdate, String telephone, String address, String updatetime, String entity, String mainsuit, String personalhis, String maritalhis, String familyhis, String pastillnesshis, String presentillnesshis, String specialitycheck, String tentativediagnosis) {
        this.idcard = idcard;
        this.yibaoid = yibaoid;
        this.patname = patname;
        this.age = age;
        this.patgender = patgender;
        this.clinicdoc = clinicdoc;
        this.patbirthdate = patbirthdate;
        this.telephone = telephone;
        this.address = address;
        this.updatetime = updatetime;
        this.entity = entity;
        this.mainsuit = mainsuit;
        this.personalhis = personalhis;
        this.maritalhis = maritalhis;
        this.familyhis = familyhis;
        this.pastillnesshis = pastillnesshis;
        this.presentillnesshis = presentillnesshis;
        this.specialitycheck = specialitycheck;
        this.tentativediagnosis = tentativediagnosis;
    }

    public ClinicDto() {
    }

    @Override
    public String toString() {
        return "ClinicDto{" +
                "idcard='" + idcard + '\'' +
                ", yibaoid='" + yibaoid + '\'' +
                ", patname='" + patname + '\'' +
                ", age=" + age +
                ", patgender='" + patgender + '\'' +
                ", clinicdoc='" + clinicdoc + '\'' +
                ", patbirthdate='" + patbirthdate + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", entity='" + entity + '\'' +
                ", mainsuit='" + mainsuit + '\'' +
                ", personalhis='" + personalhis + '\'' +
                ", maritalhis='" + maritalhis + '\'' +
                ", familyhis='" + familyhis + '\'' +
                ", pastillnesshis='" + pastillnesshis + '\'' +
                ", presentillnesshis='" + presentillnesshis + '\'' +
                ", specialitycheck='" + specialitycheck + '\'' +
                ", tentativediagnosis='" + tentativediagnosis + '\'' +
                ", patientid='" + patientid + '\'' +
                '}';
    }
}
