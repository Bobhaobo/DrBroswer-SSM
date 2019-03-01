package org.springmvc.dto;
/**
 * @Description: 已安排的病人信息数据传输对象
 * @Author: Shalldid
 * @Date: Created in 16:01 2018-05-03
 * @Return:
 **/
public class ArrangedDetail {

    private String checkNum;
    private String recordId;
    private String patientId;
    private String registerTime;
    private String patientName;
    private String patientGender;
    private String patientAge;
    private String orderSource;
    private String examItemName;
    private String date;
    private String type;
    private String device;

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

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getExamItemName() {
        return examItemName;
    }

    public void setExamItemName(String examItemName) {
        this.examItemName = examItemName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public ArrangedDetail() {
    }

    public ArrangedDetail(String checkNum, String recordId, String patientId, String registerTime, String patientName, String patientGender, String patientAge, String orderSource, String examItemName, String date, String type, String device) {
        this.checkNum = checkNum;
        this.recordId = recordId;
        this.patientId = patientId;
        this.registerTime = registerTime;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.orderSource = orderSource;
        this.examItemName = examItemName;
        this.date = date;
        this.type = type;
        this.device = device;
    }
}
