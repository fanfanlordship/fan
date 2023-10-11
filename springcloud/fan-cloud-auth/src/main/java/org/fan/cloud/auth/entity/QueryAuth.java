package org.fan.cloud.auth.entity;

import java.util.List;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/10/11 15:43
 */
public class QueryAuth {

    private List<String> authIds;

    private Integer enabled;

    public List<String> getAuthIds() {
        return authIds;
    }

    public void setAuthIds(List<String> authIds) {
        this.authIds = authIds;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "QueryAuth{" +
                "authIds=" + authIds +
                ", enabled=" + enabled +
                '}';
    }
}
