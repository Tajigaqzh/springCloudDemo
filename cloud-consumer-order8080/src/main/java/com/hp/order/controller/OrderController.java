package com.hp.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 11:12
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 服务发现
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 服务发现获取服务列表清单
     * @return Object json
     */
    @GetMapping("/discovery")
    public Object testDiscoveryClient(){

        // 获取所有服务列表清单
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        return this.discoveryClient;
    }

    /**
     * 测试服务调用
     * @return String
     */
    @GetMapping("/invoke")
    public String testInvokeService(){
        String hostName = "PAYMENT-PROVIDER";
        String url = "/payment/index";

        List<ServiceInstance> instances = discoveryClient.getInstances(hostName);
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println(serviceInstance.getUri());
        System.out.println(serviceInstance.getPort());
        System.out.println(serviceInstance.getHost());
        System.out.println(serviceInstance.getServiceId());

        //发起远程调用
        return restTemplate.getForObject(serviceInstance.getUri() + url, String.class);
    }
}
