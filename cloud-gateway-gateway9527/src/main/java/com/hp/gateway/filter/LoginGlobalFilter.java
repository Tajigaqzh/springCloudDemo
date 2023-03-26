//package com.hp.gateway.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hp.gateway.common.Response;
//import com.hp.gateway.utils.JwtUtils;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//
///**
// * @author tony
// * @version 1.0
// * @date 2023-03-24 22:26
// * 登录过滤器
// */
//@Data
//@Slf4j
//@Component
//@ConfigurationProperties("org.my.jwt")
//public class LoginGlobalFilter implements GlobalFilter, Ordered {
//    /**
//     * 跳过路由
//     */
//    private String[] skipAuthUrls;
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String url = exchange.getRequest().getURI().getPath();
//        if (null!=skipAuthUrls && isSkipUrl(url)){
//            return chain.filter(exchange);
//        }
//        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
//        if (StringUtils.isEmpty(token)){
//            return createResponseObj(exchange,500,"token参数缺失");
//        }
//        boolean verify = JwtUtils.verify(token);
//        if (!verify){
//            return createResponseObj(exchange,500,"token失效");
//        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//    public boolean isSkipUrl(String url){
//        for (String skipAuthUrl : skipAuthUrls) {
//            if (url.startsWith(skipAuthUrl)){
//                return true;
//            }
//        }
//        return false;
//    }
//    private Mono<Void> createResponseObj(ServerWebExchange exchange,Integer code,String message){
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(HttpStatus.OK);
//        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
//        Response res = new Response(code, message);
//
//        // 7. 对象转字符串
//        byte[] bytes = JSONObject.toJSONString(res).getBytes(StandardCharsets.UTF_8);
//        // 8. 数据流返回数据
//        DataBuffer wrap = response.bufferFactory().wrap(bytes);
//        return response.writeWith(Flux.just(wrap));
//    }
//}
