package org.fan.cloud.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fanfanlordship
 * @Description 配置服务启动类
 * @Date 2023/9/22 15:42
 */
@MapperScan(basePackages = "org.fan.cloud.configuration.dao")
@SpringBootApplication
public class FanConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanConfigurationApplication.class, args);
    }
}
