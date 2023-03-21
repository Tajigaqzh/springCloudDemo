package com.hp.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 21:12
 */
@Service
@FeignClient("PAYMENT-PROVIDER")
public interface PaymentOpenFeignService {
    /**
     * 超时测试
     * @return String
     */
    @GetMapping("/payment/timeout")
    String timeout();

    /**
     * index
     * @return index
     */
    @GetMapping("/payment/index")
    String index();
}
