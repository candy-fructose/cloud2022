package com.lingyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName OrderZKMain80
 * @Description TODO
 * @Author LingYun
 * @Date 2022/3/2 9:13
 * @Version
 */

//显示禁用DataSource
@SpringBootApplication
@EnableDiscoveryClient //该注解用于想使用consul或者zookeeper的注册中心时注册服务
public class OrderZKMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZKMain80.class, args);
    }
}

