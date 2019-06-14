package org.springmvc.pojo;

import java.util.Date;

public class Report {
    private String reportcode;

    private String recordid;

    private String checknum;

    private String clinicid;

    private String patientid;

    private String idcard;

    private String bedno;

    private Date reporttime;

    private String flag;

    private String examdesc;

    private String examdiagnosis;

    private String doccode;

    private String examitemcode;

    private String examitemname;

    private Integer pattypecode;

    private String deptname;

    private String deptid;

    private Boolean ifusingflag;

    private String reportImagePath;

    private String cardno;

    private String jcbw;

    private String sfyangxing;

    private String verifydoccode;

    private Date verifydate;

    public Date getVerifydate() {
        return verifydate;
    }

    public void setVerifydate(Date verifydate) {
        this.verifydate = verifydate;
    }

    public String getVerifydoccode() {
        return verifydoccode;
    }

    public void setVerifydoccode(String verifydoccode) {
        this.verifydoccode = verifydoccode;
    }

    public String getReportcode() {
        return reportcode;
    }

    public void setReportcode(String reportcode) {
        this.reportcode = reportcode == null ? null : reportcode.trim();
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid == null ? null : recordid.trim();
    }

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum == null ? null : checknum.trim();
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno == null ? null : bedno.trim();
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getExamdesc() {
        return examdesc;
    }

    public void setExamdesc(String examdesc) {
        this.examdesc = examdesc == null ? null : examdesc.trim();
    }

    public String getExamdiagnosis() {
        return examdiagnosis;
    }

    public void setExamdiagnosis(String examdiagnosis) {
        this.examdiagnosis = examdiagnosis == null ? null : examdiagnosis.trim();
    }

    public String getDoccode() {
        return doccode;
    }

    public void setDoccode(String doccode) {
        this.doccode = doccode == null ? null : doccode.trim();
    }

    public String getExamitemcode() {
        return examitemcode;
    }

    public void setExamitemcode(String examitemcode) {
        this.examitemcode = examitemcode == null ? null : examitemcode.trim();
    }

    public String getExamitemname() {
        return examitemname;
    }

    public void setExamitemname(String examitemname) {
        this.examitemname = examitemname == null ? null : examitemname.trim();
    }

    public Integer getPattypecode() {
        return pattypecode;
    }

    public void setPattypecode(Integer pattypecode) {
        this.pattypecode = pattypecode;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public Boolean getIfusingflag() {
        return ifusingflag;
    }

    public void setIfusingflag(Boolean ifusingflag) {
        this.ifusingflag = ifusingflag;
    }

    public String getReportImagePath() {
        return reportImagePath;
    }

    public void setReportImagePath(String reportImagePath) {
        this.reportImagePath = reportImagePath == null ? null : reportImagePath.trim();
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

    public String getSfyangxing() {
        return sfyangxing;
    }

    public void setSfyangxing(String sfyangxing) {
        this.sfyangxing = sfyangxing == null ? null : sfyangxing.trim();
    }

    public Report() {
    }

    public Report(String reportcode, String recordid, String checknum, String clinicid, String patientid, String idcard,
                  String bedno, Date reporttime, String flag, String examdesc, String examdiagnosis, String doccode,
                  String examitemcode, String examitemname, Integer pattypecode, String deptname, String deptid,
                  Boolean ifusingflag, String reportImagePath, String cardno, String jcbw, String sfyangxing) {

        this.reportcode = reportcode;
        this.recordid = recordid;
        this.checknum = checknum;
        this.clinicid = clinicid;
        this.patientid = patientid;
        this.idcard = idcard;
        this.bedno = bedno;
        this.reporttime = reporttime;
        this.flag = flag;
        this.examdesc = examdesc;
        this.examdiagnosis = examdiagnosis;
        this.doccode = doccode;
        this.examitemcode = examitemcode;
        this.examitemname = examitemname;
        this.pattypecode = pattypecode;
        this.deptname = deptname;
        this.deptid = deptid;
        this.ifusingflag = ifusingflag;
        this.reportImagePath = reportImagePath;
        this.cardno = cardno;
        this.jcbw = jcbw;
        this.sfyangxing = sfyangxing;
    }

    public Report(String reportcode, String recordid, String checknum, String clinicid, String patientid, String idcard, String bedno, Date reporttime, String flag, String examdesc, String examdiagnosis, String doccode, String examitemcode, String examitemname, Integer pattypecode, String deptname, String deptid, Boolean ifusingflag, String reportImagePath, String cardno, String jcbw, String sfyangxing, String verifydoccode) {
        this.reportcode = reportcode;
        this.recordid = recordid;
        this.checknum = checknum;
        this.clinicid = clinicid;
        this.patientid = patientid;
        this.idcard = idcard;
        this.bedno = bedno;
        this.reporttime = reporttime;
        this.flag = flag;
        this.examdesc = examdesc;
        this.examdiagnosis = examdiagnosis;
        this.doccode = doccode;
        this.examitemcode = examitemcode;
        this.examitemname = examitemname;
        this.pattypecode = pattypecode;
        this.deptname = deptname;
        this.deptid = deptid;
        this.ifusingflag = ifusingflag;
        this.reportImagePath = reportImagePath;
        this.cardno = cardno;
        this.jcbw = jcbw;
        this.sfyangxing = sfyangxing;
        this.verifydoccode = verifydoccode;
    }

    public Report(String reportcode, String recordid, String checknum, String clinicid, String patientid, String idcard, String bedno, Date reporttime, String flag, String examdesc, String examdiagnosis, String doccode, String examitemcode, String examitemname, Integer pattypecode, String deptname, String deptid, Boolean ifusingflag, String reportImagePath, String cardno, String jcbw, String sfyangxing, String verifydoccode, Date verifydate) {
        this.reportcode = reportcode;
        this.recordid = recordid;
        this.checknum = checknum;
        this.clinicid = clinicid;
        this.patientid = patientid;
        this.idcard = idcard;
        this.bedno = bedno;
        this.reporttime = reporttime;
        this.flag = flag;
        this.examdesc = examdesc;
        this.examdiagnosis = examdiagnosis;
        this.doccode = doccode;
        this.examitemcode = examitemcode;
        this.examitemname = examitemname;
        this.pattypecode = pattypecode;
        this.deptname = deptname;
        this.deptid = deptid;
        this.ifusingflag = ifusingflag;
        this.reportImagePath = reportImagePath;
        this.cardno = cardno;
        this.jcbw = jcbw;
        this.sfyangxing = sfyangxing;
        this.verifydoccode = verifydoccode;
        this.verifydate = verifydate;
    }

    @Override
    public String toString(){
        return reportcode;
    }
}