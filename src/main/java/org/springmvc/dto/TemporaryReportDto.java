package org.springmvc.dto;

import java.util.Date;

public class TemporaryReportDto {
    private String id;

    private String remoteReportId;

    private String checknum;

    private String reportImagePath;

    private String writedoccode;

    private String writedocname;

    private String writeupdatetime;

    private String verifydocname;

    private String verifydoccode;

    private String verifyupdatetime;

    private String remark;


    public TemporaryReportDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemoteReportId() {
        return remoteReportId;
    }

    public void setRemoteReportId(String remoteReportId) {
        this.remoteReportId = remoteReportId;
    }

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum;
    }

    public String getReportImagePath() {
        return reportImagePath;
    }

    public void setReportImagePath(String reportImagePath) {
        this.reportImagePath = reportImagePath;
    }

    public String getWritedoccode() {
        return writedoccode;
    }

    public void setWritedoccode(String writedoccode) {
        this.writedoccode = writedoccode;
    }

    public String getWritedocname() {
        return writedocname;
    }

    public void setWritedocname(String writedocname) {
        this.writedocname = writedocname;
    }


    public String getVerifydocname() {
        return verifydocname;
    }

    public void setVerifydocname(String verifydocname) {
        this.verifydocname = verifydocname;
    }

    public String getVerifydoccode() {
        return verifydoccode;
    }

    public void setVerifydoccode(String verifydoccode) {
        this.verifydoccode = verifydoccode;
    }

    public String getVerifyupdatetime() {
        return verifyupdatetime;
    }

    public void setVerifyupdatetime(String verifyupdatetime) {
        this.verifyupdatetime = verifyupdatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWriteupdatetime() {
        return writeupdatetime;
    }

    public void setWriteupdatetime(String writeupdatetime) {
        this.writeupdatetime = writeupdatetime;
    }

    public TemporaryReportDto(String id, String remoteReportId, String checknum, String reportImagePath, String writedoccode, String writedocname, String writeupdatetime, String verifydocname, String verifydoccode, String verifyupdatetime, String remark) {
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

    @Override
    public String toString() {
        return "TemporaryReportDto{" +
                "id='" + id + '\'' +
                ", remoteReportId='" + remoteReportId + '\'' +
                ", checknum='" + checknum + '\'' +
                ", reportImagePath='" + reportImagePath + '\'' +
                ", writedoccode='" + writedoccode + '\'' +
                ", writedocname='" + writedocname + '\'' +
                ", writeupdatetime='" + writeupdatetime + '\'' +
                ", verifydocname='" + verifydocname + '\'' +
                ", verifydoccode='" + verifydoccode + '\'' +
                ", verifyupdatetime='" + verifyupdatetime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
