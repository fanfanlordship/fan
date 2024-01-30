package org.fan.cloud.auth.controller;

import org.fan.cloud.common.util.RequestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/10/14 2:26
 */
@RequestMapping("/auth/user")
public class UserController {

    @PostMapping("/info")
    public void userInfo(){
        String user = RequestUtil.getUser();
    }
}
