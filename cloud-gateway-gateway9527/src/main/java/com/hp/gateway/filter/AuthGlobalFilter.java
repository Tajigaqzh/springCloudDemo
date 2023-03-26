//package com.hp.gateway.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//
///**
// * @author tony
// * @version 1.0
// * @date 2023-03-24 21:43
// * 自定义全局过滤器，需要实现GlobalFilter和Ordered接口
// */
//@Slf4j
//@Component
//public class AuthGlobalFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
//        if (StringUtils.isEmpty(token)){
//            log.error(exchange.getRequest().getURI()+"没有token参数");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//        if (!"a".equals(token)){
//            log.error(exchange.getRequest().getURI()+"token无效");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//        return chain.filter(exchange);
//    }
//
//    /**
//     * 执行顺序，越小越靠前
//     * @return int
//     */
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
