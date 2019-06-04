package com.loveincode.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huyifan
 * @date :2019-05-23
 * com.loveincode
 */
@Data
@ApiModel("通用返回体")
public class ResultDTO<T> implements Serializable {
    /**
     * 是否成功
     */
    @ApiModelProperty("是否成功")
    private Boolean succ;

    /**
     * 服务器当前时间戳
     */
    @ApiModelProperty("服务器当前时间戳")
    private Long ts = System.currentTimeMillis();

    @ApiModelProperty("返回数据体")
    private T data;

    /**
     * 错误码
     */
    @ApiModelProperty("错误码")
    private String code;

    /**
     * 错误描述
     */
    @ApiModelProperty("错误描述")
    private String msg;

    public static ResultDTO ofSuccess() {
        ResultDTO resultDto = new ResultDTO();
        resultDto.succ = true;
        return resultDto;
    }

    public static <T> ResultDTO<T> ofSuccess(T data) {
        ResultDTO<T> resultDto = new ResultDTO<>();
        resultDto.succ = true;
        resultDto.setData(data);

        return resultDto;
    }

    public static ResultDTO ofFail(String code, String msg) {
        ResultDTO resultDto = new ResultDTO();
        resultDto.succ = false;
        resultDto.code = code;
        resultDto.msg = msg;

        return resultDto;
    }

    public static <T> ResultDTO<T> ofFail(String code, String msg, T data) {
        ResultDTO<T> resultDto = new ResultDTO();
        resultDto.succ = false;
        resultDto.code = code;
        resultDto.msg = msg;
        resultDto.setData(data);
        return resultDto;
    }

    public ResultDTO() {

    }

    public ResultDTO(Boolean succ, String code) {
        this.succ = succ;
        this.code = code;
    }

    public ResultDTO(Boolean succ, String code, T data) {
        this.data = data;
    }

    public boolean isSucc() {
        return this.succ;
    }

    public boolean isFail() {
        return !this.succ;
    }

}
