package org.fan.cloud.auth.dao;

import org.fan.cloud.auth.entity.User;

import java.util.List;

public interface FanUserDao {

    List<User> queryByParam(User user);
}
