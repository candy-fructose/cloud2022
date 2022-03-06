package cloud.lingyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName PaymentMain8004
 * @Description TODO
 * @Author LingYun
 * @Date 2022/3/2 10:18
 * @Version
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于想使用consul或者zookeeper的注册中心时注册服务
public class PaymentMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8006.class, args);
    }
}
