package com.mofan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author MoFany-J
 * @date 2023/2/17
 * @description PaymentMain8006
 */
@EnableDiscoveryClient // 服务的提供者
@SpringBootApplication
public class PaymentMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8006.class, args);
    }
}
