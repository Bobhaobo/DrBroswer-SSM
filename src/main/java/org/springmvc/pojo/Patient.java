package org.springmvc.pojo;

import java.util.Date;

public class Patient {
    private String idcard;

    private String patname;

    private String patnamepy;

    private String patgender;

    private Date patbrithdate;

    private String address;

    private String yibaoid;

    private String telephone;

    private Integer age;

    private String agetype;

    private Date regtime;

    private Date updatetime;

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname == null ? null : patname.trim();
    }

    public String getPatnamepy() {
        return patnamepy;
    }

    public void setPatnamepy(String patnamepy) {
        this.patnamepy = patnamepy == null ? null : patnamepy.trim();
    }

    public String getPatgender() {
        return patgender;
    }

    public void setPatgender(String patgender) {
        this.patgender = patgender == null ? null : patgender.trim();
    }

    public Date getPatbrithdate() {
        return patbrithdate;
    }

    public void setPatbrithdate(Date patbrithdate) {
        this.patbrithdate = patbrithdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getYibaoid() {
        return yibaoid;
    }

    public void setYibaoid(String yibaoid) {
        this.yibaoid = yibaoid == null ? null : yibaoid.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAgetype() {
        return agetype;
    }

    public void setAgetype(String agetype) {
        this.agetype = agetype == null ? null : agetype.trim();
    }

    public Patient() {

    }

    public Patient(String idcard, String patname, String patgender, String address, String yibaoid, String telephone, Integer age, String agetype) {
        this.idcard = idcard;
        this.patname = patname;
        this.patgender = patgender;
        this.address = address;
        this.yibaoid = yibaoid;
        this.telephone = telephone;
        this.age = age;
        this.agetype = agetype;
    }

    public Patient(String idcard, String patname, String patnamepy, String patgender, Date patbrithdate, String address, String yibaoid, String telephone, Integer age, String agetype, Date regtime, Date updatetime) {
        this.idcard = idcard;
        this.patname = patname;
        this.patnamepy = patnamepy;
        this.patgender = patgender;
        this.patbrithdate = patbrithdate;
        this.address = address;
        this.yibaoid = yibaoid;
        this.telephone = telephone;
        this.age = age;
        this.agetype = agetype;
        this.regtime = regtime;
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "idcard='" + idcard + '\'' +
                ", patname='" + patname + '\'' +
                ", patnamepy='" + patnamepy + '\'' +
                ", patgender='" + patgender + '\'' +
                ", patbrithdate=" + patbrithdate +
                ", address='" + address + '\'' +
                ", yibaoid='" + yibaoid + '\'' +
                ", telephone='" + telephone + '\'' +
                ", age=" + age +
                ", agetype='" + agetype + '\'' +
                ", regtime=" + regtime +
                ", updatetime=" + updatetime +
                '}';
    }

    /**
     *@Description: 更新除了身份证号以外的病人信息
     *@Author: Shalldid
     *@Date:Created in 15:12 2018-04-24
     **/
    public void update(Patient patient){
        if(patient.getAddress() != null){
            this.address = patient.getAddress();
        }
        if(patient.getAge() != null){
            this.age = patient.getAge();
        }
        if(patient.getAgetype() != null){
            this.agetype = patient.getAgetype();
        }
        if(patient.getPatbrithdate() != null){
            this.patbrithdate = patient.getPatbrithdate();
        }
        if(patient.getPatgender() != null){
            this.patgender = patient.getPatgender();
        }
        if(patient.getPatname() != null){
            this.patname = patient.getPatname();
        }
        if(patient.getPatnamepy() != null){
            this.patnamepy = patient.getPatnamepy();
        }
        if(patient.getTelephone() != null){
            this.telephone = patient.getTelephone();
        }
        if(patient.getYibaoid() != null ){
            this.yibaoid = patient.getYibaoid();
        }
    }
}