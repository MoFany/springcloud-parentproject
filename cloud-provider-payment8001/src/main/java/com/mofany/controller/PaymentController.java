package com.mofany.controller;

import com.mofany.entities.CommonResult;
import com.mofany.entities.Payment;
import com.mofany.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author MoFany-J
 * @date 2023/2/15
 * @description PaymentController 支付模块对外的controller
 */
@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    /**
     * 读取全局配置的属性
     */
    @Value("${server.port}")
    private String serverPort;

    /***
     * 通过服务发现读取服务信息
     * */
    @Resource
    private DiscoveryClient discoveryClient;


    /**
     * 插入数据
     * 测试接口：http://localhost:8001/payment/create
     * {
     * "code": 200,
     * "message": "插入数据成功",
     * "data": 1
     * }
     */
    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("********插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功，serverPort：" + serverPort, result);
        }
        return new CommonResult(444, "插入数据失败", null);
    }

    /**
     * 查询
     * 测试接口：http://localhost:8001/payment/get/1
     * {
     * "code": 200,
     * "message": "查询成功",
     * "data": {
     * "id": 1,
     * "serial": "笑傲江湖"
     * }
     * }
     */
    @GetMapping("get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("********插入结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功，serverPort：" + serverPort, payment);
        }
        return new CommonResult(444, "无对应记录，查询ID：" + id, null);
    }

    /**
     * 通过服务发现来查看本服务的信息
     */
    @GetMapping("discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*******element:" + element);
        }
        /**
         * 获取指定service集群中的服务器列表
         * */
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }


    /**
     * 测试自定义轮询算法
     */
    @GetMapping(value = "lb")
    public String getPaymentLB() {
        return serverPort;
    }

    /**
     * 服务提供方故意写一个暂停的程序
     */
    @GetMapping("feign/timeout")
    public String paymentFeignTimeout() {
        try {
            /**
             * 故意停3秒中
             * */
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
