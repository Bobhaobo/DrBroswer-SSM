package org.springmvc.pojo;

public class AnnoImage {
    private String sopInstanceUid;

    private String patientId;

    private String studyInstanceUid;

    private String seriesInstanceUid;

    private String annoFlag;

    private String imageFolder;

    private String imageFilename;

    private String imagePath;

    public String getSopInstanceUid() {
        return sopInstanceUid;
    }

    public void setSopInstanceUid(String sopInstanceUid) {
        this.sopInstanceUid = sopInstanceUid == null ? null : sopInstanceUid.trim();
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

    public String getAnnoFlag() {
        return annoFlag;
    }

    public void setAnnoFlag(String annoFlag) {
        this.annoFlag = annoFlag == null ? null : annoFlag.trim();
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder == null ? null : imageFolder.trim();
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename == null ? null : imageFilename.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    @Override
    public String toString() {
        return "AnnoImage{" +
                "sopInstanceUid='" + sopInstanceUid + '\'' +
                ", patientId='" + patientId + '\'' +
                ", studyInstanceUid='" + studyInstanceUid + '\'' +
                ", seriesInstanceUid='" + seriesInstanceUid + '\'' +
                ", annoFlag='" + annoFlag + '\'' +
                ", imageFolder='" + imageFolder + '\'' +
                ", imageFilename='" + imageFilename + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}