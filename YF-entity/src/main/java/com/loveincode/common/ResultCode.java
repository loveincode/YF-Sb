package com.loveincode.common;

import lombok.Getter;

/**
 * @author huyifan
 * @date :2019-06-04
 * com.loveincode.common
 */
@Getter
public enum  ResultCode {
    /**
     * 系统异常
     */
    EXCEPTION("YF500","服务器开小差，请稍后再试"),
    /**
     * 参数错误
     */
    PARAM_ERROR("YF100", "参数错误"),
    /**
     * 参数错误
     */
    METHOD_ERROR("YF200", "方法错误"),
    /**
     * 业务异常
     */
    BUSINESS_ERROR("YF400","业务异常");

    private String code;

    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
