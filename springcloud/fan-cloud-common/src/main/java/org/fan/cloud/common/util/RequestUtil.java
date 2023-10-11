package org.fan.cloud.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/9/27 17:19
 */
public class RequestUtil {

    public static String getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginUser = request.getHeader("LOGIN_USER");
        System.out.println(loginUser);
        return loginUser;
    }
}
