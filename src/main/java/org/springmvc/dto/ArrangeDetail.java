package org.springmvc.dto;
/**
 * @Description: 返回arrange_reservation_m.jsp数据传输对象
 * @Author: Shalldid
 * @Date: Created in 17:29 2018-05-02
 * @Return:
 **/
public class ArrangeDetail {

    private String checkNum;
    private String recordId;
    private String patientId;
    private String registerTime;
    private String patientName;
    private String patientGender;
    private String patientAge;
    private String examItemName;
    private String examItemCode;
    private String orderDate;
    private String orderSource;

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getExamItemName() {
        return examItemName;
    }

    public void setExamItemName(String examItemName) {
        this.examItemName = examItemName;
    }

    public String getExamItemCode() {
        return examItemCode;
    }

    public void setExamItemCode(String examItemCode) {
        this.examItemCode = examItemCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public ArrangeDetail() {
    }

    public ArrangeDetail(String checkNum, String recordId, String patientId, String registerTime, String patientName, String patientGender, String patientAge, String examItemName, String examItemCode, String orderDate, String orderSource) {
        this.checkNum = checkNum;
        this.recordId = recordId;
        this.patientId = patientId;
        this.registerTime = registerTime;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.examItemName = examItemName;
        this.examItemCode = examItemCode;
        this.orderDate = orderDate;
        this.orderSource = orderSource;
    }
}
