package org.fan.cloud.common.config;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author fanfanlordship
 * @Description JWT配置
 * @Date 2023/9/26 14:40
 */
@Component
@ConfigurationProperties("fan.jwt")
public class JwtConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtConfig.class);

    private String header;

    private String secretKey;

    private Integer expire;

    private String ignoreUrl;

    private Set<String> ignoreUrls;

    public Integer getRefreshTokenExpire() {
        // 暂时写死刷新用的token在验证token失效后20分钟内还能使用
        return this.expire + 20;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public String getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(String ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
        setIgnoreUrls();
    }

    private void setIgnoreUrls() {
        this.ignoreUrls = Strings.isNullOrEmpty(this.ignoreUrl) ? Collections.emptySet()
                : Splitter.on(",").splitToStream(this.ignoreUrl).map(String::trim).collect(Collectors.toSet());
        LOGGER.info("Jwt ignore urls：{}", this.ignoreUrls);
    }

    public Set<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    @Override
    public String toString() {
        return "JwtConfig{" +
                "header='" + header + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", expire=" + expire +
                ", ignoreUrl='" + ignoreUrl + '\'' +
                '}';
    }
}
