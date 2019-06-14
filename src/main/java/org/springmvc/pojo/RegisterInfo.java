package org.springmvc.pojo;

import java.util.Date;

public class RegisterInfo {
    private String checknum;

    private String recordid;

    private Date regtime;

    private String clinicid;

    private String patientid;

    private String bedno;

    private String examitemcode;

    private String flag;

    private String deptname;

    private String examitemname;

    private Boolean ifusingflag;

    private String cardno;

    private String jcbw;

    private String sjmd;

    private String sqdbh;

    private String deptid;

    private String idcard;

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum == null ? null : checknum.trim();
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid == null ? null : recordid.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public String getClinicid() {
        return clinicid;
    }

    public void setClinicid(String clinicid) {
        this.clinicid = clinicid == null ? null : clinicid.trim();
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid == null ? null : patientid.trim();
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno == null ? null : bedno.trim();
    }

    public String getExamitemcode() {
        return examitemcode;
    }

    public void setExamitemcode(String examitemcode) {
        this.examitemcode = examitemcode == null ? null : examitemcode.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getExamitemname() {
        return examitemname;
    }

    public void setExamitemname(String examitemname) {
        this.examitemname = examitemname == null ? null : examitemname.trim();
    }

    public Boolean getIfusingflag() {
        return ifusingflag;
    }

    public void setIfusingflag(Boolean ifusingflag) {
        this.ifusingflag = ifusingflag;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getJcbw() {
        return jcbw;
    }

    public void setJcbw(String jcbw) {
        this.jcbw = jcbw == null ? null : jcbw.trim();
    }

    public String getSjmd() {
        return sjmd;
    }

    public void setSjmd(String sjmd) {
        this.sjmd = sjmd == null ? null : sjmd.trim();
    }

    public String getSqdbh() {
        return sqdbh;
    }

    public void setSqdbh(String sqdbh) {
        this.sqdbh = sqdbh == null ? null : sqdbh.trim();
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public RegisterInfo() {
    }

    public RegisterInfo(String checknum, String recordid, Date regtime, String clinicid, String patientid,
                        String bedno, String examitemcode, String flag, String deptname, String examitemname,
                        Boolean ifusingflag, String cardno, String jcbw, String sjmd, String sqdbh,
                        String deptid, String idcard) {
        this.checknum = checknum;
        this.recordid = recordid;
        this.regtime = regtime;
        this.clinicid = clinicid;
        this.patientid = patientid;
        this.bedno = bedno;
        this.examitemcode = examitemcode;
        this.flag = flag;
        this.deptname = deptname;
        this.examitemname = examitemname;
        this.ifusingflag = ifusingflag;
        this.cardno = cardno;
        this.jcbw = jcbw;
        this.sjmd = sjmd;
        this.sqdbh = sqdbh;
        this.deptid = deptid;
        this.idcard = idcard;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "checknum='" + checknum + '\'' +
                ", recordid='" + recordid + '\'' +
                ", regtime=" + regtime +
                ", clinicid='" + clinicid + '\'' +
                ", patientid='" + patientid + '\'' +
                ", bedno='" + bedno + '\'' +
                ", examitemcode='" + examitemcode + '\'' +
                ", flag='" + flag + '\'' +
                ", deptname='" + deptname + '\'' +
                ", examitemname='" + examitemname + '\'' +
                ", ifusingflag=" + ifusingflag +
                ", cardno='" + cardno + '\'' +
                ", jcbw='" + jcbw + '\'' +
                ", sjmd='" + sjmd + '\'' +
                ", sqdbh='" + sqdbh + '\'' +
                ", deptid='" + deptid + '\'' +
                ", idcard='" + idcard + '\'' +
                '}';
    }
}