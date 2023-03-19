package com.mofany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author MoFany-J
 * @date 2023/2/21
 * @description OrderHystrixMain80
 */
@SpringBootApplication
@EnableFeignClients // 激活Feign
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class);
    }
}
