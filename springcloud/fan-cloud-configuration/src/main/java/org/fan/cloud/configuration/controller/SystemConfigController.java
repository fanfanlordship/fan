package org.fan.cloud.configuration.controller;

import org.fan.cloud.configuration.entity.SystemConfig;
import org.fan.cloud.configuration.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanfanlordship
 * @Description 配置接口
 * @Date 2023/9/22 15:41
 */
@RestController
@RequestMapping("/configuration")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    @PostMapping("/config/byCode")
    public SystemConfig captchaSize(@RequestBody String code) {
        return systemConfigService.byCode(code);
    }
}
