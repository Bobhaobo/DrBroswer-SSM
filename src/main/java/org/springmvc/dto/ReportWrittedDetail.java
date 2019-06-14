package org.springmvc.dto;

public class ReportWrittedDetail {
    private String hosName;
    private String deptName;
    private String clinicId;
    private String bedNo;
    private String jcbw;
    private String sfyangxing;
    private String examDesc;
    private String examDiagnosis;

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getJcbw() {
        return jcbw;
    }

    public void setJcbw(String jcbw) {
        this.jcbw = jcbw;
    }

    public String getSfyangxing() {
        return sfyangxing;
    }

    public void setSfyangxing(String sfyangxing) {
        this.sfyangxing = sfyangxing;
    }

    public String getExamDesc() {
        return examDesc;
    }

    public void setExamDesc(String examDesc) {
        this.examDesc = examDesc;
    }

    public String getExamDiagnosis() {
        return examDiagnosis;
    }

    public void setExamDiagnosis(String examDiagnosis) {
        this.examDiagnosis = examDiagnosis;
    }

    public ReportWrittedDetail() {
    }

    public ReportWrittedDetail(String hosName, String deptName, String clinicId, String bedNo, String jcbw,
                               String sfyangxing, String examDesc, String examDiagnosis) {
        this.hosName = hosName;
        this.deptName = deptName;
        this.clinicId = clinicId;
        this.bedNo = bedNo;
        this.jcbw = jcbw;
        this.sfyangxing = sfyangxing;
        this.examDesc = examDesc;
        this.examDiagnosis = examDiagnosis;
    }

    @Override
    public String toString() {
        return "ReportWrittedDetail{" +
                "hosName='" + hosName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", clinicId='" + clinicId + '\'' +
                ", bedNo='" + bedNo + '\'' +
                ", jcbw='" + jcbw + '\'' +
                ", sfyangxing='" + sfyangxing + '\'' +
                ", examDesc='" + examDesc + '\'' +
                ", examDiagnosis='" + examDiagnosis + '\'' +
                '}';
    }
}
