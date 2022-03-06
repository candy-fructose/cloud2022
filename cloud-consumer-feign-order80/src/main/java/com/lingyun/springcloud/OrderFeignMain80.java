package com.lingyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName OrderFeignMain80
 * @Description TODO
 * @Author LingYun
 * @Date 2022/3/3 17:25
 * @Version
 */
@SpringBootApplication
@EnableFeignClients  //激活
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}

