package org.springmvc.service;

import org.springmvc.pojo.AuthList;
import org.springmvc.pojo.User;

import java.util.List;

public interface AuthService {
    AuthList getAuthByAuthCode(String authCode);
    List<String> getAuthCode();
    String getAuthNameByCode(String authCode);
    List<String> getAuthNameByAuthCode1(List<String> authCode1);
    User getAuthNameByCodeUser(String authCode);
}
