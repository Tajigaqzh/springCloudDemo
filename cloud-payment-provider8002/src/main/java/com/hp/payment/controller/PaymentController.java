package com.hp.payment.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 10:57
 * 支付控制层
 */

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @GetMapping("/lb")
    public String lb(){
        return port;
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service={}",service);
        }
        return this.discoveryClient;
    }

    @GetMapping("/index")
    public String index(){
        return "payment success";
    }

    /**
     * 超时设置
     * @return 超时
     */
    @GetMapping("/timeout")
    public String paymentTimeout(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "payment success";
    }
}
