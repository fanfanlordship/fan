package org.fan.cloud.auth.entity;

import java.util.List;

/**
 * @author fanfanlordship
 * @Description 数据库查询类
 * @Date 2024/12/26 00:41
 */
public class DatabaseArgs {

    private List<String> ids;

    private String enabled;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "DatabaseArgs{" +
                "ids=" + ids +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
