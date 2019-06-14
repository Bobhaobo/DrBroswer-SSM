package org.springmvc.dto;
/**
 * @Description: 已检查的病人信息数据传输对象
 * @Author: Shalldid
 * @Date: Created in 17:55 2018-05-03
 * @Return:
 **/
public class CheckedTab {

    private String checkNum;
    private String imagePath;
    private String patName;
    private String patGender;
    private String patient_Age;
    private String examItemName;
    private String checkDate;
    private String hosName;

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public CheckedTab() {
    }

    public CheckedTab(String checkNum, String imagePath, String patName, String patGender, String patient_Age, String examItemName, String checkDate) {
        this.checkNum = checkNum;
        this.imagePath = imagePath;
        this.patName = patName;
        this.patGender = patGender;
        this.patient_Age = patient_Age;
        this.examItemName = examItemName;
        this.checkDate = checkDate;
    }

    @Override
    public String toString() {
        return "CheckedTab{" +
                "checkNum='" + checkNum + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", patName='" + patName + '\'' +
                ", patGender='" + patGender + '\'' +
                ", patient_Age='" + patient_Age + '\'' +
                ", examItemName='" + examItemName + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", hosName='" + hosName + '\'' +
                '}';
    }
}
