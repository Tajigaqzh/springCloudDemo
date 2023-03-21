package com.hp.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 16:34
 */
@Component
@FeignClient("PAYMENT-PROVIDER")
public interface PaymentFeignService {
    /**
     * 远程调用payment的index
     * @return String
     */
    @GetMapping("/payment/index")
    String index();

    /**
     * 模拟超时
     * @return String
     */
    @GetMapping("/payment/timeout")
    String timeout();

}
