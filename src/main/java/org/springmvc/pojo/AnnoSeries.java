package org.springmvc.pojo;

import java.util.Date;

public class AnnoSeries {
    private String seriesInstanceUid;

    private String patientId;

    private String studyInstanceUid;

    private Date seriesDate;

    public String getSeriesInstanceUid() {
        return seriesInstanceUid;
    }

    public void setSeriesInstanceUid(String seriesInstanceUid) {
        this.seriesInstanceUid = seriesInstanceUid == null ? null : seriesInstanceUid.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getStudyInstanceUid() {
        return studyInstanceUid;
    }

    public void setStudyInstanceUid(String studyInstanceUid) {
        this.studyInstanceUid = studyInstanceUid == null ? null : studyInstanceUid.trim();
    }

    public Date getSeriesDate() {
        return seriesDate;
    }

    public void setSeriesDate(Date seriesDate) {
        this.seriesDate = seriesDate;
    }

    @Override
    public String toString() {
        return "AnnoSeries{" +
                "seriesInstanceUid='" + seriesInstanceUid + '\'' +
                ", patientId='" + patientId + '\'' +
                ", studyInstanceUid='" + studyInstanceUid + '\'' +
                ", seriesDate=" + seriesDate +
                '}';
    }
}