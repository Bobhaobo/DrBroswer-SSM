package org.springmvc.pojo;

import java.util.Date;
import java.util.List;

public class User {
    private String username;

    private String password;

    private String id;

    private String name;

    private String dept;

    private String type;

    private Date regtime;

    private String auth;

    private List<String> auth1;

    private String main_page;

    private AuthList authList;

    public AuthList getAuthList() {
        return authList;
    }

    public void setAuthList(AuthList authList) {
        this.authList = authList;
    }

    public String getMain_page() {
        return main_page;
    }

    public void setMain_page(String main_page) {
        this.main_page = main_page;
    }

    public String getAuth() {
        return auth;
    }

    public List<String> getAuth1() {
        return auth1;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public User(String username, String password, String id, String name, String dept, String type, Date regtime, String auth, String main_page) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.type = type;
        this.regtime = regtime;
        this.auth = auth;
        this.main_page = main_page;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", type='" + type + '\'' +
                ", regtime=" + regtime +
                ", auth='" + auth + '\'' +
                '}';
    }
}