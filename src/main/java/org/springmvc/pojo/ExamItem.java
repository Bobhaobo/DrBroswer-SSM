package org.springmvc.pojo;

public class ExamItem {
    private String examitemcode;

    private String examitemname;

    private String inputname;

    private String checknumber;

    private Boolean isauto;

    private String prefix;

    public String getExamitemcode() {
        return examitemcode;
    }

    public void setExamitemcode(String examitemcode) {
        this.examitemcode = examitemcode == null ? null : examitemcode.trim();
    }

    public String getExamitemname() {
        return examitemname;
    }

    public void setExamitemname(String examitemname) {
        this.examitemname = examitemname == null ? null : examitemname.trim();
    }

    public String getInputname() {
        return inputname;
    }

    public void setInputname(String inputname) {
        this.inputname = inputname == null ? null : inputname.trim();
    }

    public String getChecknumber() {
        return checknumber;
    }

    public void setChecknumber(String checknumber) {
        this.checknumber = checknumber == null ? null : checknumber.trim();
    }

    public Boolean getIsauto() {
        return isauto;
    }

    public void setIsauto(Boolean isauto) {
        this.isauto = isauto;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    @Override
    public String toString(){
        return "ExamItemCode: " + examitemcode + " ; ExamItemName: " + examitemname;
    }
}