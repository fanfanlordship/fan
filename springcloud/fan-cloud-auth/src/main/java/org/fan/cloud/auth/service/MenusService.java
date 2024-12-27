package org.fan.cloud.auth.service;

import org.fan.cloud.auth.entity.Menu;

import java.util.List;


public interface MenusService {

    List<Menu> menus();

    List<Menu> userMenus(String userId);
}
