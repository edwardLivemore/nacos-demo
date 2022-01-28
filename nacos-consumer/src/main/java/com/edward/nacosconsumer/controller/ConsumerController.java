package com.edward.nacosconsumer.controller;

import com.edward.nacoscommon.CommonResult;
import com.edward.nacosconsumer.entity.Order;
import com.edward.nacosconsumer.req.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RefreshScope
@RestController
@RequestMapping("consumer")
@Slf4j
public class ConsumerController {

    @PostMapping("order")
    CommonResult<Void> createOrder(@RequestBody OrderRequest request) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setConsumerId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setProductId(request.getProductId());
        order.setProductName(request.getProductName());
        order.setPrice(request.getPrice());
        order.setOrderTime(LocalDateTime.now());
        log.info("收到商品信息, 正在创建订单: {}", order);
        return CommonResult.success(null);
    }
}
