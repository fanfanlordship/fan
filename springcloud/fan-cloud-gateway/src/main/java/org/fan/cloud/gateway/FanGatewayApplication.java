package org.fan.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fanfanlordship
 * @Description 网关
 * @Date 2023/9/21 16:14
 */
@SpringBootApplication
@EnableFeignClients
public class FanGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanGatewayApplication.class, args);
    }
}
