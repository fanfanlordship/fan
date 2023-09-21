package org.fan.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableEurekaServer
@EnableWebSecurity
@SpringBootApplication
public class EurekaServerApplickation {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplickation.class, args);
    }
}
