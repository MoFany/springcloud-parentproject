package com.mofany.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MoFany-J
 * @date 2023/2/15
 * @description GlobalWebConfig 全局web配置
 */
@Configuration
public class GlobalWebConfig implements WebMvcConfigurer {
    /**
     * 跨域放行
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*");
    }
}
