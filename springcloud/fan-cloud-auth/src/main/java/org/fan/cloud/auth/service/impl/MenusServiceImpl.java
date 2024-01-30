package org.fan.cloud.auth.service.impl;

import com.google.common.base.Strings;
import org.fan.cloud.auth.dao.FanSystemAuthDao;
import org.fan.cloud.auth.entity.*;
import org.fan.cloud.auth.service.MenusService;
import org.fan.cloud.auth.service.RoleService;
import org.fan.cloud.common.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenusServiceImpl implements MenusService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private FanSystemAuthDao fanSystemAuthDao;

    @Override
    public List<Permission> menus() {
        List<Permission> permissions = fanSystemAuthDao.queryAuth(null);
        return TreeUtils.toSortTree(permissions);
    }

    @Override
    public List<Permission> userMenus(String userId) {
        // 查询用户的角色
        List<Role> roles = roleService.byUser(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptyList();
        }

        List<String> roleIds = roles.stream().map(Role::getId).filter(Strings::isNullOrEmpty).distinct().collect(Collectors.toList());

        // 查询角色拥有的权限菜单
        QueryRoleAuth queryRoleAuth = new QueryRoleAuth();
        queryRoleAuth.setRoleIds(roleIds);
        List<LinkRoleAuth> linkRoleAuths = fanSystemAuthDao.queryLinkRoleAuth(queryRoleAuth);
        if (CollectionUtils.isEmpty(linkRoleAuths)) {
            return Collections.emptyList();
        }

        List<String> authId = linkRoleAuths.stream().map(LinkRoleAuth::getAuthId).filter(Strings::isNullOrEmpty).distinct().collect(Collectors.toList());
        QueryAuth queryAuth = new QueryAuth();
        queryAuth.setAuthIds(authId);
        queryAuth.setEnabled(1);
        List<Permission> permissions = fanSystemAuthDao.queryAuth(queryAuth);

        return TreeUtils.toSortTree(permissions);
    }
}
