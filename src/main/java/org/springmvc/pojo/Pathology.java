package org.springmvc.pojo;

import java.util.Date;

public class Pathology {
    private String idcard;

    private String patname;

    private String sex;

    private Integer age;

    private Date reportDate;

    private String sendHospital;

    private String sendDept;

    private String checkDoc;

    private String specimenName;

    private String clinicDiagnosis;

    private String eyeView;

    private String glassView;

    private String pathologyDiagnosis;

    private String patientid;

    private String entity;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname == null ? null : patname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getSendHospital() {
        return sendHospital;
    }

    public void setSendHospital(String sendHospital) {
        this.sendHospital = sendHospital == null ? null : sendHospital.trim();
    }

    public String getSendDept() {
        return sendDept;
    }

    public void setSendDept(String sendDept) {
        this.sendDept = sendDept == null ? null : sendDept.trim();
    }

    public String getCheckDoc() {
        return checkDoc;
    }

    public void setCheckDoc(String checkDoc) {
        this.checkDoc = checkDoc == null ? null : checkDoc.trim();
    }

    public String getSpecimenName() {
        return specimenName;
    }

    public void setSpecimenName(String specimenName) {
        this.specimenName = specimenName == null ? null : specimenName.trim();
    }

    public String getClinicDiagnosis() {
        return clinicDiagnosis;
    }

    public void setClinicDiagnosis(String clinicDiagnosis) {
        this.clinicDiagnosis = clinicDiagnosis == null ? null : clinicDiagnosis.trim();
    }

    public String getEyeView() {
        return eyeView;
    }

    public void setEyeView(String eyeView) {
        this.eyeView = eyeView == null ? null : eyeView.trim();
    }

    public String getGlassView() {
        return glassView;
    }

    public void setGlassView(String glassView) {
        this.glassView = glassView == null ? null : glassView.trim();
    }

    public String getPathologyDiagnosis() {
        return pathologyDiagnosis;
    }

    public void setPathologyDiagnosis(String pathologyDiagnosis) {
        this.pathologyDiagnosis = pathologyDiagnosis == null ? null : pathologyDiagnosis.trim();
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid == null ? null : patientid.trim();
    }

    public Pathology(String idcard, String patname, String sex, Integer age, Date reportDate, String sendHospital, String sendDept, String checkDoc, String specimenName, String clinicDiagnosis, String eyeView, String glassView, String pathologyDiagnosis, String patientid) {
        this.idcard = idcard;
        this.patname = patname;
        this.sex = sex;
        this.age = age;
        this.reportDate = reportDate;
        this.sendHospital = sendHospital;
        this.sendDept = sendDept;
        this.checkDoc = checkDoc;
        this.specimenName = specimenName;
        this.clinicDiagnosis = clinicDiagnosis;
        this.eyeView = eyeView;
        this.glassView = glassView;
        this.pathologyDiagnosis = pathologyDiagnosis;
        this.patientid = patientid;
    }

    public Pathology(String idcard, String patname, String sex, Integer age, Date reportDate, String sendHospital, String sendDept, String checkDoc, String specimenName, String clinicDiagnosis, String eyeView, String glassView, String pathologyDiagnosis, String patientid, String entity) {
        this.idcard = idcard;
        this.patname = patname;
        this.sex = sex;
        this.age = age;
        this.reportDate = reportDate;
        this.sendHospital = sendHospital;
        this.sendDept = sendDept;
        this.checkDoc = checkDoc;
        this.specimenName = specimenName;
        this.clinicDiagnosis = clinicDiagnosis;
        this.eyeView = eyeView;
        this.glassView = glassView;
        this.pathologyDiagnosis = pathologyDiagnosis;
        this.patientid = patientid;
        this.entity = entity;
    }

    public Pathology() {
    }

    @Override
    public String toString() {
        return "Pathology{" +
                "idcard='" + idcard + '\'' +
                ", patname='" + patname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", reportDate=" + reportDate +
                ", sendHospital='" + sendHospital + '\'' +
                ", sendDept='" + sendDept + '\'' +
                ", checkDoc='" + checkDoc + '\'' +
                ", specimenName='" + specimenName + '\'' +
                ", clinicDiagnosis='" + clinicDiagnosis + '\'' +
                ", eyeView='" + eyeView + '\'' +
                ", glassView='" + glassView + '\'' +
                ", pathologyDiagnosis='" + pathologyDiagnosis + '\'' +
                ", patientid='" + patientid + '\'' +
                ", entity='" + entity + '\'' +
                '}';
    }
}