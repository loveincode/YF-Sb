package com.loveincode.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author huyifan
 * @date :2019-06-04
 * com.loveincode.config.swagger
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    private boolean isOpen = true;

    /**
     * 通过 createRestApi函数来构建一个DocketBean
     * 函数名,可以随意命名,喜欢什么命名就什么命名
     *
     * @ApiError ：发生错误返回的信息
     * - @Api()用于类； 描述Controller的作用
     * 表示标识这个类是swagger的资源
     * - @ApiOperation()用于方法；  描述一个类的一个方法，或者说一个接口
     * 表示一个http请求的操作
     * - @ApiParam()用于方法，参数，字段说明；单个参数描述
     * 表示对参数的添加元数据（说明或是否必填等）
     * - @ApiModel()用于类
     * 表示对类进行说明，用于参数用实体类接收
     * - @ApiModelProperty()用于方法，字段
     * 表示对model属性的说明或者数据操作更改
     * - @ApiIgnore()用于类，方法，方法参数
     * 表示这个方法或者类被忽略
     * - @ApiImplicitParam() 用于方法 个请求参数
     * 表示单独的请求参数
     * - @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam 多个请求参数
     * @ApiResponse：HTTP响应其中1个描述
     * @ApiResponses：HTTP响应整体描述
     * ---------------------
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .enable(isOpen)
            //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
            .apiInfo(apiInfo())
            .select()
            //控制暴露出去的路径下的实例
            //如果某个接口不想暴露,可以使用以下注解
            //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
            .apis(RequestHandlerSelectors.basePackage("com.loveincode"))
            .paths(PathSelectors.any())
            .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            //页面标题
            .title("Swagger2 API document")
            //版本号
            .version("1.0")
            //描述
            .description("API 描述")
            .build();
    }
}