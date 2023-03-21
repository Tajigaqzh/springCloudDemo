package com.hp.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 21:08
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class Resilience4jOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(Resilience4jOrderMain.class,args);
        log.info("resilience4j 启动成功");
    }
}
