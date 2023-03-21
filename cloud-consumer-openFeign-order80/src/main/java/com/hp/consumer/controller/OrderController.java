package com.hp.consumer.controller;

import com.hp.consumer.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 16:33
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    /**
     * openFeign 远程服务调用
     * @return string
     */
    @GetMapping("index")
    public String index(){
        return paymentFeignService.index();
    }
    /**
     * 测试超时机制
     * @return 测试超时
     */
    @GetMapping("timeout")
    public String timeout(){
        return paymentFeignService.timeout();
    }
}
