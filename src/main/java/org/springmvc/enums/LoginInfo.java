package org.springmvc.enums;

/**
 *@Description: 用户登陆时返回信息枚举类
 *@Author: Shalldid
 *@Date:Created in 15:54 2018-04-20
 **/
public enum LoginInfo {

    Major(1,"返回上级医院"),
    Junior(2,"返回下级医院"),
    Super(5,"返回超级用户"),
    User_Error(0,"用户信息错误"),
    Server_Error(4,"服务器异常"),
    AuthCode_Error(3,"验证码错误");

    private int index;
    private String marks;

    /**
     *@Description: 枚举类构造方法
     *@Author: Shalldid
     *@Date:Created in 9:35 2018-04-23
     **/
    LoginInfo(int index, String marks){
        this.index = index;
        this.marks = marks;
    }

    public int getIndex() {
        return index;
    }

    public String getMarks() {
        return marks;
    }

}
