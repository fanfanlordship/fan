package org.fan.cloud.auth.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.fan.cloud.auth.dao.FanUserDao;
import org.fan.cloud.auth.entity.RequestArgs;
import org.fan.cloud.auth.entity.User;
import org.fan.cloud.auth.service.LoginMethodService;
import org.fan.cloud.auth.util.JWTUtil;
import org.fan.cloud.common.config.JwtConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginMethodServiceImpl implements LoginMethodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginMethodServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FanUserDao fanUserDao;
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Map<String, String> login(RequestArgs args) {
        String username = args.getUsername();
        String password = args.getPassword();
        String captcha = args.getCaptcha();
        String captchaKey = args.getCaptchaKey();

        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();

        // 验证验证码
        String captchaTrueKey = String.format("cache:manage:identity:token:%s", captchaKey);
        String captchaTrue = stringStringValueOperations.get(captchaTrueKey);
        redisTemplate.delete(captchaTrueKey);
        if (Strings.isNullOrEmpty(captchaTrue)) {
            // 验证码过期或者失效
            LOGGER.info("验证码过期或者失效了");
            return null;
        }
        if (!Objects.equals(captchaTrue.toLowerCase(), captcha.toLowerCase())) {
            // 验证码不正确
            LOGGER.warn("验证码不正确");
            return null;
        }

        // 查询用户信息
        User user = new User();
        user.setUsername(username);
        List<User> users = fanUserDao.queryByParam(user);

        if (CollectionUtils.isEmpty(users)) {
            return null;
        }

        User loginUser = users.get(0);

        boolean matches = passwordEncoder.matches(password, loginUser.getPassword());
        if (!matches) {
            return null;
        }

        Map<String, Object> info = Maps.newHashMap();
        info.put("userId", loginUser.getUserId());

        return buildTokens(info);
    }

    @Override
    public Map<String, String> refreshToken(String refreshToken) {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        String accessToken = stringStringValueOperations.getAndDelete("refreshToken:" + refreshToken);
        if (Strings.isNullOrEmpty(accessToken)) {
            return null;
        }
        Map<String, Object> info = JWTUtil.getInfo(accessToken);
        LOGGER.info("刷新token，{}", info);
        if (info == null) {
            return null;
        }

        return buildTokens(info);
    }

    private Map<String, String> buildTokens(Map<String, Object> info) {
        String accessToken = JWTUtil.createToken(info);
        String refreshToken = JWTUtil.createRefreshToken();

        // refreshToken和accessToken的关系存到redis中，后面使用refreshToken换出新的accessToken
        redisTemplate.opsForValue().set("refreshToken:" + refreshToken, accessToken, jwtConfig.getRefreshTokenExpire(), TimeUnit.MINUTES);

        Map<String, String> map = Maps.newHashMap();
        map.put("accessToken", accessToken);
        map.put("refreshToken", refreshToken);
        return map;
    }
}
