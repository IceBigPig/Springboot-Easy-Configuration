package com.icebigpig.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 的路由拦截器，自定义认证规则
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler)->{
            // 根据路由划分模块，不同模块不同鉴权
            SaRouter.match(SaHttpMethod.OPTIONS).match("/**").check(r -> System.out.println("Option请求，放行"));
//            SaRouter.match("/**").check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/init/**")
                .excludePathPatterns("/verify/**")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/distribution/get-score-by-rank/**");

        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/init/**")
                .excludePathPatterns("/verify/**")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/distribution/get-score-by-rank/**");
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
                            .setHeader("Access-Control-Allow-Origin", "http://localhost:3000")
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