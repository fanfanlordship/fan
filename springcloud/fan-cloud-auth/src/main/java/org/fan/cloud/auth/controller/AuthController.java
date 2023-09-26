package org.fan.cloud.auth.controller;

import org.fan.cloud.auth.entity.CaptchaDto;
import org.fan.cloud.auth.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/captcha")
    public CaptchaDto captcha() {
        return captchaService.getCaptcha();
    }
}
