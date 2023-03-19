package com.mofany.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author MoFany-J
 * @date 2023/2/15
 * @description ApplicationContextConfig
 */
@Configuration
public class ApplicationContextConfig {
    /**
     * @LoadBalanced注解赋予RestTemplate默认轮询的负载均衡的能力
     * */
//    @LoadBalanced
    @Bean("restTemplate")
    public RestTemplate getResetTemplate() {
        return new RestTemplate();
    }
}
