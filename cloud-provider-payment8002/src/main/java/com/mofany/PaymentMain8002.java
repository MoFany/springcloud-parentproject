package com.mofany;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author MoFany-J
 * @date 2023/2/14
 * @description PaymentMain8002 支付模块
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = "com.mofany.mapper")
public class PaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }
}
