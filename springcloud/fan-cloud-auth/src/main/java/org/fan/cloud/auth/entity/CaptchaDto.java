package org.fan.cloud.auth.entity;

/**
 * @author fanfanlordship
 * @Description 验证码响应
 * @Date 2023/9/22 10:25
 */
public class CaptchaDto {
    /**
     * 键
     */
    private String key;
    /**
     * 验证码（base64）
     */
    private String captcha;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "CaptchaDto{" +
                "key='" + key + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
