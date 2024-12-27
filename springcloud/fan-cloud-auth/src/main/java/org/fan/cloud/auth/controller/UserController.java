package org.fan.cloud.auth.controller;

import org.fan.cloud.auth.entity.UserDetailDto;
import org.fan.cloud.auth.service.UserInfoService;
import org.fan.cloud.common.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanfanlordship
 * @Description 用户相关接口
 * @Date 2023/10/14 2:26
 */
@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @PostMapping("/info")
    public UserDetailDto userInfo() {
        return userInfoService.info(RequestUtil.getUser());
    }
}
