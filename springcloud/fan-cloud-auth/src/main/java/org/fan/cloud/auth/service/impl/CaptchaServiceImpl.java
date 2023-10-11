package org.fan.cloud.auth.service.impl;

import org.fan.cloud.auth.entity.CaptchaDto;
import org.fan.cloud.auth.feign.ConfigurationFeign;
import org.fan.cloud.auth.service.CaptchaService;
import org.fan.cloud.auth.util.ImageUtil;
import org.fan.cloud.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author fanfanlordship
 * @Description 验证码服务
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ConfigurationFeign configurationFeign;

    @Override
    public CaptchaDto getCaptcha() {
        // 获取验证码长度
        Map<String, Object> captchasize = configurationFeign.captchaSize("CAPTCHA_SIZE");
        int size = 4;
        if (captchasize != null) {
            Object value = captchasize.getOrDefault("value", "4");
            size = Integer.parseInt(value.toString());
        }
        LOGGER.info("{}", captchasize);

        //生成验证码
        String captcha = StringUtil.buildRandomStr(size);

        // 因为可能存在跨域问题，这里将验证码保存在redis中将验证码生成base64
        String base64 = ImageUtil.encryCaptcha2Base64(captcha);

        String uid = StringUtil.getUid();

        redisTemplate.opsForValue().set(String.format("cache:manage:identity:token:%s", uid), captcha, 5, TimeUnit.MINUTES);

        CaptchaDto captchaDto = new CaptchaDto();
        captchaDto.setKey(uid);
        captchaDto.setCaptcha(base64);

        return captchaDto;
    }
}
