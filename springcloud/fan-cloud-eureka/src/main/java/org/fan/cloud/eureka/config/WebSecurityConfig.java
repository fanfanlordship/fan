package org.fan.cloud.eureka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/9/21 16:22
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf 否则注册中心能启动，但是服务无法注册进来
        http.csrf().disable();
        // 这一句必须要加上的，否则直接关闭密码验证服务了
        super.configure(http);
    }
}
