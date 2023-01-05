package com.icebigpig.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 状态码集合
 * Author: icebigpig
 * Data: 2022/3/13 18:03
 * Version 1.0
 ***/

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    /**
     * 成功
     */
    SUCCESS(20000, "操作成功"),

    /**
     * 没有操作权限
     */
    AUTHORIZED(40300, "没有操作权限"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(50000, "系统异常"),

    /**
     * 失败
     */
    FAIL(51000, "操作失败"),

    /**
     * 参数校验失败
     */
    PARAM_ERROR(52000, "参数格式不正确"),

    /**
     * 用户名已存在
     */
    USERNAME_EXIST(52001, "用户名已存在"),

    /**
     * 用户名不存在
     */
    USERNAME_NOT_EXIST(52002, "用户名不存在"),

    /**
     * 手机号未注册
     */
    MOBILE_NOT_EXIST(52003,"手机号码未注册"),

    /**
     * 邮箱不存在
     */
    EMAIL_NOT_EXIST(52004,"邮箱号码不存在"),

    /**
     * 手机号不存在
     */
    MOBILE_EXIST(52005,"手机号码已注册"),

    /**
     * 邮箱不存在
     */
    EMAIL_EXIST(52006,"邮箱号码已注册"),

    /**
     * 图片验证码错误
     */
    VERIFICATION_CODE_ERROR(40001,"图片验证码错误"),

    /**
     * 短信验证码错误
     */
    MOBILE_VERIFICATION_CODE_ERROR(40002,"手机短信验证码错误"),

    /**
     * 邮箱验证码错误
     */
    EMAIL_VERIFICATION_CODE_ERROR(40003,"邮箱验证码错误"),

    /**
     * 账号或密码错误
     */
    LOGIN_ERROR(40300,"账号或密码错误");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

}
