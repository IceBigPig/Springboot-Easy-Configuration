package com.icebigpig.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.icebigpig.entity.vo.Result;
import com.icebigpig.enums.StatusCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@Slf4j
@Api(tags = "账户登录接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/test")
    public Result<StatusCodeEnum> testFunction() {
        return Result.ok();
    }

    @PostMapping("/username")
    @ApiOperation(value = "通过账号登录")
    public Result<StatusCodeEnum> doLoginByUsername(String username, String password) {
        if (Objects.equals(username, "123") && Objects.equals(password, "123")) {
            StpUtil.login(1);
        } else {
            Result.fail();
        }
        return Result.ok();
    }

}
