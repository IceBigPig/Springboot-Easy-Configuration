package com.icebigpig.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: icebigpig
 * Data: 2022/5/27 14:52
 * Version 1.0
 **/

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 拦截器打开注解鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器打开注解鉴权功能
        registry.addInterceptor(new SaInterceptor(handle -> {
            // SaManager.getLog().debug("----- 请求path={}  提交token={}", SaHolder.getRequest().getRequestPath(), StpUtil.getTokenValue());

            // 指定一条 match 规则
            SaRouter
                    .match("/user/**")    // 拦截的 path 列表，可以写多个
                    .notMatch("/login/username", "/user/doLogin2")     // 排除掉的 path 列表，可以写多个
                    .check(r -> StpUtil.checkLogin());        // 要执行的校验动作，可以写完整的 lambda 表达式

            // 权限校验 -- 不同模块认证不同权限
            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
            SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
            SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
            SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
            SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));

            // 甚至你可以随意的写一个打印语句
            SaRouter.match("/router/print", r -> System.out.println("----啦啦啦----"));

            // 写一个完整的 lambda
            SaRouter.match("/router/print2", r -> {
                System.out.println("----啦啦啦2----");
                // ... 其它代码
            });

            /*
             * 相关路由都定义在 com.pj.cases.use.RouterCheckController 中
             */

        })).addPathPatterns("/**");
    }

    /**
     * 注册 [Sa-Token全局过滤器]
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**").addExclude("/favicon.ico")

                .setAuth(obj -> {
                    // 全局认证函数...
                })

                .setError(Throwable::getMessage)

                .setBeforeAuth(obj -> {
                    // ---------- 设置跨域响应头 ----------
                    SaHolder.getResponse()
                            .setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5173")
                            .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                            .setHeader("Access-Control-Max-Age", "3600")
                            .setHeader("Access-Control-Allow-Headers", "token,Content-Type")
                            .setHeader("Access-Control-Allow-Credentials", "true");
                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            .free(r -> System.out.println("--------OPTIONS预检请求，不做处理"))
                            .back();
                });
    }
}