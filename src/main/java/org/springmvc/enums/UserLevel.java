package org.springmvc.enums;

/**
 *@Description: 使用枚举表示用户等级字典
 *@Author: Shalldid
 *@Date:Created in 16:36 2018-04-18
 **/
public enum UserLevel {

    Major(1,"上级医院用户"),      //定义枚举，上级医院用户
    Junior(2,"下级医院用户"),     //下级医院用户
    Super(3,"超级用户管理员"),
    ErrorUser(0,"用户所属医院信息错误");  //错误用户信息

    private int level;  //用户所属医院等级
    private String description; //用户所属医院信息描述

    UserLevel(int i, String s) {
        this.level = i;
        this.description = s;
    }   //构造方法

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    /**
     *@Description: 根据索引输出医院等级关键字
     *@Author: Shalldid
     *@Date:Created in 9:30 2018-04-19
     **/
    public static UserLevel statOf(int index){
        for(UserLevel ul : values()){   //enum内部方法value(),返回枚举类所有类型数组，即返回UserLevel[]
            //System.out.println(ul.getLevel());
            if(ul.getLevel() == index){
                return ul;
            }
        }
        return ErrorUser;   //若没有对应的医院等级，返回不合法用户
    }
}
