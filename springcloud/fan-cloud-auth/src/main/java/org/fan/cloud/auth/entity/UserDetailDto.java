package org.fan.cloud.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2024/4/15 15:39
 */
public class UserDetailDto {

    private String username;

    private String name;

    private String mobile;

    private String email;

    @JsonFormat(pattern = "yyyy年MM月dd日HH时mm分", timezone = "GMT+8")
    private Date createTime;

    public void dataFrom(User user) {
        if (user == null) {
            return;
        }
        this.username = user.getUsername();
        this.name = user.getName();
        this.mobile = user.getMobile();
        this.email = user.getEmail();
        this.createTime = user.getCreateTime();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserDetailDto{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
