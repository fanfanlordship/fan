package org.fan.cloud.configuration.dao;

import org.fan.cloud.configuration.entity.SystemConfig;

import java.util.List;

public interface SystemConfigDao {

    List<SystemConfig> queryByParam(SystemConfig systemConfig);
}
