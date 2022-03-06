package com.lingyun.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName PaymentFallbackService
 * @Description TODO
 * @Author LingYun
 * @Date 2022/3/5 18:25
 * @Version
 */
@Component
//服务端挂了会做降级处理
public class PaymentFallbackService implements PaymentFeignHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall back-paymentInfo_OK,55555";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----PaymentFallbackService fall paymentInfo_TimeOut,55555";
    }
}
