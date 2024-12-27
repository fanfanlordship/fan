package org.fan.cloud.auth.service.impl;

import com.google.common.base.Strings;
import org.fan.cloud.auth.dao.FanSystemAuthDao;
import org.fan.cloud.auth.entity.*;
import org.fan.cloud.auth.service.MenusService;
import org.fan.cloud.auth.service.RoleService;
import org.fan.cloud.common.entity.Association;
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
    public List<Menu> menus() {
        List<Menu> menus = fanSystemAuthDao.queryByArgs(null);
        return TreeUtils.toSortTree(menus);
    }

    @Override
    public List<Menu> userMenus(String userId) {
        // 查询用户的角色
        List<Role> roles = roleService.byUser(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptyList();
        }

        List<String> roleIds = roles.stream().map(Role::getId).filter(Strings::isNullOrEmpty).distinct().collect(Collectors.toList());

        // 查询角色拥有的权限菜单
        QueryRoleAuth queryRoleAuth = new QueryRoleAuth();
        queryRoleAuth.setRoleIds(roleIds);
        List<Association> associations = fanSystemAuthDao.queryLinkRoleAuth(queryRoleAuth);
        if (CollectionUtils.isEmpty(associations)) {
            return Collections.emptyList();
        }

        List<String> authId = associations.stream().map(Association::getSecondary).filter(Strings::isNullOrEmpty).distinct().collect(Collectors.toList());
        DatabaseArgs databaseArgs = new DatabaseArgs();
        databaseArgs.setIds(authId);
        databaseArgs.setEnabled("1");
        List<Menu> menus = fanSystemAuthDao.queryByArgs(databaseArgs);

        return TreeUtils.toSortTree(menus);
    }
}
