package com.hp.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 08:22
 */
@EnableEurekaClient
@Slf4j
@SpringBootApplication
public class OrderMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8080.class, args);
        log.info("消费者order启动成功");
    }
}
