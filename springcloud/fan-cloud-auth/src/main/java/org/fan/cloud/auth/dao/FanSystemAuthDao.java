package org.fan.cloud.auth.dao;

import org.fan.cloud.auth.entity.DatabaseArgs;
import org.fan.cloud.auth.entity.Menu;
import org.fan.cloud.auth.entity.QueryRoleAuth;
import org.fan.cloud.common.entity.Association;

import java.util.List;

public interface FanSystemAuthDao {

    List<Menu> queryByArgs(DatabaseArgs query);

    List<Menu> queryBrother(DatabaseArgs query);

    List<Association> queryLinkRoleAuth(QueryRoleAuth queryRoleAuth);

    int updateByPrimary(Menu menu);
}
