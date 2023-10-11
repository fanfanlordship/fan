package org.fan.cloud.auth.entity;

import org.fan.cloud.common.entity.tree.TreeNodeSort;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/9/27 18:23
 */
public class Permission extends TreeNodeSort {

    private String name;

    private String router;

    private String icon;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", router='" + router + '\'' +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
