package com.hp.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-24 22:00
 */
@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class UserMain6500 {
    public static void main(String[] args) {
        SpringApplication.run(UserMain6500.class,args);
        log.info("************** UserMain6500 启动成功 **********");
    }
}