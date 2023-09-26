package org.fan.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fanfanlordship
 * @Description 权限相关服务
 * @Date 2023/9/21 16:14
 */
@EnableConfigurationProperties
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "org.fan.cloud.*")
public class FanAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanAuthApplication.class, args);
    }
}
