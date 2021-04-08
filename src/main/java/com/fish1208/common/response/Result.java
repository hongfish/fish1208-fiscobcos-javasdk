package com.fish1208.common.response;


import com.fish1208.common.constant.GlobalConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

//import org.springframework.lang.Nullable;
//import java.util.Optional;
//import cn.hutool.core.util.ObjectUtil;


/**
 * @author : Vinson
 * @date : 2020/8/11 10:22 上午
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
//@ApiModel(description = "返回结构体")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
   // @ApiModelProperty(value = "状态码", required = true)
    private int code;
    //@ApiModelProperty(value = "是否成功", required = true)
    private boolean success;
   // @ApiModelProperty(value = "返回消息", required = true)
    private String msg;
  //  @ApiModelProperty(value = "数据")
    private T data;

    private Result(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private Result(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private Result(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }
    private Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }


    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
/*    public static boolean isSuccess(@Nullable com.fish1208.common.response.Result<?> result) {
        return Optional.ofNullable(result)
                .map(x -> ObjectUtil.equal(ResultCode.SUCCESS.code, x.code))
                .orElse(Boolean.FALSE);
    }*/

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
/*    public static boolean isNotSuccess(@Nullable com.fish1208.common.response.Result<?> result) {
        return !com.fish1208.common.response.Result.isSuccess(result);
    }*/

    /**
     * 返回R
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> data(T data) {
        return data(data, GlobalConstants.DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.code, data, msg);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> data(int code, T data, String msg) {
        return new com.fish1208.common.response.Result<>(code, data, data == null ? GlobalConstants.DEFAULT_NULL_MESSAGE : msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> success(String msg) {
        return new com.fish1208.common.response.Result<>(ResultCode.SUCCESS, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> success(IResultCode resultCode) {
        return new com.fish1208.common.response.Result<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> success(IResultCode resultCode, String msg) {
        return new com.fish1208.common.response.Result<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> fail(String msg) {
        return new com.fish1208.common.response.Result<>(ResultCode.FAILURE, msg);
    }


    /**
     * 返回R
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> fail(int code, String msg) {
        return new com.fish1208.common.response.Result<>(code, null, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> fail(IResultCode resultCode) {
        return new com.fish1208.common.response.Result<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> fail(IResultCode resultCode, String msg) {
        return new com.fish1208.common.response.Result<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param flag 成功状态
     * @return R
     */
    public static <T> com.fish1208.common.response.Result<T> status(boolean flag) {
        return flag ? success(GlobalConstants.DEFAULT_SUCCESS_MESSAGE) : fail(GlobalConstants.DEFAULT_FAILURE_MESSAGE);
    }

}
