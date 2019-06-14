package org.springmvc.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class lungDto {
    @JSONField(ordinal = 4)
    private String patientid;
    @JSONField(ordinal = 1)
    private String patname;
    @JSONField(ordinal = 2)
    private String patgender;
    @JSONField(ordinal = 3)
    private Integer age;
    @JSONField(ordinal = 7)
    private String examdoc;
    @JSONField(ordinal = 8)
    private String imageclass;
    @JSONField(ordinal = 9)
    private String checkpart;
    @JSONField(ordinal = 10)
    private String scanmethod;
    @JSONField(ordinal = 11)
    private String scanway;
    @JSONField(ordinal = 12)
    private String clinicdiagnosis;
    @JSONField(ordinal = 13)
    private String checkdate;
    @JSONField(ordinal = 14)
    private String reportdate;
    @JSONField(ordinal = 15)
    private String imageview;
    @JSONField(ordinal = 16)
    private String imagesuggestion;
    @JSONField(ordinal = 5)
    private String idcard;
    @JSONField(ordinal = 6)
    private String entity;

    public lungDto(String patientid, String patname, String patgender, Integer age, String examdoc, String imageclass, String checkpart, String scanmethod, String scanway, String clinicdiagnosis, String checkdate, String reportdate, String imageview, String imagesuggestion, String idcard, String entity) {
        this.patientid = patientid;
        this.patname = patname;
        this.patgender = patgender;
        this.age = age;
        this.examdoc = examdoc;
        this.imageclass = imageclass;
        this.checkpart = checkpart;
        this.scanmethod = scanmethod;
        this.scanway = scanway;
        this.clinicdiagnosis = clinicdiagnosis;
        this.checkdate = checkdate;
        this.reportdate = reportdate;
        this.imageview = imageview;
        this.imagesuggestion = imagesuggestion;
        this.idcard = idcard;
        this.entity = entity;
    }

    public lungDto(String patientid, String patname, String patgender, Integer age, String examdoc, String imageclass, String checkpart, String scanmethod, String scanway, String clinicdiagnosis, String checkdate, String reportdate, String imageview, String imagesuggestion, String idcard) {
        this.patientid = patientid;
        this.patname = patname;
        this.patgender = patgender;
        this.age = age;
        this.examdoc = examdoc;
        this.imageclass = imageclass;
        this.checkpart = checkpart;
        this.scanmethod = scanmethod;
        this.scanway = scanway;
        this.clinicdiagnosis = clinicdiagnosis;
        this.checkdate = checkdate;
        this.reportdate = reportdate;
        this.imageview = imageview;
        this.imagesuggestion = imagesuggestion;
        this.idcard = idcard;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public String getPatgender() {
        return patgender;
    }

    public void setPatgender(String patgender) {
        this.patgender = patgender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getExamdoc() {
        return examdoc;
    }

    public void setExamdoc(String examdoc) {
        this.examdoc = examdoc;
    }

    public String getImageclass() {
        return imageclass;
    }

    public void setImageclass(String imageclass) {
        this.imageclass = imageclass;
    }

    public String getCheckpart() {
        return checkpart;
    }

    public void setCheckpart(String checkpart) {
        this.checkpart = checkpart;
    }

    public String getScanmethod() {
        return scanmethod;
    }

    public void setScanmethod(String scanmethod) {
        this.scanmethod = scanmethod;
    }

    public String getScanway() {
        return scanway;
    }

    public void setScanway(String scanway) {
        this.scanway = scanway;
    }

    public String getClinicdiagnosis() {
        return clinicdiagnosis;
    }

    public void setClinicdiagnosis(String clinicdiagnosis) {
        this.clinicdiagnosis = clinicdiagnosis;
    }

    public String getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(String checkdate) {
        this.checkdate = checkdate;
    }

    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }

    public String getImageview() {
        return imageview;
    }

    public void setImageview(String imageview) {
        this.imageview = imageview;
    }

    public String getImagesuggestion() {
        return imagesuggestion;
    }

    public void setImagesuggestion(String imagesuggestion) {
        this.imagesuggestion = imagesuggestion;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public lungDto() {
    }

    @Override
    public String toString() {
        return "lungDto{" +
                "patientid='" + patientid + '\'' +
                ", patname='" + patname + '\'' +
                ", patgender='" + patgender + '\'' +
                ", age=" + age +
                ", examdoc='" + examdoc + '\'' +
                ", imageclass='" + imageclass + '\'' +
                ", checkpart='" + checkpart + '\'' +
                ", scanmethod='" + scanmethod + '\'' +
                ", scanway='" + scanway + '\'' +
                ", clinicdiagnosis='" + clinicdiagnosis + '\'' +
                ", checkdate='" + checkdate + '\'' +
                ", reportdate='" + reportdate + '\'' +
                ", imageview='" + imageview + '\'' +
                ", imagesuggestion='" + imagesuggestion + '\'' +
                ", idcard='" + idcard + '\'' +
                ", entity='" + entity + '\'' +
                '}';
    }
}
