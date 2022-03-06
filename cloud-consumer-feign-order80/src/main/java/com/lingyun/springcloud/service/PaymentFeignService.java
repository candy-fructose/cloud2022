package com.lingyun.springcloud.service;

import com.lingyun.springcloud.entity.CommonResult;
import com.lingyun.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/{id}")
    CommonResult<Payment> queryById(@PathVariable("id") Integer id);

    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeout();
}
