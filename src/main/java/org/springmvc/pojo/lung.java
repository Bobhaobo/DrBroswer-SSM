package org.springmvc.pojo;

import java.util.Date;

public class lung {
    private String patientid;

    private String patname;

    private String patgender;

    private Integer age;

    private String examdoc;

    private String imageclass;

    private String checkpart;

    private String scanmethod;

    private String scanway;

    private String clinicdiagnosis;

    private Date checkdate;

    private Date reportdate;

    private String imageview;

    private String imagesuggestion;

    private String idcard;

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
        this.idcard = idcard;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid == null ? null : patientid.trim();
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname == null ? null : patname.trim();
    }

    public String getPatgender() {
        return patgender;
    }

    public void setPatgender(String patgender) {
        this.patgender = patgender == null ? null : patgender.trim();
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
        this.examdoc = examdoc == null ? null : examdoc.trim();
    }

    public String getImageclass() {
        return imageclass;
    }

    public void setImageclass(String imageclass) {
        this.imageclass = imageclass == null ? null : imageclass.trim();
    }

    public String getCheckpart() {
        return checkpart;
    }

    public void setCheckpart(String checkpart) {
        this.checkpart = checkpart == null ? null : checkpart.trim();
    }

    public String getScanmethod() {
        return scanmethod;
    }

    public void setScanmethod(String scanmethod) {
        this.scanmethod = scanmethod == null ? null : scanmethod.trim();
    }

    public String getScanway() {
        return scanway;
    }

    public void setScanway(String scanway) {
        this.scanway = scanway == null ? null : scanway.trim();
    }

    public String getClinicdiagnosis() {
        return clinicdiagnosis;
    }

    public void setClinicdiagnosis(String clinicdiagnosis) {
        this.clinicdiagnosis = clinicdiagnosis == null ? null : clinicdiagnosis.trim();
    }

    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }

    public String getImageview() {
        return imageview;
    }

    public void setImageview(String imageview) {
        this.imageview = imageview == null ? null : imageview.trim();
    }

    public String getImagesuggestion() {
        return imagesuggestion;
    }

    public void setImagesuggestion(String imagesuggestion) {
        this.imagesuggestion = imagesuggestion == null ? null : imagesuggestion.trim();
    }


    public lung(String patientid, String patname, String patgender, Integer age, String examdoc, String imageclass, String checkpart, String scanmethod, String scanway, String clinicdiagnosis, Date checkdate, Date reportdate, String imageview, String imagesuggestion, String idcard, String entity) {
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

    public lung() {
    }

    @Override
    public String toString() {
        return "lung{" +
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
                ", checkdate=" + checkdate +
                ", reportdate=" + reportdate +
                ", imageview='" + imageview + '\'' +
                ", imagesuggestion='" + imagesuggestion + '\'' +
                ", idcard='" + idcard + '\'' +
                ", entity='" + entity + '\'' +
                '}';
    }
}