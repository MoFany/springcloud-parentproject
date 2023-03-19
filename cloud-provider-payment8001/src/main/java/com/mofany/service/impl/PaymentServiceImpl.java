package com.mofany.service.impl;

import com.mofany.entities.Payment;
import com.mofany.mapper.PaymentMapper;
import com.mofany.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author MoFany-J
 * @date 2023/2/15
 * @description PaymentServiceImpl
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;
    /**
     * 新增
     *
     * @param payment
     */
    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    /**
     * 按id查Payment
     *
     * @param id
     */
    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
