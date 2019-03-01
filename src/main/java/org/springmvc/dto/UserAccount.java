package org.springmvc.dto;

public class UserAccount {
    private String id;
    private String name;
    private String hos;

    private String mainPageId;

    private String mainPageUrl;
    private String mainPageTitle;

    public String getMainPageId() {
        return mainPageId;
    }

    public void setMainPageId(String mainPageId) {
        this.mainPageId = mainPageId;
    }

    public String getMainPageUrl() {
        return mainPageUrl;
    }

    public void setMainPageUrl(String mainPageUrl) {
        this.mainPageUrl = mainPageUrl;
    }

    public String getMainPageTitle() {
        return mainPageTitle;
    }

    public void setMainPageTitle(String mainPageTitle) {
        this.mainPageTitle = mainPageTitle;
    }

    public String getHos() {
        return hos;
    }

    public void setHos(String hos) {
        this.hos = hos;
    }

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

    @Override
    public String toString() {
        return "UserAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hos='" + hos + '\'' +
                ", mainPageId='" + mainPageId + '\'' +
                ", mainPageUrl='" + mainPageUrl + '\'' +
                ", mainPageTitle='" + mainPageTitle + '\'' +
                '}';
    }
}
