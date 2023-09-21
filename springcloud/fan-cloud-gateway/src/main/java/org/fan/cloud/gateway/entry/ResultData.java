package org.fan.cloud.gateway.entry;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanfanlordship
 * @Description TODO
 * @Date 2023/9/22 1:59
 **/
public final class ResultData implements Serializable {
    /**
     * 判断码
     * 0-成功 1-失败 2-非法操作
     */
    private String code;
    /**
     * 字符串结果
     */
    private String message;
    /**
     *
     */
    private Integer count;
    /**
     * 数据
     */
    private Object data;

    public ResultData() {
    }

    public ResultData(Integer count, List<?> data) {
        code = "0";
        this.count = count;
        this.data = data;
    }

    public ResultData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功，不需要返回数据
     *
     * @return
     */
    public static ResultData success() {
        return success(null);
    }

    /**
     * 请求成功，有返回数据
     *
     * @param data
     * @return
     */
    public static ResultData success(Object data) {
        return new ResultData("200", "成功", data);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static ResultData fail() {
        return new ResultData("400", "请求失败");
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static ResultData fail(String msg) {
        return new ResultData("400", msg);
    }

    public String getCode() {
        return code;
    }

    public ResultData setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public ResultData setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultData setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
