package org.springmvc.pojo;

import java.util.Date;

public class HisInfo {
    private String id;

    private String patientid;

    private Date reportdate;

    private String modality;

    private String reportpath;

    private String checknum;

    private String csh;

    private String cardno;

    private String sqdbh;

    private String pattype;

    private String clinicid;

    private String sqks;

    private String idcard;

    private String patname;

    private String hosId;

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid == null ? null : patientid.trim();
    }

    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality == null ? null : modality.trim();
    }

    public String getReportpath() {
        return reportpath;
    }

    public void setReportpath(String reportpath) {
        this.reportpath = reportpath == null ? null : reportpath.trim();
    }

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum == null ? null : checknum.trim();
    }

    public String getCsh() {
        return csh;
    }

    public void setCsh(String csh) {
        this.csh = csh == null ? null : csh.trim();
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getSqdbh() {
        return sqdbh;
    }

    public void setSqdbh(String sqdbh) {
        this.sqdbh = sqdbh == null ? null : sqdbh.trim();
    }

    public String getPattype() {
        return pattype;
    }

    public void setPattype(String pattype) {
        this.pattype = pattype == null ? null : pattype.trim();
    }

    public String getClinicid() {
        return clinicid;
    }

    public void setClinicid(String clinicid) {
        this.clinicid = clinicid == null ? null : clinicid.trim();
    }

    public String getSqks() {
        return sqks;
    }

    public void setSqks(String sqks) {
        this.sqks = sqks == null ? null : sqks.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public HisInfo() {
    }

    public HisInfo(String id, String patientid, Date reportdate, String modality, String reportpath, String checknum, String csh, String cardno,
                   String sqdbh, String pattype, String clinicid, String sqks, String idcard) {
        this.id = id;
        this.patientid = patientid;
        this.reportdate = reportdate;
        this.modality = modality;
        this.reportpath = reportpath;
        this.checknum = checknum;
        this.csh = csh;
        this.cardno = cardno;
        this.sqdbh = sqdbh;
        this.pattype = pattype;
        this.clinicid = clinicid;
        this.sqks = sqks;
        this.idcard = idcard;
    }

    @Override
    public String toString() {
        return "HisInfo{" +
                "id='" + id + '\'' +
                ", patientid='" + patientid + '\'' +
                ", reportdate=" + reportdate +
                ", modality='" + modality + '\'' +
                ", reportpath='" + reportpath + '\'' +
                ", checknum='" + checknum + '\'' +
                ", csh='" + csh + '\'' +
                ", cardno='" + cardno + '\'' +
                ", sqdbh='" + sqdbh + '\'' +
                ", pattype='" + pattype + '\'' +
                ", clinicid='" + clinicid + '\'' +
                ", sqks='" + sqks + '\'' +
                ", idcard='" + idcard + '\'' +
                ", patname='" + patname + '\'' +
                '}';
    }
}