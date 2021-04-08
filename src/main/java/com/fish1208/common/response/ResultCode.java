package com.fish1208.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author : Vinson
 * @date : 2020/8/11 10:23 上午
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements com.fish1208.common.response.IResultCode {
    /**
     * 200 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 400 业务异常
     */
    FAILURE(400, "业务异常"),

    /**
     * 401 请求未授权
     */
    UN_AUTHORIZED(401, "请求未授权"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(404, "没找到请求"),

    /**
     * 不允许使用的方法
     */
    METHOD_NOT_ALLOWED(405, "不允许使用方法"),
    /**
     * 504 网关未能及时响应
     */
    GATEWAY_TIMEOUT(504, "网关服务请求超时"),
    /**
     * 503 服务暂时不可用
     */
    SERVICE_UNAVAILABLE(503, "服务暂时不可用"),
    /**
     * 500 服务器内部异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部异常"),

    /**
     * 服务访问失败
     */
    CONNECTION_REFUSED(601, "服务访问失败"),

    /**
     * 服务异常
     */
    CONNECTION_EXCEPTION(602, "服务异常"),

    /**
     * token 异常
     */
    TOKEN_ERROR(603, "Invalid token"),

    /**
     * token 过期
     */
    TOKEN_EXPIRED(606, "Token expired"),

    /**
     * 登录超时
     */
    LOGIN_OVERTIME(607,"登录超时,请重新登录..."),
    /**
     * 授权异常
     */
    AUTHORIZATION_EXCEPTION(608,"授权异常"),
    /**
     * 用户已被禁用
     */
    DISABLE_USER(609,"用户已被禁用"),

    /**
     * 服务访问人数过多,请稍后重试
     */
    FLOW_EXCEPTION(604, "服务访问人数过多,请稍后重试..."),

    /**
     * 服务繁忙,请稍后重试
     */
    DEGRADE_EXCEPTION(605, "服务繁忙,请稍后重试...");

    final int code;
    final String message;

    public static com.fish1208.common.response.ResultCode valueOf(int statusCode) {
        com.fish1208.common.response.ResultCode series = resolve(statusCode);
        if (series == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return series;
        }
    }

    public static com.fish1208.common.response.ResultCode resolve(int statusCode) {
        com.fish1208.common.response.ResultCode[] temp = values();
        return Arrays.stream(temp).filter(s -> s.getCode() == statusCode).findFirst().orElse(com.fish1208.common.response.ResultCode.INTERNAL_SERVER_ERROR);
    }
}
