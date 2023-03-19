package com.mofany.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author MoFany-J
 * @date 2023/2/19
 * @description PaymentService
 */
@Service
public class PaymentService {
    /**
     * 正常访问一定OK的方法
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_OK,id：" + id + "\t" + "O(n_n)O哈哈";
    }


    /**
     * 超时报错的方法
     *
     * @HystrixCommand注解中指定了当前方法异常时的备用方法，同时还指定了正常响应的时间 3秒内走正常业务逻辑，超过3秒中则出错
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        /**
         * 超过3秒为异常
         * */
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_Timeout,id：" + id + "\t" + "O(n_n)O哈哈" + "耗时" + timeNumber + "秒钟";
    }

    /**
     * 服务降级对应的处理方法
     */
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_TimeoutHandler,id：" + id + "\t" + "发生了服务降级！";
    }

    /**
     * 服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
            commandProperties = {
                    // 是否开启短路器
                    @HystrixProperty(name = "circuitBreaker.enabled",
                            value = "true"),
                    // 请求次数（快照时间窗口，默认10秒）
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",
                            value = "10"),
                    // 时间窗口期（请求总数阈值）
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",
                            value = "10000"),
                    // 失败率达到多少后跳闸（错误百分比阈值，达到60%时，就会打开）
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",
                            value = "60"),
            })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("***** id 不能为负数！");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后再试，id：" + id;
    }
}
