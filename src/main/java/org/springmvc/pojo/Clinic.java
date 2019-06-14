package org.springmvc.pojo;

import java.util.Date;

public class Clinic {
    private String idcard;

    private String yibaoid;

    private String patname;

    private Integer age;

    private String patgender;

    private Date patbirthdate;

    private String telephone;

    private String address;

    private Date updatetime;

    private String entity;

    private String clinicdoc;

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

    public String getEntity() {
        return entity;
    }


    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getYibaoid() {
        return yibaoid;
    }

    public void setYibaoid(String yibaoid) {
        this.yibaoid = yibaoid == null ? null : yibaoid.trim();
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname == null ? null : patname.trim();
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
        this.patgender = patgender == null ? null : patgender.trim();
    }

    public Date getPatbirthdate() {
        return patbirthdate;
    }

    public void setPatbirthdate(Date patbirthdate) {
        this.patbirthdate = patbirthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }


    public void setEntity(String entity) {
        this.entity = entity == null ? null : entity.trim();
    }

    public String getMainsuit() {
        return mainsuit;
    }

    public void setMainsuit(String mainsuit) {
        this.mainsuit = mainsuit == null ? null : mainsuit.trim();
    }

    public String getPersonalhis() {
        return personalhis;
    }

    public void setPersonalhis(String personalhis) {
        this.personalhis = personalhis == null ? null : personalhis.trim();
    }

    public String getMaritalhis() {
        return maritalhis;
    }

    public void setMaritalhis(String maritalhis) {
        this.maritalhis = maritalhis == null ? null : maritalhis.trim();
    }

    public String getFamilyhis() {
        return familyhis;
    }

    public void setFamilyhis(String familyhis) {
        this.familyhis = familyhis == null ? null : familyhis.trim();
    }

    public String getPastillnesshis() {
        return pastillnesshis;
    }

    public void setPastillnesshis(String pastillnesshis) {
        this.pastillnesshis = pastillnesshis == null ? null : pastillnesshis.trim();
    }

    public String getPresentillnesshis() {
        return presentillnesshis;
    }

    public void setPresentillnesshis(String presentillnesshis) {
        this.presentillnesshis = presentillnesshis == null ? null : presentillnesshis.trim();
    }

    public String getSpecialitycheck() {
        return specialitycheck;
    }

    public void setSpecialitycheck(String specialitycheck) {
        this.specialitycheck = specialitycheck == null ? null : specialitycheck.trim();
    }

    public String getTentativediagnosis() {
        return tentativediagnosis;
    }

    public void setTentativediagnosis(String tentativediagnosis) {
        this.tentativediagnosis = tentativediagnosis == null ? null : tentativediagnosis.trim();
    }


    public Clinic(String idcard, String yibaoid, String patname, Integer age, String patgender, Date patbirthdate, String telephone, String address, Date updatetime, String entity, String clinicdoc, String mainsuit, String personalhis, String maritalhis, String familyhis, String pastillnesshis, String presentillnesshis, String specialitycheck, String tentativediagnosis) {
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
        this.clinicdoc = clinicdoc;
        this.mainsuit = mainsuit;
        this.personalhis = personalhis;
        this.maritalhis = maritalhis;
        this.familyhis = familyhis;
        this.pastillnesshis = pastillnesshis;
        this.presentillnesshis = presentillnesshis;
        this.specialitycheck = specialitycheck;
        this.tentativediagnosis = tentativediagnosis;
    }

    public Clinic(String idcard, String yibaoid, String patname, Integer age, String patgender, Date patbirthdate, String telephone, String address, Date updatetime, String entity, String clinicdoc, String mainsuit, String personalhis, String maritalhis, String familyhis, String pastillnesshis, String presentillnesshis, String specialitycheck, String tentativediagnosis, String patientid) {
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
        this.clinicdoc = clinicdoc;
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

    public Clinic() {}

    @Override
    public String toString() {
        return "Clinic{" +
                "idcard='" + idcard + '\'' +
                ", yibaoid='" + yibaoid + '\'' +
                ", patname='" + patname + '\'' +
                ", age=" + age +
                ", patgender='" + patgender + '\'' +
                ", patbirthdate=" + patbirthdate +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", updatetime=" + updatetime +
                ", entity='" + entity + '\'' +
                ", clinicdoc='" + clinicdoc + '\'' +
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