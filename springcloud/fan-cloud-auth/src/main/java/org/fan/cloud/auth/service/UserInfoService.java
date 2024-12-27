package org.fan.cloud.auth.service;

import org.fan.cloud.auth.entity.UserDetailDto;

public interface UserInfoService {

    UserDetailDto info(String userId);
}
