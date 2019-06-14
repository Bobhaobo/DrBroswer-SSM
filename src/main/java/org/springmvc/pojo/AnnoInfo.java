package org.springmvc.pojo;

public class AnnoInfo {
    private String annoId;

    private String patientId;

    private String studyInstanceUid;

    private String seriesInstanceUid;

    private String sopInstanceUid;

    private Integer x1Left;

    private Integer y1Left;

    private Integer x2Right;

    private Integer y2Right;

    private Integer width;

    private Integer height;

    private Integer depth;

    private String ueserId;

    private Double volume;

    private Double diameter;

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

    public Integer getX1Left() {
        return x1Left;
    }

    public void setX1Left(Integer x1Left) {
        this.x1Left = x1Left;
    }

    public Integer getY1Left() {
        return y1Left;
    }

    public void setY1Left(Integer y1Left) {
        this.y1Left = y1Left;
    }

    public Integer getX2Right() {
        return x2Right;
    }

    public void setX2Right(Integer x2Right) {
        this.x2Right = x2Right;
    }

    public Integer getY2Right() {
        return y2Right;
    }

    public void setY2Right(Integer y2Right) {
        this.y2Right = y2Right;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getUeserId() {
        return ueserId;
    }

    public void setUeserId(String ueserId) {
        this.ueserId = ueserId == null ? null : ueserId.trim();
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
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
}