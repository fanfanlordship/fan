package org.fan.cloud.configuration.service.impl;

import org.fan.cloud.configuration.dao.SystemConfigDao;
import org.fan.cloud.configuration.entity.SystemConfig;
import org.fan.cloud.configuration.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigDao systemConfigDao;

    @Override
    public SystemConfig byCode(String code) {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setCode(code);
        List<SystemConfig> systemConfigs = systemConfigDao.queryByParam(systemConfig);
        return CollectionUtils.isEmpty(systemConfigs) ? null : systemConfigs.get(0);
    }
}
