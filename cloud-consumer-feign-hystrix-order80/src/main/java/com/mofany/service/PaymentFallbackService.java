package com.mofany.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author MoFany-J
 * @date 2023/2/23
 * @description PaymentFallbackService
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    /**
     * 正常的，无异常的
     *
     * @param id
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fall 服务降级1！";
    }

    /**
     * 不正常的，有异常的
     *
     * @param id
     */
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService fall 服务降级2！";
    }
}
