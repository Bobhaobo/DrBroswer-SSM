package org.springmvc.pojo;

public class CompareJunior {
    private String id;

    private String checknum;

    private String hosid;

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

    public String getHosid() {
        return hosid;
    }

    public void setHosid(String hosid) {
        this.hosid = hosid == null ? null : hosid.trim();
    }

    public CompareJunior(String id, String checknum, String hosid) {
        this.id = id;
        this.checknum = checknum;
        this.hosid = hosid;
    }

    public CompareJunior() {
    }

    @Override
    public String toString() {
        return "CompareJunior{" +
                "id='" + id + '\'' +
                ", checknum='" + checknum + '\'' +
                ", hosid='" + hosid + '\'' +
                '}';
    }
}