package org.springmvc.dto;

public class RemoteVerifiedReportTab {
    private String id;
    private String checkNum;
    private String patName;
    private String patGender;
    private String patient_Age;
    private String examItemName;
    private String hosName;
    private String registerDate;
    private String docName;
    private String reportDate;
    private String imagePath;
    private String verifyDocName;
    private String verifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatGender() {
        return patGender;
    }

    public void setPatGender(String patGender) {
        this.patGender = patGender;
    }

    public String getPatient_Age() {
        return patient_Age;
    }

    public void setPatient_Age(String patient_Age) {
        this.patient_Age = patient_Age;
    }

    public String getExamItemName() {
        return examItemName;
    }

    public void setExamItemName(String examItemName) {
        this.examItemName = examItemName;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVerifyDocName() {
        return verifyDocName;
    }

    public void setVerifyDocName(String verifyDocName) {
        this.verifyDocName = verifyDocName;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }

    public RemoteVerifiedReportTab(String id, String checkNum, String patName, String patGender, String patient_Age, String examItemName, String hosName, String registerDate, String docName, String reportDate, String imagePath, String verifyDocName, String verifyDate) {
        this.id = id;
        this.checkNum = checkNum;
        this.patName = patName;
        this.patGender = patGender;
        this.patient_Age = patient_Age;
        this.examItemName = examItemName;
        this.hosName = hosName;
        this.registerDate = registerDate;
        this.docName = docName;
        this.reportDate = reportDate;
        this.imagePath = imagePath;
        this.verifyDocName = verifyDocName;
        this.verifyDate = verifyDate;
    }

    public RemoteVerifiedReportTab() {
    }

    @Override
    public String toString() {
        return "RemoteVerifiedReportTab{" +
                "id='" + id + '\'' +
                ", checkNum='" + checkNum + '\'' +
                ", patName='" + patName + '\'' +
                ", patGender='" + patGender + '\'' +
                ", patient_Age='" + patient_Age + '\'' +
                ", examItemName='" + examItemName + '\'' +
                ", hosName='" + hosName + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", docName='" + docName + '\'' +
                ", reportDate='" + reportDate + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", verifyDocName='" + verifyDocName + '\'' +
                ", verifyDate='" + verifyDate + '\'' +
                '}';
    }
}
