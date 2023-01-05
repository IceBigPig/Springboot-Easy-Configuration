package com.icebigpig.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: icebigpig
 * Data: 2022/7/29 16:11
 * Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    public BusinessException(String message){
        this.code = "BUSINESS_ERROR";
        this.message = message;
    }
}
