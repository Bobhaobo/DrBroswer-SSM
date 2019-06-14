package org.springmvc.pojo;

import java.util.Date;

public class OrderTable {
    private String id;

    private String checknum;

    private String arrangestatus;

    private Date adddate;

    private Date orderdate;

    private String ordersource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum == null ? null : checknum.trim();
    }

    public String getArrangestatus() {
        return arrangestatus;
    }

    public void setArrangestatus(String arrangestatus) {
        this.arrangestatus = arrangestatus == null ? null : arrangestatus.trim();
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdersource() {
        return ordersource;
    }

    public void setOrdersource(String ordersource) {
        this.ordersource = ordersource == null ? null : ordersource.trim();
    }

    @Override
    public String toString() {
        return "OrderTable{" +
                "id='" + id + '\'' +
                ", checknum='" + checknum + '\'' +
                ", arrangestatus='" + arrangestatus + '\'' +
                ", adddate=" + adddate +
                ", orderdate=" + orderdate +
                ", ordersource='" + ordersource + '\'' +
                '}';
    }
}