package com.hp.consumer.controller;

import com.hp.consumer.service.PaymentOpenFeignService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-19 21:18
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private PaymentOpenFeignService paymentOpenFeignService;

    /**
     * 超时降级
     * 使用名为delay的超时降级机制（在配置文件中,delay名是自定义的）
     * resilience4j:
     *   # 超时机制
     *   timelimiter:
     *     instances:
     *       delay:
     *         # 设置超时时间 5秒
     *         timeoutDuration: 2
     * @return CompletableFuture<String>
     *     如果超时则调用timeoutFallback降级方法
     */
    @TimeLimiter(name = "delay",fallbackMethod = "timeoutFallback")
    @GetMapping("/timeout")
    public CompletableFuture<String> timeout(){
        log.info("进入方法");

        //异步操作
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> paymentOpenFeignService.timeout());
        log.info("退出方法");
        return stringCompletableFuture;
    }

    /**
     * 超时降级方法
     * @param e 异常
     * @return string
     */
    public CompletableFuture<String> timeoutFallback(Exception e){
        e.printStackTrace();
        return CompletableFuture.completedFuture("超时了");
    }



    @GetMapping("/retry")
    @Retry(name="retryA",fallbackMethod = "retryFallback")
    public CompletableFuture<String> retry(){
        log.info("进入方法");

        //异步操作
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> paymentOpenFeignService.index());
        log.info("退出方法");
        return stringCompletableFuture;
    }

    public CompletableFuture<String> retryFallback(Exception e){
        e.printStackTrace();
        return CompletableFuture.completedFuture("重试失败");
    }

    /**
     * 测试异常比例熔断
     * @return String
     */
    @GetMapping("/circuitBreaker")
    @CircuitBreaker(name = "circuitBreakerA",fallbackMethod = "circuitBreakerFallback")
    public String circuitBreaker(){
        log.info("********** 进入方式 ********");
        String index = paymentOpenFeignService.index();
        log.info("********** 离开方式 ********");
        return index;
    }
    /**
     * 异常比例熔断降级
     * @param e e错误
     * @return String
     */
    public String circuitBreakerFallback(Exception e){
        e.printStackTrace();
        return "客观服务繁忙 稍等一会";
    }


    /**
     * 慢调用比例熔断降级
     * @return string
     */
    @CircuitBreaker(name = "circuitBreakerB",fallbackMethod = "slowFallback")
    @GetMapping("/slowCircuitBreaker")
    public String slowCircuitBreaker(){
        log.info("********** 进入方法 ********");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String index = paymentOpenFeignService.index();
        log.info("********** 离开方法 ********");
        return index;
    }

    /**
     * 慢调用比例熔断降级
     * @param e 异常
     * @return String
     */
    public String slowFallback(Exception e){
        e.printStackTrace();
        return "客观服务繁忙 稍等一会";
    }

    /**
     * 信号量隔离
     * @return
     * @throws InterruptedException
     */
    @Bulkhead(name = "backendA",type = Bulkhead.Type.SEMAPHORE)
    @GetMapping("/bulkhead")
    public String bulkhead() throws InterruptedException {

        log.info("********** 进入方法 ********");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String index = paymentOpenFeignService.index();
        log.info("********** 离开方法 ********");
        return index;
    }

    /**
     * 线程池隔离
     * @return
     */
    @Bulkhead(name = "backendA",type = Bulkhead.Type.THREADPOOL)
    @GetMapping("/threadPoolBulkhead")
    public CompletableFuture<String> threadPoolBulkhead(){
        log.info("********** 进入方法 *******");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("********** 离开方法 *******");
        return CompletableFuture.supplyAsync(() -> "线程池隔离信息......");
    }

    /**
     * 测试限流
     * @return
     */
    @RateLimiter(name = "backendA")
    @GetMapping("limiter")
    public CompletableFuture<String> limiter() throws InterruptedException {
        log.info("************ 进入方法**********");
        TimeUnit.SECONDS.sleep(5);
        log.info("************ 离开方法**********");
        return CompletableFuture.supplyAsync(()->"限流");
    }

}
