package org.springmvc.dto;
/**
 * @Description: view_reservation_m.html显示预约列表所用的数据传输对象
 * @Author: Shalldid
 * @Date: Created in 14:45 2018-05-02
 * @Return:
 **/
public class ReservationTab {

    private String check_number;
    private String patient_Name;
    private String patient_Sex;
    private String patient_Age;
    private String orderSource;
    private String register_Date;
    private String order_Date;

    public String getCheck_number() {
        return check_number;
    }

    public void setCheck_number(String check_number) {
        this.check_number = check_number;
    }

    public String getPatient_Name() {
        return patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        this.patient_Name = patient_Name;
    }

    public String getPatient_Sex() {
        return patient_Sex;
    }

    public void setPatient_Sex(String patient_Sex) {
        this.patient_Sex = patient_Sex;
    }

    public String getPatient_Age() {
        return patient_Age;
    }

    public void setPatient_Age(String patient_Age) {
        this.patient_Age = patient_Age;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getRegister_Date() {
        return register_Date;
    }

    public void setRegister_Date(String register_Date) {
        this.register_Date = register_Date;
    }

    public String getOrder_Date() {
        return order_Date;
    }

    public void setOrder_Date(String order_Date) {
        this.order_Date = order_Date;
    }

    @Override
    public String toString(){
        return "checkNum: " + check_number +
                "\n\r patName: " + patient_Name +
                "\n\r patSex: " + patient_Sex +
                "\n\r patAge: " + patient_Age +
                "\n\r orderSource: " + orderSource +
                "\n\r regDate: " + register_Date +
                "\n\r orderDate: " + order_Date;
    }
}
