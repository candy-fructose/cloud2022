package com.lingyun.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ApplicationContextConfig
 * @Description TODO
 * @Author LingYun
 * @Date 2022/2/27 11:03
 * @Version
 */
@Configuration
public class ApplicationContextConfig {
    @Bean//加入容器
    @LoadBalanced//赋予RestTemple负载均衡机制（默认轮询）
    public RestTemplate getRestTemple() {
        return new RestTemplate();
    }

}
