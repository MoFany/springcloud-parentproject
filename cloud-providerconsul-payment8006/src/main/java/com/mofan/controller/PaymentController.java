package com.mofan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author MoFany-J
 * @date 2023/2/17
 * @description PaymentController
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("consul")
    public String paymentConsul() {
        return "spring cloud with consul：" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
