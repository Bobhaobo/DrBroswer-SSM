package org.springmvc.pojo;

public class AnnoInfo {
    private String annoId;

    private String patientId;

    private String studyInstanceUid;

    private String seriesInstanceUid;

    private String sopInstanceUid;

    private Float x1Left;

    private Float y1Left;

    private Float x2Right;

    private Float y2Right;

    private String ueserId;

    private Float volume;

    private Float diameter;

    private String form;

    private String part;

    private String density;

    public String getAnnoId() {
        return annoId;
    }

    public void setAnnoId(String annoId) {
        this.annoId = annoId == null ? null : annoId.trim();
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

    public String getSeriesInstanceUid() {
        return seriesInstanceUid;
    }

    public void setSeriesInstanceUid(String seriesInstanceUid) {
        this.seriesInstanceUid = seriesInstanceUid == null ? null : seriesInstanceUid.trim();
    }

    public String getSopInstanceUid() {
        return sopInstanceUid;
    }

    public void setSopInstanceUid(String sopInstanceUid) {
        this.sopInstanceUid = sopInstanceUid == null ? null : sopInstanceUid.trim();
    }

    public Float getX1Left() {
        return x1Left;
    }

    public void setX1Left(Float x1Left) {
        this.x1Left = x1Left;
    }

    public Float getY1Left() {
        return y1Left;
    }

    public void setY1Left(Float y1Left) {
        this.y1Left = y1Left;
    }

    public Float getX2Right() {
        return x2Right;
    }

    public void setX2Right(Float x2Right) {
        this.x2Right = x2Right;
    }

    public Float getY2Right() {
        return y2Right;
    }

    public void setY2Right(Float y2Right) {
        this.y2Right = y2Right;
    }

    public String getUeserId() {
        return ueserId;
    }

    public void setUeserId(String ueserId) {
        this.ueserId = ueserId == null ? null : ueserId.trim();
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getDiameter() {
        return diameter;
    }

    public void setDiameter(Float diameter) {
        this.diameter = diameter;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form == null ? null : form.trim();
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part == null ? null : part.trim();
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density == null ? null : density.trim();
    }

    @Override
    public String toString() {
        return "AnnoInfo{" +
                "annoId='" + annoId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", studyInstanceUid='" + studyInstanceUid + '\'' +
                ", seriesInstanceUid='" + seriesInstanceUid + '\'' +
                ", sopInstanceUid='" + sopInstanceUid + '\'' +
                ", x1Left=" + x1Left +
                ", y1Left=" + y1Left +
                ", x2Right=" + x2Right +
                ", y2Right=" + y2Right +
                ", ueserId='" + ueserId + '\'' +
                ", volume=" + volume +
                ", diameter=" + diameter +
                ", form='" + form + '\'' +
                ", part='" + part + '\'' +
                ", density='" + density + '\'' +
                '}';
    }
}