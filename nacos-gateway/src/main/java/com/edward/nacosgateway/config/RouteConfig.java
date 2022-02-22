//package com.edward.nacosgateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import static org.springframework.http.HttpStatus.BANDWIDTH_LIMIT_EXCEEDED;
//
//@Configuration
//public class RouteConfig {
//    @Autowired
//    private KeyResolver hostAddressKeyResolver;
//
//    @Autowired
//    @Qualifier("producerRateLimiter")
//    private RedisRateLimiter producerRateLimiter;
//
//    @Autowired
//    @Qualifier("consumerRateLimiter")
//    private RedisRateLimiter consumerRateLimiter;
//
//    @Bean
//    public RouteLocator declare(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(route -> route
//                        .path("/gateway/product/**")
//                        .filters(f -> f.stripPrefix(1)
//                                .requestRateLimiter(limiter -> {
//                                    limiter.setKeyResolver(hostAddressKeyResolver);
//                                    limiter.setRateLimiter(producerRateLimiter);
//                                    limiter.setStatusCode(BANDWIDTH_LIMIT_EXCEEDED);
//                                }))
//                        .uri("lb://producer-server"))
//                .route(route -> route
//                        .path("/gateway/consumer/order/**")
//                        .filters(f -> f.stripPrefix(1)
//                                .requestRateLimiter(limiter -> {
//                                    limiter.setKeyResolver(hostAddressKeyResolver);
//                                    limiter.setRateLimiter(consumerRateLimiter);
//                                    limiter.setStatusCode(BANDWIDTH_LIMIT_EXCEEDED);
//                                }))
//                        .uri("lb://consumer-server"))
//                .build();
//    }
//}
