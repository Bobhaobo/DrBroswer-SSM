package org.springmvc.dto;

public class DeviceInfo {
    private String id;
    private String deviceName;
    private String deviceDscr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDscr() {
        return deviceDscr;
    }

    public void setDeviceDscr(String deviceDscr) {
        this.deviceDscr = deviceDscr;
    }

    public DeviceInfo(String id, String deviceName, String deviceDscr) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceDscr = deviceDscr;
    }
}
