package com.mofany.controller;

import com.mofany.entities.CommonResult;
import com.mofany.entities.Payment;
import com.mofany.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author MoFany-J
 * @date 2023/2/19
 * @description OrderFeignController
 */
@RestController
@Slf4j
@RequestMapping("consumer/payment")
public class OrderFeignController {
    /**
     * 业务逻辑接口成员
     */
    @Resource
    private PaymentFeignService paymentFeignService;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        return paymentFeignService.create(payment);
    }

    @GetMapping("get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 服务提供方故意写一个暂停的程序
     */
    @GetMapping("feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign-->ribbon，客户端一般默认1s
        return paymentFeignService.paymentFeignTimeout();
    }
}
