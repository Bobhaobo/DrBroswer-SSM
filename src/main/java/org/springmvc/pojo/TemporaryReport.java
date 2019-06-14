package org.springmvc.pojo;

import java.util.Date;

public class TemporaryReport {
    private String id;

    private String remoteReportId;

    private String checknum;

    private String reportImagePath;

    private String writedoccode;

    private String writedocname;

    private Date writeupdatetime;

    private String verifydocname;

    private String verifydoccode;

    private Date verifyupdatetime;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRemoteReportId() {
        return remoteReportId;
    }

    public void setRemoteReportId(String remoteReportId) {
        this.remoteReportId = remoteReportId == null ? null : remoteReportId.trim();
    }

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum == null ? null : checknum.trim();
    }

    public String getReportImagePath() {
        return reportImagePath;
    }

    public void setReportImagePath(String reportImagePath) {
        this.reportImagePath = reportImagePath == null ? null : reportImagePath.trim();
    }

    public String getWritedoccode() {
        return writedoccode;
    }

    public void setWritedoccode(String writedoccode) {
        this.writedoccode = writedoccode == null ? null : writedoccode.trim();
    }

    public String getWritedocname() {
        return writedocname;
    }

    public void setWritedocname(String writedocname) {
        this.writedocname = writedocname == null ? null : writedocname.trim();
    }

    public Date getWriteupdatetime() {
        return writeupdatetime;
    }

    public void setWriteupdatetime(Date writeupdatetime) {
        this.writeupdatetime = writeupdatetime;
    }

    public String getVerifydocname() {
        return verifydocname;
    }

    public void setVerifydocname(String verifydocname) {
        this.verifydocname = verifydocname == null ? null : verifydocname.trim();
    }

    public String getVerifydoccode() {
        return verifydoccode;
    }

    public void setVerifydoccode(String verifydoccode) {
        this.verifydoccode = verifydoccode == null ? null : verifydoccode.trim();
    }

    public Date getVerifyupdatetime() {
        return verifyupdatetime;
    }

    public void setVerifyupdatetime(Date verifyupdatetime) {
        this.verifyupdatetime = verifyupdatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public TemporaryReport(String id, String remoteReportId, String checknum, String reportImagePath, String writedoccode, String writedocname, Date writeupdatetime, String verifydocname, String verifydoccode, Date verifyupdatetime, String remark) {
        this.id = id;
        this.remoteReportId = remoteReportId;
        this.checknum = checknum;
        this.reportImagePath = reportImagePath;
        this.writedoccode = writedoccode;
        this.writedocname = writedocname;
        this.writeupdatetime = writeupdatetime;
        this.verifydocname = verifydocname;
        this.verifydoccode = verifydoccode;
        this.verifyupdatetime = verifyupdatetime;
        this.remark = remark;
    }

    public TemporaryReport() {
    }

    public TemporaryReport(String id, String remoteReportId, String checknum, String reportImagePath, String writedoccode, String writedocname, Date writeupdatetime) {
        this.id = id;
        this.remoteReportId = remoteReportId;
        this.checknum = checknum;
        this.reportImagePath = reportImagePath;
        this.writedoccode = writedoccode;
        this.writedocname = writedocname;
        this.writeupdatetime = writeupdatetime;
    }

    @Override
    public String toString() {
        return "TemporaryReport{" +
                "id='" + id + '\'' +
                ", remoteReportId='" + remoteReportId + '\'' +
                ", checknum='" + checknum + '\'' +
                ", reportImagePath='" + reportImagePath + '\'' +
                ", writedoccode='" + writedoccode + '\'' +
                ", writedocname='" + writedocname + '\'' +
                ", writeupdatetime=" + writeupdatetime +
                ", verifydocname='" + verifydocname + '\'' +
                ", verifydoccode='" + verifydoccode + '\'' +
                ", verifyupdatetime=" + verifyupdatetime +
                ", remark='" + remark + '\'' +
                '}';
    }
}