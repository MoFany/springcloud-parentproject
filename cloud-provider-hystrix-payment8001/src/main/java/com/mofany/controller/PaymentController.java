package com.mofany.controller;

import com.mofany.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MoFany-J
 * @date 2023/2/19
 * @description PaymentController
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 正常的，无异常的
     */
    @GetMapping("hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("******result" + result);
        return result;
    }

    /**
     * 不正常的，有异常的
     */
    @GetMapping("hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("******result" + result);
        return result;
    }

    //==>服务熔断
    @GetMapping("circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        String result=paymentService.paymentCircuitBreaker(id);
        log.info("************result:"+result);
        return result;
    }
}
