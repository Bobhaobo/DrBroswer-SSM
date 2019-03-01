package org.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.AuthListMapper;
import org.springmvc.pojo.AuthList;
import org.springmvc.pojo.User;
import org.springmvc.service.AuthService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthServiceimpl implements AuthService {

    @Autowired
    private AuthListMapper authListMapper;

    @Override
    public AuthList getAuthByAuthCode(String authCode) {
        return authListMapper.selectByauthCode(authCode);
    }

    @Override
    public List<String> getAuthCode(){return authListMapper.selectAllAuthCode();}

    @Override
    public String getAuthNameByCode(String authCode){return authListMapper.selectNameByAuthCode(authCode);}

    @Override
    public List<String> getAuthNameByAuthCode1(List<String> authCode1){return authListMapper.selectAuthNameByAuthCode1(authCode1);}

    @Override
    public User getAuthNameByCodeUser(String authCode){return authListMapper.selectAuthNameByCodeUser(authCode);}
}
