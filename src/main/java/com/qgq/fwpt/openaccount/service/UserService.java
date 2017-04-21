package com.qgq.fwpt.openaccount.service;

import com.qgq.fwpt.openaccount.dao.UserLoginMapper;
import com.qgq.fwpt.openaccount.entity.UserLogin;
import com.qgq.fwpt.openaccount.entity.UserLoginExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 2017/04/07
 *
 * @author 繁华
 */
@Service
public class UserService {

    @Resource
    private UserLoginMapper userLoginMapper;

    public UserLogin login(UserLogin userLogin) {
        UserLoginExample userLoginExample = new UserLoginExample();
        userLoginExample.createCriteria()
                .andNumberEqualTo(userLogin.getNumber())
                .andPasswordEqualTo(userLogin.getPassword())
                .andResourceEqualTo(userLogin.getResource());
        List<UserLogin> userLogins = userLoginMapper.selectByExample(userLoginExample);
        if (userLogins.size() == 0) {
            return null;
        }
        return userLogins.get(0);
    }

    public void insertUser(UserLogin userLogin) {
        userLoginMapper.insertSelective(userLogin);
    }

    public void del(Integer openId,Integer resource) {
        UserLoginExample userLoginExample = new UserLoginExample();
        userLoginExample.createCriteria()
                .andOpenIdEqualTo(openId)
                .andResourceEqualTo(resource);
        userLoginMapper.deleteByExample(userLoginExample);
    }

    public void update(UserLogin userLogin) {
        userLoginMapper.updateByPrimaryKeySelective(userLogin);
    }
}
