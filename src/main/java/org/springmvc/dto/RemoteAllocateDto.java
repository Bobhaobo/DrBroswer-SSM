package org.springmvc.dto;

public class RemoteAllocateDto {
    private String patName;
    private String modality;
    private String pattype;
    private String checknum;
    private String patGender;
    private String age;
    private String flag;
    private String ageType;

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getPattype() {
        return pattype;
    }

    public void setPattype(String pattype) {
        this.pattype = pattype;
    }

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum;
    }

    public String getPatGender() {
        return patGender;
    }

    public void setPatGender(String patGender) {
        this.patGender = patGender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAgeType() {
        return ageType;
    }

    public void setAgeType(String ageType) {
        this.ageType = ageType;
    }

    public RemoteAllocateDto(String patName, String modality, String pattype, String checknum, String patGender, String age) {
        this.patName = patName;
        this.modality = modality;
        this.pattype = pattype;
        this.checknum = checknum;
        this.patGender = patGender;
        this.age = age;
    }

    public RemoteAllocateDto() {
    }

    public RemoteAllocateDto(String patName, String modality, String pattype, String checknum, String patGender, String age, String flag, String ageType) {
        this.patName = patName;
        this.modality = modality;
        this.pattype = pattype;
        this.checknum = checknum;
        this.patGender = patGender;
        this.age = age;
        this.flag = flag;
        this.ageType = ageType;
    }

    @Override
    public String toString() {
        return "RemoteAllocateDto{" +
                "patName='" + patName + '\'' +
                ", modality='" + modality + '\'' +
                ", pattype='" + pattype + '\'' +
                ", checknum='" + checknum + '\'' +
                ", patGender='" + patGender + '\'' +
                ", age='" + age + '\'' +
                ", flag='" + flag + '\'' +
                ", ageType='" + ageType + '\'' +
                '}';
    }
}
