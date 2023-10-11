package org.fan.cloud.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.fan.cloud.common.config.JwtConfig;
import org.fan.cloud.common.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fanfanlordship
 * @Description JWT工具
 * @Date 2023/9/26 14:13
 */
public class JWTUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

    private static final String USER_INFO_KEY = "info";

    private JWTUtil() {
    }

    /**
     * 生成签名
     */
    public static String createToken(Map<String, Object> map) {
        JwtConfig jwtConfig = SpringContextUtil.getBean(JwtConfig.class);

        Date now = new Date();
        //算法
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey());

        return JWT.create()
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime() + jwtConfig.getExpire() * 60 * 1000))
                .withClaim(USER_INFO_KEY, map)
                .sign(algorithm);
    }

    /**
     * 生成刷新token，刷新token不用携带任何信息
     *
     * @return
     */
    public static String createRefreshToken() {
        JwtConfig jwtConfig = SpringContextUtil.getBean(JwtConfig.class);

        Date now = new Date();
        //算法
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey());

        return JWT.create()
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime() + jwtConfig.getRefreshTokenExpire() * 60 * 1000))
                .sign(algorithm);
    }

    public static Map<String, Object> getInfo(String token) {
        DecodedJWT decode;
        try {
            decode = JWT.decode(token);
        } catch (JWTDecodeException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return decode.getClaim(USER_INFO_KEY).asMap();
    }
}
