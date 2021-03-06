package com.lingyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName PaymentMain8004
 * @Description TODO
 * @Author LingYun
 * @Date 2022/2/28 21:07
 * @Version
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于想使用consul或者zookeeper的注册中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
