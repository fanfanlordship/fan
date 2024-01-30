package org.fan.cloud.auth.service.impl;

import org.fan.cloud.auth.dao.FanUserDao;
import org.fan.cloud.auth.entity.User;
import org.fan.cloud.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FanUserDao fanUserDao;

    @Override
    public void userInfo(String userId) {
        User queryUser = new User();
        queryUser.setUserId(userId);
        List<User> users = fanUserDao.queryByParam(queryUser);

    }
}
