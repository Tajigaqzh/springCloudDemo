package com.hp.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-24 21:18
 * 自定义局部过滤器过滤filter开启日志的请求
 */
@Slf4j
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory(){
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (config.consoleLog) {
                ServerHttpRequest request = exchange.getRequest();
                log.info(request.getQueryParams().toString());
            }
            return chain.filter(exchange);
        });
    }

    /**
     * 配置填写顺序
     * @return List
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }

    /**
     * 过滤器使用的配置内容
     */
    @Data
    public static class Config {
        private boolean consoleLog;
    }
}
