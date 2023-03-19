package com.mofany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author MoFany-J
 * @date 2023/2/19
 * @description OrderFeignMain80
 */

/**
 * 不论我们使用什么都要激活：@EnableXXX
 * 激活OpenFeign的使用
 */
@EnableFeignClients
@SpringBootApplication
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
