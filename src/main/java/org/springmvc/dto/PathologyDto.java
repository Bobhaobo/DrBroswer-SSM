package org.springmvc.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class PathologyDto {
    @JSONField(ordinal = 5)
    private String idcard;
    @JSONField(ordinal = 1)
    private String patname;
    @JSONField(ordinal = 2)
    private String sex;
    @JSONField(ordinal = 3)
    private Integer age;
    @JSONField(ordinal = 11)
    private String reportDate;
    @JSONField(ordinal = 7)
    private String sendHospital;
    @JSONField(ordinal = 8)
    private String sendDept;
    @JSONField(ordinal = 9)
    private String checkDoc;
    @JSONField(ordinal = 10)
    private String specimenName;
    @JSONField(ordinal = 12)
    private String clinicDiagnosis;
    @JSONField(ordinal = 13)
    private String eyeView;
    @JSONField(ordinal = 14)
    private String glassView;
    @JSONField(ordinal = 15)
    private String pathologyDiagnosis;
    @JSONField(ordinal = 4)
    private String patientid;
    @JSONField(ordinal = 6)
    private String entity;

    public PathologyDto(String idcard, String patname, String sex, Integer age, String reportDate, String sendHospital, String sendDept, String checkDoc, String specimenName, String clinicDiagnosis, String eyeView, String glassView, String pathologyDiagnosis, String patientid, String entity) {
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

    public PathologyDto(String idcard, String patname, String sex, Integer age, String reportDate, String sendHospital, String sendDept, String checkDoc, String specimenName, String clinicDiagnosis, String eyeView, String glassView, String pathologyDiagnosis, String patientid) {
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
        this.idcard = idcard;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getSendHospital() {
        return sendHospital;
    }

    public void setSendHospital(String sendHospital) {
        this.sendHospital = sendHospital;
    }

    public String getSendDept() {
        return sendDept;
    }

    public void setSendDept(String sendDept) {
        this.sendDept = sendDept;
    }

    public String getCheckDoc() {
        return checkDoc;
    }

    public void setCheckDoc(String checkDoc) {
        this.checkDoc = checkDoc;
    }

    public String getSpecimenName() {
        return specimenName;
    }

    public void setSpecimenName(String specimenName) {
        this.specimenName = specimenName;
    }

    public String getClinicDiagnosis() {
        return clinicDiagnosis;
    }

    public void setClinicDiagnosis(String clinicDiagnosis) {
        this.clinicDiagnosis = clinicDiagnosis;
    }

    public String getEyeView() {
        return eyeView;
    }

    public void setEyeView(String eyeView) {
        this.eyeView = eyeView;
    }

    public String getGlassView() {
        return glassView;
    }

    public void setGlassView(String glassView) {
        this.glassView = glassView;
    }

    public String getPathologyDiagnosis() {
        return pathologyDiagnosis;
    }

    public void setPathologyDiagnosis(String pathologyDiagnosis) {
        this.pathologyDiagnosis = pathologyDiagnosis;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    @Override
    public String toString() {
        return "PathologyDto{" +
                "idcard='" + idcard + '\'' +
                ", patname='" + patname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", reportDate='" + reportDate + '\'' +
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

    public PathologyDto() {
    }
}
