package org.fan.cloud.auth.service.impl;

import com.google.common.base.Strings;
import org.fan.cloud.auth.dao.FanRoleDao;
import org.fan.cloud.auth.entity.LinkUserRole;
import org.fan.cloud.auth.entity.QueryRole;
import org.fan.cloud.auth.entity.Role;
import org.fan.cloud.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/10/11 15:31
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private FanRoleDao fanRoleDao;

    @Override
    public List<Role> byUser(String userId) {
        LinkUserRole queryLinkUserRole = new LinkUserRole();
        queryLinkUserRole.setUserId(userId);
        List<LinkUserRole> linkUserRoles = fanRoleDao.queryLinkUserRole(queryLinkUserRole);
        if (CollectionUtils.isEmpty(linkUserRoles)) {
            return Collections.emptyList();
        }

        List<String> roleIds = linkUserRoles.stream().map(LinkUserRole::getRoleId).filter(Strings::isNullOrEmpty).collect(Collectors.toList());
        QueryRole queryRole = new QueryRole();
        queryRole.setRoleIds(roleIds);
        return fanRoleDao.queryRole(queryRole);
    }
}
