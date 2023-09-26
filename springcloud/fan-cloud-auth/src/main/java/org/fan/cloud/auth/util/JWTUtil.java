package org.fan.cloud.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.fan.cloud.common.config.JwtConfig;
import org.fan.cloud.common.util.SpringContextUtil;

import java.util.Date;

/**
 * @author fanfanlordship
 * @Description JWT工具
 * @Date 2023/9/26 14:13
 */
public class JWTUtil {

    private JWTUtil() {
    }

    /**
     * 生成签名
     */
    public static String createToken(String username, String roles) {
        JwtConfig jwtConfig = SpringContextUtil.getBean(JwtConfig.class);

        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey()); //算法

        return JWT.create()
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime() + jwtConfig.getExpire()))
                // 保存身份标识
                .withClaim("username", username)
                // 保存权限标识
                .withClaim("roles", roles)
                .sign(algorithm);
    }

    /**
     * 从token获取username
     */
    public static String getUsername(String token) {
        try {
            return JWT.decode(token).getClaim("username").asString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 从token获取roles
     */
    public static String getRoles(String token) {
        try {
            return JWT.decode(token).getClaim("roles").asString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
