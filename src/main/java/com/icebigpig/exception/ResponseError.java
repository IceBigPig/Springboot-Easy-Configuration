package com.icebigpig.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: icebigpig
 * Data: 2022/7/29 16:10
 * Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError extends ResponseResult{

    /**
     * 请求地址（发生异常时返回）
     */
    private String requestUrl;

    /**
     * 异常类（发生异常时返回）
     */
    private String exception;
}
