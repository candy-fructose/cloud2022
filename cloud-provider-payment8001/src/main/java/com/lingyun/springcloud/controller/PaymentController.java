package com.lingyun.springcloud.controller;

import com.lingyun.springcloud.entity.CommonResult;
import com.lingyun.springcloud.entity.Payment;
import com.lingyun.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (Payment)表控制层
 *
 * @author
 * @since 2022-02-27 09:46:36
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    /**
     * 服务对象
     */
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    //注意包是org.springframework.cloud.起头的
    private DiscoveryClient discoveryClient;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public CommonResult queryById(@PathVariable("id") Integer id) {
        log.info("****查询数据的id为:{},端口号为:{}", id, serverPort);
        return CommonResult.ok(paymentService.queryById(id));
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            //openfeign默认等待一秒，超时后报错
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


    /**
     * 新增数据
     *
     * @param payment 实体
     * @return 新增结果
     */
    @PostMapping("/create")
    //@RequestBody注解千万别漏了
    public CommonResult add(@RequestBody Payment payment) {
        log.info("****插入数据为:{},端口号为:{}", payment, serverPort);
        if (paymentService.insert(payment) > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    /**
     * 编辑数据
     *
     * @param payment 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Payment> edit(Payment payment) {
        return ResponseEntity.ok(paymentService.update(payment));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(paymentService.deleteById(id));
    }


    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        //获得服务清单列表
        services.stream().forEach(i -> {
            log.info("********element:" + i);
        });
        //获取服务名为CLOUD-PAYMENT-SERVICE的所有服务器消息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.stream().forEach(i -> {
            log.info(i.getServiceId() + "\t" + i.getHost() + "\t" + i.getPort() + "\t" + i.getUri());
        });
        return discoveryClient;
    }
}

