//package com.edward.nacosgateway.config;
//
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import reactor.core.publisher.Mono;
//
//@Configuration
//public class RedisLimitationConfig {
//
//    // 限流的维度
//    @Bean
//    @Primary
//    public KeyResolver remoteHostLimitationKey() {
//        return exchange -> Mono.just(exchange.getRequest()
//                .getRemoteAddress()
//                .getAddress()
//                .getHostAddress());
//    }
//
//    // producer服务限流规则
//    @Bean
//    public RedisRateLimiter producerRateLimiter() {
//        return new RedisRateLimiter(10, 20);
//    }
//
//    // consumer服务限流规则
//    @Bean
//    public RedisRateLimiter consumerRateLimiter() {
//        return new RedisRateLimiter(20, 40);
//    }
//}
