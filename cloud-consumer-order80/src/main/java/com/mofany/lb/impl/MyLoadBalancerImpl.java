package com.mofany.lb.impl;

import com.mofany.lb.MyLoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author MoFany-J
 * @date 2023/2/18
 * @description MyLoadBalancerImpl
 */
@Component
public class MyLoadBalancerImpl implements MyLoadBalancer {

    /**
     * 创建原子类整型类成员属性，初始值为0
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 先获取在增加
     */
    public final int getAndIncrement() {
        // 当前值
        int current;
        // 请求访问的次数
        int next;
        do {
            // 获取对象的原子整型属性的值
            current = this.atomicInteger.get();
            // Integer.MAX_VALUE ==> 2147483647
            next = current >= 2147483647 ? 0 : current + 1;
            /**
             * 乐观锁CAS，期望值为：current，新值为：next
             *      compareAndSetInt(this, VALUE, expectedValue, newValue)
             * 如果 VALUE == expectedValue，则以原子方式将VALUE设置为newValue
             * */
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("当前请求访问的次数为：" + next);
        return next;
    }

    /**
     * 初始化的时候，必须指定一个机器总数
     *
     * @param serviceInstanceList
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstanceList) {
        /**
         * 返回服务器下标 = 当前请求次数 % 可达服务集群中微服务总数
         * */
        int index = getAndIncrement() % serviceInstanceList.size();
        // 以下标index为参，获取对应的微服务实例
        return serviceInstanceList.get(index);
    }
}
