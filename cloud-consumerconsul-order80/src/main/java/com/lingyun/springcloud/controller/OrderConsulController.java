package com.lingyun.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author LingYun
 * @Date 2022/2/27 11:00
 * @Version
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderConsulController {
    //简单的http请求
//    public static final String PAYMENT_URl = "http://localhost:8001";
    //eureka上的服务名称
    public static final String PAYMENT_URl = "http://CLOUD-PROVIDER-PAYMENT";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String create() {
        return restTemplate.getForObject(PAYMENT_URl + "/payment/consul", String.class);
    }
}
