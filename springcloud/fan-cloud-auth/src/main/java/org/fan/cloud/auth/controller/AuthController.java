package org.fan.cloud.auth.controller;

import org.fan.cloud.auth.entity.CaptchaDto;
import org.fan.cloud.auth.entity.Permission;
import org.fan.cloud.auth.entity.RequestArgs;
import org.fan.cloud.auth.service.CaptchaService;
import org.fan.cloud.auth.service.LoginMethodService;
import org.fan.cloud.auth.service.MenusMoveService;
import org.fan.cloud.auth.service.MenusService;
import org.fan.cloud.common.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author fanfanlordship
 * @Description 鉴权接口
 * @Date 2023/9/22 10:25
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private LoginMethodService loginMethodService;
    @Autowired
    private MenusService menusService;
    @Autowired
    private MenusMoveService menusMoveService;

    @PostMapping("/captcha")
    public CaptchaDto captcha() {
        return captchaService.getCaptcha();
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody RequestArgs args) {
        return loginMethodService.login(args);
    }

    @PostMapping("/refreshToken")
    public Map<String, String> refreshToken(@RequestBody RequestArgs args) {
        return loginMethodService.refreshToken(args.getRefreshToken());
    }

    @PostMapping("/menus")
    public List<Permission> menus() {
        String user = RequestUtil.getUser();
        return menusService.userMenus(user);
    }

    @PostMapping("/menus/data")
    public List<Permission> menusData() {
        return menusService.menus();
    }

    @PostMapping("/menus/move/up")
    public List<Permission> menusMoveUp(@RequestBody RequestArgs args) {
        return menusMoveService.moveUp(args.get);
    }
}
