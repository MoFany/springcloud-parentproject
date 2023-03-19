package com.mofany.service;

import com.mofany.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author MoFany-J
 * @date 2023/2/15
 * @description PaymentService
 */
public interface PaymentService {
    /**
     * 新增
     * */
    int create(Payment payment);

    /**
     * 按id查Payment
     * */
    Payment getPaymentById(@Param("id") Long id);
}
