package org.springmvc.pojo;

public class Device {
    private String devcode;

    private String devname;

    private String devdesc;

    private String examitemcode;

    private String ipaddress;

    private String roomcode;

    private String providername;

    private String inputcode;

    public String getDevcode() {
        return devcode;
    }

    public void setDevcode(String devcode) {
        this.devcode = devcode == null ? null : devcode.trim();
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname == null ? null : devname.trim();
    }

    public String getDevdesc() {
        return devdesc;
    }

    public void setDevdesc(String devdesc) {
        this.devdesc = devdesc == null ? null : devdesc.trim();
    }

    public String getExamitemcode() {
        return examitemcode;
    }

    public void setExamitemcode(String examitemcode) {
        this.examitemcode = examitemcode == null ? null : examitemcode.trim();
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    public String getRoomcode() {
        return roomcode;
    }

    public void setRoomcode(String roomcode) {
        this.roomcode = roomcode == null ? null : roomcode.trim();
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername == null ? null : providername.trim();
    }

    public String getInputcode() {
        return inputcode;
    }

    public void setInputcode(String inputcode) {
        this.inputcode = inputcode == null ? null : inputcode.trim();
    }

    @Override
    public String toString(){
        return "examItemCode: " + examitemcode + "   examItemName: " + devdesc;
    }
}