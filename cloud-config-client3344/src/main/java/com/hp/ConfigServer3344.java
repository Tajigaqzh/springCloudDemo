package com.hp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-25 08:49
 */
@SpringBootApplication
@Slf4j
//开启配置中心功能
@EnableConfigServer
//开启注册功能
@EnableEurekaClient
public class ConfigServer3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer3344.class,args);
        log.info("************  ConfigServer3344 启动成功 *****");
    }
}
