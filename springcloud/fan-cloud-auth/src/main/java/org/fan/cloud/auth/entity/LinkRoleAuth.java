package org.fan.cloud.auth.entity;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/10/11 15:33
 */
public class LinkRoleAuth {
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 权限ID
     */
    private String authId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    @Override
    public String toString() {
        return "LinkRoleAuth{" +
                "roleId='" + roleId + '\'' +
                ", authId='" + authId + '\'' +
                '}';
    }
}
