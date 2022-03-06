package com.lingyun.springcloud.controller;

import com.lingyun.springcloud.entity.CommonResult;
import com.lingyun.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderFeignController
 * @Description TODO
 * @Author LingYun
 * @Date 2022/3/3 17:38
 * @Version
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/{id}")
    public CommonResult queryById(@PathVariable("id") Integer id) {
        log.info("****查询数据的id为:{},端口号为:{}", id);
        return paymentFeignService.queryById(id);
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentFeignTimeout();
    }


}
