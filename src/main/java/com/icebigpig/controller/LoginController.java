package com.icebigpig.controller;

import com.icebigpig.entity.vo.Result;
import com.icebigpig.enums.StatusCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Api(tags = "账户登录接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/username")
    @ApiOperation(value = "通过账号登录")
    public Result<StatusCodeEnum> doLoginByUsername() {
        return Result.ok();
    }

}
