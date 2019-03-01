package org.springmvc.pojo;

import java.util.List;

public class AuthList {
    private String id;

    private String authName;

    private String authCode;

    private String remarks;

    private String pre_menu;

    private String url;

    private String html_id;

    private String authObject;

    private List<String> authCode1;

    public List<String> getAuthCode1() {
        return authCode1;
    }

    public void setAuthCode1(List<String> authCode1) {
        this.authCode1 = authCode1;
    }

    public String getAuthObject() {
        return authObject;
    }

    public void setAuthObject(String authObject) {
        this.authObject = authObject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPre_menu() {
        return pre_menu;
    }

    public void setPre_menu(String pre_menu) {
        this.pre_menu = pre_menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_id() {
        return html_id;
    }

    public void setHtml_id(String html_id) {
        this.html_id = html_id;
    }

    @Override
    public String toString() {
        return "AuthList{" +
                "id='" + id + '\'' +
                ", authName='" + authName + '\'' +
                ", authCode='" + authCode + '\'' +
                ", remarks='" + remarks + '\'' +
                ", pre_menu='" + pre_menu + '\'' +
                ", url='" + url + '\'' +
                ", html_id='" + html_id + '\'' +
                ", authObject='" + authObject + '\'' +
                '}';
    }
}