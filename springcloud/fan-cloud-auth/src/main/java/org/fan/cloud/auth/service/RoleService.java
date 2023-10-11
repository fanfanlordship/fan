package org.fan.cloud.auth.service;

import org.fan.cloud.auth.entity.Role;

import java.util.List;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/10/11 15:31
 */
public interface RoleService {

    List<Role> byUser(String userId);
}
