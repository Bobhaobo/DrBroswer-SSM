package org.springmvc.pojo;

public class RemoteImage {
    private String accessionn;

    private String patientid;

    private String startdate;

    private String ordersource;

    public String getAccessionn() {
        return accessionn;
    }

    public void setAccessionn(String accessionn) {
        this.accessionn = accessionn == null ? null : accessionn.trim();
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid == null ? null : patientid.trim();
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate == null ? null : startdate.trim();
    }

    public String getOrdersource() {
        return ordersource;
    }

    public void setOrdersource(String ordersource) {
        this.ordersource = ordersource == null ? null : ordersource.trim();
    }
}