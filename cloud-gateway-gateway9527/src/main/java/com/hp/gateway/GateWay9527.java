package com.hp.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-20 21:14
 */
@Slf4j
@SpringBootApplication
public class GateWay9527 {
    public static void main(String[] args) {
        SpringApplication.run(GateWay9527.class, args);
        log.info(" Gateway9527 服务启动成功");
    }
}
