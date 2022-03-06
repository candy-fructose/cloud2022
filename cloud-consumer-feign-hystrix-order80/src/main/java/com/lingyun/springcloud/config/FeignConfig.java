package com.lingyun.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName FeignConfig
 * @Description TODO
 * @Author LingYun
 * @Date 2022/3/3 18:54
 * @Version
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feigenLoggerLevel() {
        return Logger.Level.FULL;
    }
}
