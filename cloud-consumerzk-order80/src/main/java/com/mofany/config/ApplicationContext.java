package com.mofany.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author MoFany-J
 * @date 2023/2/17
 * @description ApplicationContext
 */
@Configuration
public class ApplicationContext {
    @Bean
    @LoadBalanced //启用负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
