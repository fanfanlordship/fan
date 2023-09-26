package org.fan.cloud.auth.service;

import org.fan.cloud.auth.entity.CaptchaDto;

/**
 * @author fanfanlordship
 * @Description 生成验证码
 */
public interface CaptchaService {

    CaptchaDto getCaptcha();
}
