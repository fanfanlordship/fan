package org.fan.cloud.common.exception;

/**
 * @author fanfanlordship
 * @Description 异常信息
 * @Date 2023/9/22 10:25
 */
public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }
}
