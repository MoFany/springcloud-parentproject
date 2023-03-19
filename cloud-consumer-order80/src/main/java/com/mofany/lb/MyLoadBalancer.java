package com.mofany.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author MoFany-J
 * @date 2023/2/18
 * @description MyLoadBalancer 自定义LoadBalancer接口
 */
public interface MyLoadBalancer {

    /**
     * @param serviceInstanceList 现在服务器集群上能够提供服务的机器的集合
     * */
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
