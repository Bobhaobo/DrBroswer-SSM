package org.springmvc.pojo;

import java.util.Date;

public class AnnoStudy {
    private String studyInstanceUid;

    private String patientId;

    private String modality;

    private Date studyDate;

    public String getStudyInstanceUid() {
        return studyInstanceUid;
    }

    public void setStudyInstanceUid(String studyInstanceUid) {
        this.studyInstanceUid = studyInstanceUid == null ? null : studyInstanceUid.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality == null ? null : modality.trim();
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    @Override
    public String toString() {
        return "AnnoStudy{" +
                "studyInstanceUid='" + studyInstanceUid + '\'' +
                ", patientId='" + patientId + '\'' +
                ", modality='" + modality + '\'' +
                ", studyDate=" + studyDate +
                '}';
    }
}