package org.fan.cloud.auth.dao;

import org.fan.cloud.auth.entity.LinkUserRole;
import org.fan.cloud.auth.entity.QueryRole;
import org.fan.cloud.auth.entity.Role;

import java.util.List;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/10/11 15:31
 */
public interface FanRoleDao {

    List<Role> queryRole(QueryRole queryRole);

    List<LinkUserRole> queryLinkUserRole(LinkUserRole linkUserRole);
}
