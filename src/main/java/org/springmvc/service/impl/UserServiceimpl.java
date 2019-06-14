package org.springmvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.DepartmentMapper;
import org.springmvc.dao.HospitalMapper;
import org.springmvc.dao.UserMapper;
import org.springmvc.dto.UserListTab;
import org.springmvc.enums.UserLevel;
import org.springmvc.pojo.User;
import org.springmvc.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:与用户接口相关实现类
 * @Author:ShallDid
 * */
@Service
public class UserServiceimpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceimpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private HospitalMapper hospitalMapper;

    /**
     * @Description:检查是否存在用户
     * @Author:ShallDid
     * @Parm:username
     * @Parm:password
     * @Return:User
     * */
    @Override
    public User ifExsitUser(String username, String password) {
        User user;  //定义一个User
        if(username != null && password != null){
            try{
                user = userMapper.queryByUserNameAndPwd(username,password);     //查询用户是否存在
                logger.info("user: [" + user.getUsername() + "] login this system");
            }catch (Exception e){
                logger.error("login error info：" + e.getMessage() + ">>>>>>"
                    + "login user: " + username);
                user = null;
            }
        }else{
            logger.error("login error info: " + "username or password is null");
            user = null;
        }
        return user;
    }

    @Override
    public User ifExsitUserByName(String name) {
        User user;  //定义一个User
        if(name != null){
            try{
                user = userMapper.queryByName(name);     //查询用户是否存在
//                logger.info("user: [" + user.getUsername() + "] login this system");
            }catch (Exception e){
//                logger.error("login error info：" + e.getMessage() + ">>>>>>"
//                        + "login user: " + username);
                user = null;
            }
        }else{
//            logger.error("login error info: " + "username or password is null");
            user = null;
        }
        System.out.println(user);
        return user;
    }


    /**
     *@Description: 根据用户所属科室id查询用户属于上级或者下级医院
     *@Author: Shalldid
     *@Date:Created in 16:46 2018-04-18
     **/
    @Override
    public UserLevel getUserLevel(User user) {
        if("0".equals(user.getType())) {
            String hosid = departmentMapper.getHosIdbyDeptid(user.getDept());
            //System.out.println(hosid);//通过科室id查询所属医院id
            String hoslevel = hospitalMapper.getHosLevelbyHosid(hosid); //通过医院id查询所属医院等级
            // System.out.println(hoslevel);
            try {
                int level = Integer.valueOf(hoslevel);
                //System.out.println(level);
                return UserLevel.statOf(level); //根据查询得到的医院等级返回用户所属医院等级枚举类
            } catch (NumberFormatException e) {
                logger.error("用户所属医院等转化错误：" + e.getMessage());
                return UserLevel.ErrorUser;
            }
        }else if("1".equals(user.getType())){
            return UserLevel.Super;
        }else {
            return UserLevel.ErrorUser;
        }
    }
    /**
     * @Description: 得到用户科室所属医院ID
     * @Author: Shalldid
     * @Date: Created in 11:44 2018-04-26
     * @Return: 医院ID
     **/
    @Override
    public String getHosIdOfUser(String deptid){
        return departmentMapper.getHosIdbyDeptid(deptid);
    }
    /**
     * @Description: 根据用户Id返回用户信息
     * @Author: Shalldid
     * @Date: Created in 17:03 2018-05-07
     * @Return:
     **/
    @Override
    public User getUserByUserId(String id){
        return userMapper.queryByUserId(id);
    }
    /**
     * @Description: 返回用户列表信息
     * @Author: Shalldid
     * @Date: Created in 16:06 2018-05-29
     * @Return:
     **/
    @Override
    public List<UserListTab> getUserList() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> list = userMapper.selectAllUser();
        List<UserListTab> userListTabs = new ArrayList<UserListTab>();
        for(User u : list){
            UserListTab userListTab = new UserListTab();
            userListTab.setUser_auth(u.getAuth());
            userListTab.setUser_dept(departmentMapper.getDeptNamebyDeptid(u.getDept()));
            userListTab.setUser_hos(hospitalMapper.getHosNameByHosId(departmentMapper.getHosIdbyDeptid(u.getDept())));
            userListTab.setUser_id(u.getId());
            userListTab.setUser_name(u.getName());
            userListTab.setUser_reg(simpleDateFormat.format(u.getRegtime()));
            userListTabs.add(userListTab);
        }
        return userListTabs;

    }

    @Override
    public int addNewUser(User record){
        return userMapper.insert(record);
    }

    @Override
    public List<User> getAll(){
        return userMapper.selectAllUser1();
    }

    @Override
    public  User getUserByUserName(String name){return  userMapper.selectUserByUserName(name);}

//    @Override
//    public List<String> getAllAuth(){return userMapper.selectAllAuth();}

//    @Override
//    public List<String> getAuthNameByAuthCode1(List<String> authCode1){return userMapper.selectAuthNameByAuthCode1(authCode1);}
}
