package com.mofany.controller;

import com.mofany.entities.CommonResult;
import com.mofany.entities.Payment;
import com.mofany.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author MoFany-J
 * @date 2023/2/15
 * @description OrderController 客户端controller模拟向服务端发请求
 */
@Slf4j
@RestController
@RequestMapping("consumer/payment")
public class OrderController {

    /**
     * 集群版，URL为微服务名称
     */
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    /**
     * 不同端口的微服务之间通信
     */
    @Resource
    private RestTemplate restTemplate;

    /**
     * 自定义负载均衡依赖注入
     */
    @Resource
    private MyLoadBalancer myLoadBalancer;
    /**
     * 获取客户端
     */
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 客户端向服务端发新增请求
     */
    @PostMapping("create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        // 返回时二次发请求
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    /**
     * 客户端向服务端发获取请求
     *
     * @return
     */
    @GetMapping("get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        // 返回时二次发请求
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("getEntity/{id}")
    public CommonResult<Payment> getEntity(@PathVariable("id") Long id) {
        // 返回时二次发请求
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    /**
     * 测试自定义负载均衡器
     */
    @GetMapping("lb")
    public String getPaymentLB() {
        /**
         * 获取指定service集群中的服务器列表
         * */
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        // 排除无效的服务
        if (serviceInstanceList == null || serviceInstanceList.size() <= 0) {
            return null;
        }
        //获取有效的服务
        ServiceInstance serviceInstance = myLoadBalancer.instances(serviceInstanceList);
        //获取当前服务的URI
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
