package com.mofany.service;

import com.mofany.entities.CommonResult;
import com.mofany.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author MoFany-J
 * @date 2023/2/19
 * @description PaymentFeignService
 * <p>
 * 服务消费者FeignService接口的方法应与目标服务提供者的XxxService接口方法一致
 */
@Component // 组件声明，这样可以Spring中的组件扫描识别并被Spring容器托管
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //服务调用,指定调用的微服务名
public interface PaymentFeignService {

    /**
     * 新增
     */
    @PostMapping("payment/create")
    CommonResult create(@RequestBody Payment payment);

    /**
     * 按id查Payment
     */
    @GetMapping("payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 服务提供方故意写一个暂停的程序
     */
    @GetMapping("payment/feign/timeout")
    public String paymentFeignTimeout();
}
