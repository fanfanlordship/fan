package org.fan.cloud.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "fan-cloud-nacos-configuration", configuration = FeignClientsConfiguration.class)
public interface ConfigurationFeign {

    @PostMapping("/configuration/config/byCode")
    Map<String, Object> captchaSize(String code);
}
