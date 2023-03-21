package com.hp.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 16:28
 */
@Slf4j
@SpringBootApplication
@EnableFeignClients
public class OrderOpenFeignMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignMain.class,args);
        log.info("orderFeign80启动成功");
    }
}
