package com.loveincode.common;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huyifan
 * @date :2019-06-04
 * com.loveincode.common
 */
@Data
@ApiModel("hello 请求")
public class HelloRequest {

    @ApiModelProperty(required = true, value = "说话")
    @NotBlank(message = "资源名称必须输入")
    private String say;

    @ApiModelProperty(required = true, value = "id")
    @NotNull(message = "id必须输入")
    private Long id;
}
