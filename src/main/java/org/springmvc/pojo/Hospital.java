package org.springmvc.pojo;

public class Hospital {
    private String id;

    private String hosName;

    private String hosLevel;

    private String remarks;

    private String hosCode;

    private String hosAddress;

    public String getHosCode() {
        return hosCode;
    }

    public void setHosCode(String hosCode) {
        this.hosCode = hosCode;
    }

    public String getHosAddress() {
        return hosAddress;
    }

    public void setHosAddress(String hosAddress) {
        this.hosAddress = hosAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getHosLevel() {
        return hosLevel;
    }

    public void setHosLevel(String hosLevel) {
        this.hosLevel = hosLevel;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Hospital(String id, String hosName, String hosLevel, String remarks, String hosCode, String hosAddress) {
        this.id = id;
        this.hosName = hosName;
        this.hosLevel = hosLevel;
        this.remarks = remarks;
        this.hosCode = hosCode;
        this.hosAddress = hosAddress;
    }

    public Hospital() {
    }
}