package com.fish1208.common.response;

import java.io.Serializable;

/**
 * @author : Vinson
 * @date : 2020/8/11 10:22 上午
 */
public interface IResultCode extends Serializable {

    /**
     * 获取状态码
     *
     * @return code
     */
    int getCode();

    /**
     * 获取状态信息
     *
     * @return message
     */
    String getMessage();
}
