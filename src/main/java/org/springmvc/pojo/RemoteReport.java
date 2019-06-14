package org.springmvc.pojo;

import java.util.Date;

public class RemoteReport {
    private String id;

    private String checknum;

    private String clinicid;

    private String patname;

    private String patsex;

    private String patage;

    private String deptname;

    private String bedno;

    private String jcbw;

    private Date reporttime;

    private String sfyangxing;

    private String modality;

    private String reportpath;

    private String doccode;

    private String examdesc;

    private String examdiagnosis;

    private String juniorhos;

    private Date verifieddate;

    private String verifieddoccode;

    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getVerifieddate() {
        return verifieddate;
    }

    public void setVerifieddate(Date verifieddate) {
        this.verifieddate = verifieddate;
    }

    public String getVerifieddoccode() {
        return verifieddoccode;
    }

    public void setVerifieddoccode(String verifieddoccode) {
        this.verifieddoccode = verifieddoccode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname == null ? null : patname.trim();
    }

    public String getPatsex() {
        return patsex;
    }

    public void setPatsex(String patsex) {
        this.patsex = patsex == null ? null : patsex.trim();
    }

    public String getPatage() {
        return patage;
    }

    public void setPatage(String patage) {
        this.patage = patage == null ? null : patage.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno == null ? null : bedno.trim();
    }

    public String getJcbw() {
        return jcbw;
    }

    public void setJcbw(String jcbw) {
        this.jcbw = jcbw == null ? null : jcbw.trim();
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public String getSfyangxing() {
        return sfyangxing;
    }

    public void setSfyangxing(String sfyangxing) {
        this.sfyangxing = sfyangxing == null ? null : sfyangxing.trim();
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

    public String getDoccode() {
        return doccode;
    }

    public void setDoccode(String doccode) {
        this.doccode = doccode == null ? null : doccode.trim();
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

    public String getJuniorhos() {
        return juniorhos;
    }

    public void setJuniorhos(String juniorhos) {
        this.juniorhos = juniorhos == null ? null : juniorhos.trim();
    }

    public RemoteReport(String id, String checknum, String clinicid, String patname, String patsex, String patage, String deptname, String bedno, String jcbw, Date reporttime, String sfyangxing, String modality, String reportpath, String doccode, String examdesc, String examdiagnosis, String juniorhos) {
        this.id = id;
        this.checknum = checknum;
        this.clinicid = clinicid;
        this.patname = patname;
        this.patsex = patsex;
        this.patage = patage;
        this.deptname = deptname;
        this.bedno = bedno;
        this.jcbw = jcbw;
        this.reporttime = reporttime;
        this.sfyangxing = sfyangxing;
        this.modality = modality;
        this.reportpath = reportpath;
        this.doccode = doccode;
        this.examdesc = examdesc;
        this.examdiagnosis = examdiagnosis;
        this.juniorhos = juniorhos;
    }

    public RemoteReport() {
    }


//    public RemoteReport(String id, String checknum, String clinicid, String patname, String patsex, String patage, String deptname, String bedno, String jcbw, Date reporttime, String sfyangxing, String modality, String reportpath, String doccode, String examdesc, String examdiagnosis, String juniorhos, Date verifieddate, String verifieddoccode) {
//        this.id = id;
//        this.checknum = checknum;
//        this.clinicid = clinicid;
//        this.patname = patname;
//        this.patsex = patsex;
//        this.patage = patage;
//        this.deptname = deptname;
//        this.bedno = bedno;
//        this.jcbw = jcbw;
//        this.reporttime = reporttime;
//        this.sfyangxing = sfyangxing;
//        this.modality = modality;
//        this.reportpath = reportpath;
//        this.doccode = doccode;
//        this.examdesc = examdesc;
//        this.examdiagnosis = examdiagnosis;
//        this.juniorhos = juniorhos;
//        this.verifieddate = verifieddate;
//        this.verifieddoccode = verifieddoccode;
//    }

    @Override
    public String toString() {
        return "RemoteReport{" +
                "id='" + id + '\'' +
                ", checknum='" + checknum + '\'' +
                ", clinicid='" + clinicid + '\'' +
                ", patname='" + patname + '\'' +
                ", patsex='" + patsex + '\'' +
                ", patage='" + patage + '\'' +
                ", deptname='" + deptname + '\'' +
                ", bedno='" + bedno + '\'' +
                ", jcbw='" + jcbw + '\'' +
                ", reporttime=" + reporttime +
                ", sfyangxing='" + sfyangxing + '\'' +
                ", modality='" + modality + '\'' +
                ", reportpath='" + reportpath + '\'' +
                ", doccode='" + doccode + '\'' +
                ", examdesc='" + examdesc + '\'' +
                ", examdiagnosis='" + examdiagnosis + '\'' +
                ", juniorhos='" + juniorhos + '\'' +
                ", verifieddate=" + verifieddate +
                ", verifieddoccode='" + verifieddoccode + '\'' +
                '}';
    }

    public RemoteReport(String id, String checknum, String clinicid, String patname, String patsex, String patage, String deptname, String bedno, String jcbw, Date reporttime, String sfyangxing, String modality, String reportpath, String doccode, String examdesc, String examdiagnosis, String juniorhos, Date verifieddate, String verifieddoccode, String remarks) {
        this.id = id;
        this.checknum = checknum;
        this.clinicid = clinicid;
        this.patname = patname;
        this.patsex = patsex;
        this.patage = patage;
        this.deptname = deptname;
        this.bedno = bedno;
        this.jcbw = jcbw;
        this.reporttime = reporttime;
        this.sfyangxing = sfyangxing;
        this.modality = modality;
        this.reportpath = reportpath;
        this.doccode = doccode;
        this.examdesc = examdesc;
        this.examdiagnosis = examdiagnosis;
        this.juniorhos = juniorhos;
        this.verifieddate = verifieddate;
        this.verifieddoccode = verifieddoccode;
        this.remarks = remarks;
    }
}