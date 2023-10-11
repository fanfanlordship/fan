package org.fan.cloud.auth.service;

import org.fan.cloud.auth.entity.Permission;

import java.util.List;

public interface MenusService {

    List<Permission> userMenus(String userId);
}
