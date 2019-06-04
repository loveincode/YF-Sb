package com.loveincode.config.advice;

import com.loveincode.common.ResultCode;
import com.loveincode.common.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * @author huyifan
 * @date :2019-06-04
 * com.loveincode.config.advice
 */
@Slf4j
@RestControllerAdvice
public class ValidateControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException e:{}", e.getMessage());
        return ResultDTO.ofFail(ResultCode.PARAM_ERROR.getCode(), e.getRootCause().getMessage());
    }

    /**
     *
     *
     * @Valid 被注释的元素是一个对象，需要检查此对象的所有字段值
     * @Null 被注释的元素必须为 null
     * @NotNull 被注释的元素必须不为 null
     * @NotEmpty 集合对象的元素不为0，即集合不为空，也可以用于字符串不为null
     * @NotBlank 只能用于字符串不为null，并且字符串trim()以后length要大于0
     * @AssertTrue 被注释的元素必须为 true
     * @AssertFalse 被注释的元素必须为 false
     * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
     * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
     * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
     * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
     * @Size(max, min)	被注释的元素的大小必须在指定的范围内
     * @Digits (integer, fraction)    被注释的元素必须是一个数字，其值必须在可接受的范围内
     * @Past 被注释的元素必须是一个过去的日期
     * @Future 被注释的元素必须是一个将来的日期
     * @Pattern(value) 被注释的元素必须符合指定的正则表达式
     *
     * @Email 被注释的元素必须是电子邮箱地址
     * @Length(min=, max=)	被注释的字符串的大小必须在指定的范围内
     * @NotEmpty 被注释的字符串的必须非空
     * @Range(min=, max=)	被注释的元素必须在合适的范围内
     * @NotBlank 被注释的字符串的必须非空
     * @URL(protocol=,
     * host=,    port=, 
     * regexp=, flags=)	被注释的字符串必须是一个有效的url
     * @CreditCardNumber
     * 被注释的字符串必须通过Luhn校验算法，
     * 银行卡，信用卡等号码一般都用Luhn
     * 计算合法性
     * @ScriptAssert
     * (lang =, script =, alias =)    要有Java Scripting API 即JSR 223 
     * ("Scripting for the JavaTM Platform")的实现
     * @SafeHtml
     * (whitelistType =,  
     *additionalTags =)    classpath中要有jsoup包
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException e:{}", e.getMessage());
        return getBindResultDTO(e.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultDTO handleMethodArgumentNotValidException(BindException e) {
        log.error("MethodArgumentNotValidException e:{}", e.getMessage());
        return getBindResultDTO(e.getBindingResult());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResultDTO handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException e:{}", e.getMessage());
        return ResultDTO.ofFail(ResultCode.PARAM_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultDTO handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException e:{}", e.getMessage());
        return ResultDTO.ofFail(ResultCode.METHOD_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResultDTO requestMissingServletRequest(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException 缺少必要参数,参数名称为{}", e.getParameterName());
        return ResultDTO.ofFail(ResultCode.PARAM_ERROR.getCode(), "缺少必要参数,参数名称为" + e.getParameterName());
    }

    /**
     * Exception类捕获
     *
     * @param e 异常类
     * @return 前端包装对象
     */
    @ExceptionHandler(value = Exception.class)
    public ResultDTO handler(Exception e) {
        log.error(ResultCode.EXCEPTION.getMessage(), e);
        return ResultDTO.ofFail(ResultCode.EXCEPTION);
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    private ResultDTO getBindResultDTO(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (log.isDebugEnabled()) {
            for (FieldError error : fieldErrors) {
                log.debug(error.getField() + " -> " + error.getDefaultMessage());
            }
        }
        if (fieldErrors.isEmpty()) {
            log.error("validExceptionHandler error fieldErrors is empty");
            ResultDTO.ofFail(ResultCode.EXCEPTION);
        }
        return ResultDTO.ofFail(ResultCode.PARAM_ERROR.getCode(), fieldErrors.get(0).getDefaultMessage());
    }

}
