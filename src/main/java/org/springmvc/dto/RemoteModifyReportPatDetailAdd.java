package org.springmvc.dto;

public class RemoteModifyReportPatDetailAdd {
    private String hosName;
    private String deptName;
    private String clinicId;
    private String bedNo;
    private String jcbw;
    private String sfyangxing;
    private String examDesc;
    private String examDiagnosis;
    private String imagePath;
    private String suggestion;

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "RemoteModifyReportPatDetailAdd{" +
                "hosName='" + hosName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", clinicId='" + clinicId + '\'' +
                ", bedNo='" + bedNo + '\'' +
                ", jcbw='" + jcbw + '\'' +
                ", sfyangxing='" + sfyangxing + '\'' +
                ", examDesc='" + examDesc + '\'' +
                ", examDiagnosis='" + examDiagnosis + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", suggestion='" + suggestion + '\'' +
                '}';
    }
}
