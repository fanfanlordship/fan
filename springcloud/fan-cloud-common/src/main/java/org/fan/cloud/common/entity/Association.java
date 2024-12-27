package org.fan.cloud.common.entity;

/**
 * @author fanfanlordship
 * @Description 关联
 * @Date 2024/12/26 00:47
 */
public class Association {

    private String primary;

    private String secondary;

    public Association() {
    }

    public Association(String primary, String secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    @Override
    public String toString() {
        return "Association{" +
                "primary='" + primary + '\'' +
                ", secondary='" + secondary + '\'' +
                '}';
    }
}
