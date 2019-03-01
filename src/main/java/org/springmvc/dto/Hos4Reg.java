package org.springmvc.dto;
/**
 * @Description: 登记病人信息时，可申请医院的数据结构
 * @Author: Shalldid
 * @Date: Created in 17:24 2018-04-27
 * @Return:
 **/
public class Hos4Reg {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hos4Reg() {
    }

    public Hos4Reg(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
