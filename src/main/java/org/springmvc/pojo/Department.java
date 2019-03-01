package org.springmvc.pojo;

public class Department {
    private String id;

    private String deptname;

    private String hosid;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getHosid() {
        return hosid;
    }

    public void setHosid(String hosid) {
        this.hosid = hosid == null ? null : hosid.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Department(String id, String deptname, String hosid, String remarks) {
        this.id = id;
        this.deptname = deptname;
        this.hosid = hosid;
        this.remarks = remarks;
    }

    public Department() {
    }
}