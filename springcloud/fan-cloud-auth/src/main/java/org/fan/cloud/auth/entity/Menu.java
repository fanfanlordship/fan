package org.fan.cloud.auth.entity;

import org.fan.cloud.common.entity.tree.TreeNodeSort;

/**
 * @author fanfanlordship
 * @Description 菜单
 * @Date 2023/9/27 18:23
 */
public class Menu extends TreeNodeSort {

    private String name;

    private String code;

    private String path;

    private String router;

    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", path='" + path + '\'' +
                ", router='" + router + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
