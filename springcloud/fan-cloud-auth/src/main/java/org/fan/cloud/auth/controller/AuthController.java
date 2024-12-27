package org.fan.cloud.auth.controller;

import org.fan.cloud.auth.entity.CaptchaDto;
import org.fan.cloud.auth.entity.Menu;
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

    /**
     * 获取验证码
     *
     * @return
     */
    @PostMapping("/captcha")
    public CaptchaDto captcha() {
        return captchaService.getCaptcha();
    }

    /**
     * 登录
     *
     * @param args
     * @return
     */
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody RequestArgs args) {
        return loginMethodService.login(args);
    }

    /**
     * 刷新token
     *
     * @param args
     * @return
     */
    @PostMapping("/refreshToken")
    public Map<String, String> refreshToken(@RequestBody RequestArgs args) {
        return loginMethodService.refreshToken(args.getRefreshToken());
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @PostMapping("/menus")
    public List<Menu> menus() {
        String user = RequestUtil.getUser();
        return menusService.userMenus(user);
    }

    @PostMapping("/menus/data")
    public List<Menu> menusData() {
        return menusService.menus();
    }

    /**
     * 上移菜单
     *
     * @param args
     * @return
     */
    @PostMapping("/menus/move/up")
    public boolean menusMoveUp(@RequestBody RequestArgs args) {
        menusMoveService.moveUp(args.getId());
        return true;
    }

    /**
     * 下移菜单
     *
     * @param args
     * @return
     */
    @PostMapping("/menus/move/down")
    public boolean menusMoveDown(@RequestBody RequestArgs args) {
        menusMoveService.moveDown(args.getId());
        return true;
    }
}
