package org.springmvc.dto;

import java.util.Date;
import java.util.List;

public class UserListTab {

    private String user_id;
    private String user_name;
    private String user_hos;
    private String user_dept;
    private String user_auth;
    private String user_reg;
    private List<String> user_auth1;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_hos() {
        return user_hos;
    }

    public void setUser_hos(String user_hos) {
        this.user_hos = user_hos;
    }

    public String getUser_dept() {
        return user_dept;
    }

    public void setUser_dept(String user_dept) {
        this.user_dept = user_dept;
    }

    public String getUser_auth() {
        return user_auth;
    }

    public void setUser_auth(String user_auth) {
        this.user_auth = user_auth;
    }

    public List<String> getUser_auth1() {
        return user_auth1;
    }

    public void setUser_auth1(List<String> user_auth1) {
        this.user_auth1 = user_auth1;
    }

    public String getUser_reg() {
        return user_reg;
    }

    public void setUser_reg(String user_reg) {
        this.user_reg = user_reg;
    }


    public UserListTab(String user_id, String user_name, String user_hos, String user_dept, String user_auth, String user_reg,List<String> user_auth1) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_hos = user_hos;
        this.user_dept = user_dept;
        this.user_auth = user_auth;
        this.user_reg = user_reg;
        this.user_auth1 = user_auth1;
    }

    public UserListTab() {
    }

    @Override
    public String toString() {
        return "UserListTab{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_hos='" + user_hos + '\'' +
                ", user_dept='" + user_dept + '\'' +
                ", user_auth='" + user_auth + '\'' +
                ", user_reg='" + user_reg + '\'' +
                '}';
    }

}
