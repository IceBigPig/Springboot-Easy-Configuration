package com.icebigpig.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: icebigpig
 * Data: 2022/7/29 16:09
 * Version 1.0
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获全局异常，处理所有不可知的异常
     */
    @ExceptionHandler(value=Exception.class)
    public ResponseResult handleException(Exception e, HttpServletRequest request) {
        log.error("出现未知异常 -> ", e);
        ResponseError error = new ResponseError();
        error.setCode("APPEAR_ERROR");
        error.setMessage(e.getMessage());
        error.setRequestUrl(request.getRequestURL().toString());
        error.setException(e.getClass().getName());
        return error;
    }

    /**
     * 捕获运行异常
     */
    @ExceptionHandler(value=RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("出现运行异常异常 -> ", e);
        ResponseError error = new ResponseError();
        error.setCode("RUNTIME_EXCEPTION");
        error.setMessage(e.getMessage());
        error.setRequestUrl(request.getRequestURL().toString());
        error.setException(e.getClass().getName());
        return error;
    }

    /**
     * 捕获空指针异常
     */
    @ExceptionHandler(value=NullPointerException.class)
    public ResponseResult handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        log.error("出现空指针异常 -> ", e);
        ResponseError error = new ResponseError();
        error.setCode("NULL_POINTER_ERROR");
        error.setMessage("出现空指针异常");
        error.setRequestUrl(request.getRequestURL().toString());
        error.setException(e.getClass().getName());
        return error;
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(value=BusinessException.class)
    public ResponseResult handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("出现业务异常 -> ", e);
        ResponseError error = new ResponseError();
        error.setCode(e.getCode());
        error.setMessage(e.getMessage());
        error.setRequestUrl(request.getRequestURL().toString());
        error.setException(e.getClass().getName());
        return error;
    }

    /**
     * 处理参数为空异常
     */
    @ExceptionHandler(value = RequestParamIsNullException.class)
    public ResponseResult handleInputParamIsNullException(RequestParamIsNullException e, HttpServletRequest request) {
        log.error("请求参数为空异常 -> ", e);
        ResponseError error = new ResponseError();
        error.setCode(e.getCode());
        error.setMessage(e.getMessage());
        error.setRequestUrl(request.getRequestURL().toString());
        error.setException(e.getClass().getName());
        return error;
    }
}
