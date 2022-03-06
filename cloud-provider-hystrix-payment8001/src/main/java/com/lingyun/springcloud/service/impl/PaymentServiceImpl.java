package com.lingyun.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.lingyun.springcloud.dao.PaymentDao;
import com.lingyun.springcloud.entity.Payment;
import com.lingyun.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * (Payment)表服务实现类
 *
 * @author
 * @since 2022-02-27 09:46:38
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Payment queryById(Integer id) {
        return paymentDao.selectById(id);
    }

    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id: " + id + "\t" + "哈哈~";
    }


    //********服务降级
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {//服务的降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")//设置超时时间为3秒
    })//一旦调用服务方法失败并抛出了错误信息后，
    //会自动调用@HystrixCommand标注好的fallbackMethod指定的方法
    public String paymentInfo_TimeOut(Integer id) {
//        int age = 10 / 0;
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id: " + id + "\t" + "哈哈~" + " 耗时(秒):" + timeNumber;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,系统繁忙或者运行报错，请稍后再试，" +
                "id: " + id + "\t" + "555~~";
    }


    //********服务熔断
    @HystrixCommand(fallbackMethod = "paymentCiruitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            //如果值是20，那么如果在滑动窗口中只接收到19个请求(比如一个10秒的窗口)，即使所有19个请求都失败了，断路器也不会打开。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            //此属性设置断路器打开后拒绝请求的时间量，每隔一段时间(sleepWindowInMilliseconds，单位是毫秒)允许再次尝试(也就是放行一个请求)确定是否应该关闭断路器。
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "61")//失败率达到多少后跳匝
    })
    public String paymentCiruitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCiruitBreaker_fallback(Integer id) {
        return "id不能为负数，请稍后再试，555555，id:" + id;
    }


    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment update(Payment payment) {
        paymentDao.updateById(payment);
        return queryById(payment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return paymentDao.deleteById(id) > 0;
    }
}
