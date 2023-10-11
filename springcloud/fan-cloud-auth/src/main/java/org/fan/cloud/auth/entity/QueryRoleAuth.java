package org.fan.cloud.auth.entity;

import java.util.List;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/10/11 15:43
 */
public class QueryRoleAuth {

    private List<String> roleIds;

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "QueryRole{" +
                "roleIds=" + roleIds +
                '}';
    }
}
