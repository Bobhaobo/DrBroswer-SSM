package org.springmvc.dto;

public class JuniorCheckedDto {
    private String checkNum;
    private String patName;
    private String patGender;
    private String patient_Age;
    private String examItemName;
    private String hosName;
    private String checkDate;
    private String imagePath;

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

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public JuniorCheckedDto(String checkNum, String patName, String patGender, String patient_Age, String examItemName, String hosName, String checkDate, String imagePath) {
        this.checkNum = checkNum;
        this.patName = patName;
        this.patGender = patGender;
        this.patient_Age = patient_Age;
        this.examItemName = examItemName;
        this.hosName = hosName;
        this.checkDate = checkDate;
        this.imagePath = imagePath;
    }

    public JuniorCheckedDto() {
    }

    @Override
    public String toString() {
        return "JuniorCheckedDto{" +
                "checkNum='" + checkNum + '\'' +
                ", patName='" + patName + '\'' +
                ", patGender='" + patGender + '\'' +
                ", patient_Age='" + patient_Age + '\'' +
                ", examItemName='" + examItemName + '\'' +
                ", hosName='" + hosName + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
