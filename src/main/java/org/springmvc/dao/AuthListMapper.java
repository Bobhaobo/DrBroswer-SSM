package org.springmvc.dao;

import org.springmvc.pojo.AuthList;
import org.springmvc.pojo.User;

import java.util.List;

public interface AuthListMapper {
    int deleteByPrimaryKey(String id);

    int insert(AuthList record);

    int insertSelective(AuthList record);

    AuthList selectByPrimaryKey(AuthList id);

    int updateByPrimaryKeySelective(AuthList record);

    int updateByPrimaryKey(AuthList record);

    AuthList selectByauthCode(String authCode);

    List<String> selectAllAuthCode();

    String selectNameByAuthCode(String authCode);

//    List<String> selectNameByAuthCode1(List<String> authCode1);

    List<String> selectAuthNameByAuthCode1(List<String> authCode1);

    User selectAuthNameByCodeUser(String authCode);
}