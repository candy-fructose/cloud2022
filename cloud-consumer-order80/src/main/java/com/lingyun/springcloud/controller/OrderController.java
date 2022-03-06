package com.lingyun.springcloud.controller;

import com.lingyun.springcloud.entity.CommonResult;
import com.lingyun.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class OrderController {
    //简单的http请求
//    public static final String PAYMENT_URl = "http://localhost:8001";
    //eureka上的服务名称
    public static final String PAYMENT_URl = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URl + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/{id}")
    public CommonResult queryById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_URl + "/payment/" + id, CommonResult.class);
    }

    @GetMapping("/payment/getForEntity/{id}")
    public CommonResult getForEntity(@PathVariable("id") Integer id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URl + "/payment/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return CommonResult.error();
        }
    }
}
