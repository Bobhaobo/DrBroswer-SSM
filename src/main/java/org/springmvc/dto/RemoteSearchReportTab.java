package org.springmvc.dto;

public class RemoteSearchReportTab {

    private String check_number;
    private String patient_Name;
    private String patient_Sex;
    private String patient_Age;
    private String pat_type;
    private String modality;
    private String report_Date;

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

    public String getPat_type() {
        return pat_type;
    }

    public void setPat_type(String pat_type) {
        this.pat_type = pat_type;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getReport_Date() {
        return report_Date;
    }

    public void setReport_Date(String report_Date) {
        this.report_Date = report_Date;
    }

    public RemoteSearchReportTab() {
    }

    public RemoteSearchReportTab(String check_number, String patient_Name, String patient_Sex, String patient_Age, String pat_type, String modality, String report_Date) {
        this.check_number = check_number;
        this.patient_Name = patient_Name;
        this.patient_Sex = patient_Sex;
        this.patient_Age = patient_Age;
        this.pat_type = pat_type;
        this.modality = modality;
        this.report_Date = report_Date;
    }
}
