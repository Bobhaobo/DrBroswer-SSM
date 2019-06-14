package org.springmvc.service;

import org.springmvc.dto.UserListTab;
import org.springmvc.enums.UserLevel;
import org.springmvc.pojo.User;

import java.util.List;

/**
 * @Description: 与用户相关接口
 * @Author: ShallDid
 * */
public interface UserService {
    /**
     * @Description: 查询是否存在用户
     * @Parm: username(用户名)
     * @Parm: password(用户密码)
     * @return: 状态，是否存在用户
     * */
    User ifExsitUser(String username, String password);


    User ifExsitUserByName(String name);


    /**
     *@Description: 查询用户等级，属于上级医院或者下级医院
     *@Author: Shalldid
     *@Date: Created in 16:27 2018-04-18
     *@Retrun: 等级,用户等级枚举类型
     **/
    UserLevel getUserLevel(User user);
    /**
     * @Description: 得到用户所属医院ID
     * @Author: Shalldid
     * @Date: Created in 11:42 2018-04-26
     * @Param: deptID,用户所属科室
     * @Return: 医院ID
     **/
    String getHosIdOfUser(String deptid);
    /**
     * @Description: 根据用户ID查找用户
     * @Author: Shalldid
     * @Date: Created in 15:50 2018-05-29
     * @Return:
     **/
    User getUserByUserId(String id);
    /**
     * @Description: 得到用户列表
     * @Author: Shalldid
     * @Date: Created in 15:51 2018-05-29
     * @Return:
     **/
    List<UserListTab> getUserList();

    int addNewUser(User record);

    List<User> getAll();


    /**
     * @Description: 通过用户姓名查找用户id
     * @Author: Shalldid
     * @Date: Created in 15:51 2018-05-29
     * @Return:
     **/

    User getUserByUserName(String name);
//    List<String> getAllAuth();
//    List<String> getAuthNameByAuthCode1(List<String> authCode1);
}
