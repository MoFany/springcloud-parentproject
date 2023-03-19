package com.mofany.mapper;

import com.mofany.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author MoFany-J
 * @date 2023/2/15
 * @description PaymentMapper 数据访问接口层
 */
@Mapper
public interface PaymentMapper {
    /**
     * 新增
     * */
    int create(Payment payment);

    /**
     * 按id查Payment
     * */
    Payment getPaymentById(@Param("id") Long id);
}
