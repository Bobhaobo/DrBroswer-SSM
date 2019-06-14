package org.springmvc.dto;

public class RemoteFlowDto {
    private String checknum;
    private String flag;
    private String name;
    private String sex;
    private int age;
    private String remotehos;
    private String pattype;
    private String modality;


    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRemotehos() {
        return remotehos;
    }

    public void setRemotehos(String remotehos) {
        this.remotehos = remotehos;
    }

    public String getPattype() {
        return pattype;
    }

    public void setPattype(String pattype) {
        this.pattype = pattype;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public RemoteFlowDto(String checknum, String flag, String name, String sex, int age, String remotehos, String pattype, String modality) {
        this.checknum = checknum;
        this.flag = flag;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.remotehos = remotehos;
        this.pattype = pattype;
        this.modality = modality;
    }

    public RemoteFlowDto() {
    }

    @Override
    public String toString() {
        return "RemoteFlowDto{" +
                "checknum='" + checknum + '\'' +
                ", flag='" + flag + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", remotehos='" + remotehos + '\'' +
                ", pattype='" + pattype + '\'' +
                ", modality='" + modality + '\'' +
                '}';
    }
}
