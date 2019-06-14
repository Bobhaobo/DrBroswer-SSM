package org.springmvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springmvc.dto.UserListTab;
import org.springmvc.pojo.User;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryByUserNameAndPwd(@Param("username") String username,@Param("password") String password);

    User queryByUserId(String userid);

    List<User> selectAllUser();

    List<User> selectAllUser1();

    User selectUserByUserName(String name);

    User queryByName(String name);

    List<User> selectUserByDept(String dept);

//    List<String> selectAllAuth();

//    List<String> selectAuthNameByAuthCode1(List<String> authCode1);

}