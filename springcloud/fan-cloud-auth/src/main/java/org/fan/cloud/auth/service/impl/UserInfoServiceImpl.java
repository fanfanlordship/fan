package org.fan.cloud.auth.service.impl;

import com.google.common.base.Strings;
import org.fan.cloud.auth.dao.FanUserDao;
import org.fan.cloud.auth.entity.User;
import org.fan.cloud.auth.entity.UserDetailDto;
import org.fan.cloud.auth.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private FanUserDao fanUserDao;

    @Override
    public UserDetailDto info(String userId) {
        if (Strings.isNullOrEmpty(userId)) {
            return null;
        }
        User queryUser = new User();
        queryUser.setUserId(userId);
        List<User> users = fanUserDao.queryByParam(queryUser);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        User user = users.get(0);
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.dataFrom(user);
        return userDetailDto;
    }
}
