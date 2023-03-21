package com.hp.consumer.config;

import feign.Logger;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 16:30
 */
@SpringBootConfiguration
public class OpenFeignConfig {
    /**
     * 设置feign日志级别
     * @return Level
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
