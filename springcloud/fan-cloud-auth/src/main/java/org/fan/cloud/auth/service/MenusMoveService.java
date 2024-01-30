package org.fan.cloud.auth.service;

/**
 * @author fanfanlordship
 * @Description 移动菜单顺序
 */
public interface MenusMoveService {

    void moveUp(String id);

    void moveDown(String id);
}
