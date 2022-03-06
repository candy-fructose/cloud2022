package com.lingyun.springcloud.controller;

import com.lingyun.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //通用的fallback
public class OrderFeignHystrixController {
    @Autowired
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        log.info("****查询数据的id为:{},端口号为:{}", id);
        return paymentFeignHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    //fallback一般用到客户端，客户端错误
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {//服务的降级
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")//设置超时时间为3秒
//    })//一旦调用服务方法失败并抛出了错误信息后，
    //会自动调用@HystrixCommand标注好的fallbackMethod指定的方法，服务端与
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        return paymentFeignHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙请10秒后再试或者自己运行出错请检查自己，555~~~~，定制异常方法";
    }
    public String payment_Global_FallbackMethod() {
        return "我是消费者80，对方支付系统繁忙请10秒后再试或者自己运行出错请检查自己，555~~~~，通用兜底方法";
    }

}
