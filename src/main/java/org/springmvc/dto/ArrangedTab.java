package org.springmvc.dto;
/**
 * @Description: arranged_info_j.html的数据传输对象
 * @Author: Shalldid
 * @Date: Created in 14:56 2018-05-03
 * @Return:
 **/
public class ArrangedTab {

    private String check_number;
    private String patient_Name;
    private String patient_Sex;
    private String patient_Age;
    private String modality;
    private String checkHos;
    private String arranged_Date;
    private String recordId;
    private String checkHosId;

    public String getCheck_number() {
        return check_number;
    }

    public void setCheck_number(String check_number) {
        this.check_number = check_number;
    }

    public String getPatient_Name() {
        return patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        this.patient_Name = patient_Name;
    }

    public String getPatient_Sex() {
        return patient_Sex;
    }

    public void setPatient_Sex(String patient_Sex) {
        this.patient_Sex = patient_Sex;
    }

    public String getPatient_Age() {
        return patient_Age;
    }

    public void setPatient_Age(String patient_Age) {
        this.patient_Age = patient_Age;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getCheckHos() {
        return checkHos;
    }

    public void setCheckHos(String checkHos) {
        this.checkHos = checkHos;
    }

    public String getArranged_Date() {
        return arranged_Date;
    }

    public void setArranged_Date(String arranged_Date) {
        this.arranged_Date = arranged_Date;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getCheckHosId() {
        return checkHosId;
    }

    public void setCheckHosId(String checkHosId) {
        this.checkHosId = checkHosId;
    }

    @Override
    public String toString() {
        return "ArrangedTab{" +
                "check_number='" + check_number + '\'' +
                ", patient_Name='" + patient_Name + '\'' +
                ", patient_Sex='" + patient_Sex + '\'' +
                ", patient_Age='" + patient_Age + '\'' +
                ", modality='" + modality + '\'' +
                ", checkHos='" + checkHos + '\'' +
                ", arranged_Date='" + arranged_Date + '\'' +
                ", recordId='" + recordId + '\'' +
                ", checkHosId='" + checkHosId + '\'' +
                '}';
    }
}
