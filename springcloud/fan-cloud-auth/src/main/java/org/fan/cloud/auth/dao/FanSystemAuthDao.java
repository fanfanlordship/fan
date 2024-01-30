package org.fan.cloud.auth.dao;

import org.fan.cloud.auth.entity.LinkRoleAuth;
import org.fan.cloud.auth.entity.Permission;
import org.fan.cloud.auth.entity.QueryAuth;
import org.fan.cloud.auth.entity.QueryRoleAuth;

import java.util.List;

public interface FanSystemAuthDao {

    List<Permission> queryAuth(QueryAuth queryAuth);

    List<Permission> queryBrother(QueryAuth queryAuth);

    List<LinkRoleAuth> queryLinkRoleAuth(QueryRoleAuth queryRoleAuth);
}
