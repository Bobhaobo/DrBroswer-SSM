package org.springmvc.dto;
/**
 * @Description: 已报告病人列表的数据传输对象
 * @Author: Shalldid
 * @Date: Created in 16:43 2018-05-07
 * @Return:
 **/
public class WrittedTab {
    private String bgCode;
    private String checkNum;
    private String patName;
    private String patGender;
    private String patient_Age;
    private String examItemName;
    private String registerDate;
    private String docName;
    private String reportDate;
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public WrittedTab() {
    }

    public WrittedTab(String bgCode, String checkNum, String patName, String patGender, String patient_Age, String examItemName,
                      String registerDate, String docName, String reportDate) {
        this.bgCode = bgCode;
        this.checkNum = checkNum;
        this.patName = patName;
        this.patGender = patGender;
        this.patient_Age = patient_Age;
        this.examItemName = examItemName;
        this.registerDate = registerDate;
        this.docName = docName;
        this.reportDate = reportDate;
    }

    public String getBgCode() {
        return bgCode;
    }

    public void setBgCode(String bgCode) {
        this.bgCode = bgCode;
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
}
