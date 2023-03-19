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
 * @description OrderConsulController
 */
@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderConsulController {

    /**
     * 通过微服务的名字去请求
     */
    private static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    /**
     *
     */
    @GetMapping("payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return result;
    }
}
