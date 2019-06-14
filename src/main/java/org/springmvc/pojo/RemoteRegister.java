package org.springmvc.pojo;

import java.util.Date;

public class RemoteRegister {
    private String id;

    private String checknum;

    private String tagpatientid;

    private String idcard;

    private String flag;

    private String remotehos;

    private Date regdate;

    private String pattype;

    private String modality;

    private String remarks;

    private Date studydate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum == null ? null : checknum.trim();
    }

    public String getTagpatientid() {
        return tagpatientid;
    }

    public void setTagpatientid(String tagpatientid) {
        this.tagpatientid = tagpatientid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getRemotehos() {
        return remotehos;
    }

    public void setRemotehos(String remotehos) {
        this.remotehos = remotehos == null ? null : remotehos.trim();
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getPattype() {
        return pattype;
    }

    public void setPattype(String pattype) {
        this.pattype = pattype == null ? null : pattype.trim();
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality == null ? null : modality.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getStudydate() {
        return studydate;
    }

    public void setStudydate(Date studydate) {
        this.studydate = studydate;
    }

    @Override
    public String toString() {
        return "RemoteRegister{" +
                "id='" + id + '\'' +
                ", checknum='" + checknum + '\'' +
                ", tagpatientid='" + tagpatientid + '\'' +
                ", idcard='" + idcard + '\'' +
                ", flag='" + flag + '\'' +
                ", remotehos='" + remotehos + '\'' +
                ", regdate=" + regdate +
                ", pattype='" + pattype + '\'' +
                ", modality='" + modality + '\'' +
                ", remarks='" + remarks + '\'' +
                ", studydate=" + studydate +
                '}';
    }
}