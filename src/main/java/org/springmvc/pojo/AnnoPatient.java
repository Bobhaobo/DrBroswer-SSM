package org.springmvc.pojo;

import java.util.Date;

public class AnnoPatient {
    private String patientId;

    private String patientName;

    private Date patientBirth;

    private String patientSex;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public Date getPatientBirth() {
        return patientBirth;
    }

    public void setPatientBirth(Date patientBirth) {
        this.patientBirth = patientBirth;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex == null ? null : patientSex.trim();
    }

    @Override
    public String toString() {
        return "AnnoPatient{" +
                "patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientBirth=" + patientBirth +
                ", patientSex='" + patientSex + '\'' +
                '}';
    }

    public AnnoPatient(String patientId, String patientName, Date patientBirth, String patientSex) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientBirth = patientBirth;
        this.patientSex = patientSex;
    }

    public AnnoPatient() {
    }
}