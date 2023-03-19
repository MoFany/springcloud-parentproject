package com.mofany.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author MoFany-J
 * @date 2023/2/17
 * @description OrderZKController
 */
@Slf4j
@RestController
@RequestMapping("consumer")
public class OrderZKController {
    public static final String INVOKE_URL = "http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("payment/zookeeperTest")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zookeeperTest", String.class);
        return result;
    }
}
