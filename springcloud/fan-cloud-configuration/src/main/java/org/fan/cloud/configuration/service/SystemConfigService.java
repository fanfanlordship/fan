package org.fan.cloud.configuration.service;

import org.fan.cloud.configuration.entity.SystemConfig;

public interface SystemConfigService {

    SystemConfig byCode(String code);
}
