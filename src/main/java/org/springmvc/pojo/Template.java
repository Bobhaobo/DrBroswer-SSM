package org.springmvc.pojo;

public class Template {
    private String templateId;

    private String parentId;

    private String title;

    private Integer templateLevel;

    private String examitemname;

    private String templateCode;

    private String exammethod;

    private String examdesc;

    private String examdiagnosis;

    private String rootId;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getTemplateLevel() {
        return templateLevel;
    }

    public void setTemplateLevel(Integer templateLevel) {
        this.templateLevel = templateLevel;
    }

    public String getExamitemname() {
        return examitemname;
    }

    public void setExamitemname(String examitemname) {
        this.examitemname = examitemname == null ? null : examitemname.trim();
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    public String getExammethod() {
        return exammethod;
    }

    public void setExammethod(String exammethod) {
        this.exammethod = exammethod == null ? null : exammethod.trim();
    }

    public String getExamdesc() {
        return examdesc;
    }

    public void setExamdesc(String examdesc) {
        this.examdesc = examdesc == null ? null : examdesc.trim();
    }

    public String getExamdiagnosis() {
        return examdiagnosis;
    }

    public void setExamdiagnosis(String examdiagnosis) {
        this.examdiagnosis = examdiagnosis == null ? null : examdiagnosis.trim();
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId == null ? null : rootId.trim();
    }

    @Override
    public String toString(){
        return "template_id : " + templateId + "     ;  parent_id : " + parentId;
    }
}