package com.lingyun.springcloud.controller;

import com.lingyun.springcloud.entity.CommonResult;
import com.lingyun.springcloud.entity.Payment;
import com.lingyun.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("****插入数据为:{}", payment);
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

}

