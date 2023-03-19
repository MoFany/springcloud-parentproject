package com.mofany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author MoFany-J
 * @date 2023/2/16
 * @description PaymentMain8004
 */
@EnableDiscoveryClient // 该注解用于向使用consul或zookeeper作为注册中心时提供注册服务
@SpringBootApplication
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}
