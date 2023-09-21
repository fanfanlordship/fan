package org.fan.cloud.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/9/21 16:14
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientLogApplication.class, args);
    }
}
