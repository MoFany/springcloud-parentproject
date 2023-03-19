package com.myrule.packages;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MoFany-J
 * @date 2023/2/18
 * @description MySelfRule
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        /**
         * 从轮询负载均衡切换为随机负载均衡
         * */
        return new RandomRule();
    }
}
