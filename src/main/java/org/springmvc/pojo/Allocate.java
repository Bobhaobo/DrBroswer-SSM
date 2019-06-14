package org.springmvc.pojo;

import java.util.Date;

public class Allocate {
    private String id;

    private String checknum;

    private String allocatedoccode;

    private Date allocatetime;

    private String allocateddoccode;

    private String remark;

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

    public String getAllocatedoccode() {
        return allocatedoccode;
    }

    public void setAllocatedoccode(String allocatedoccode) {
        this.allocatedoccode = allocatedoccode == null ? null : allocatedoccode.trim();
    }

    public Date getAllocatetime() {
        return allocatetime;
    }

    public void setAllocatetime(Date allocatetime) {
        this.allocatetime = allocatetime;
    }

    public String getAllocateddoccode() {
        return allocateddoccode;
    }

    public void setAllocateddoccode(String allocateddoccode) {
        this.allocateddoccode = allocateddoccode == null ? null : allocateddoccode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}