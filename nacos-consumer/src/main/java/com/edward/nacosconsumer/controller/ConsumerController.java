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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.UUID;

@RefreshScope
@RestController
@RequestMapping("consumer")
@Slf4j
public class ConsumerController {

    @PostMapping("order")
    CommonResult<Void> createOrder(@RequestBody OrderRequest request, HttpServletRequest req, HttpServletResponse response) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setConsumerId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setProductId(request.getProductId());
        order.setProductName(request.getProductName());
        order.setPrice(request.getPrice());
        order.setOrderTime(LocalDateTime.now());
        log.info("收到商品信息, 正在创建订单: {}", order);
        Enumeration<String> headers = req.getHeaderNames();
        while(headers.hasMoreElements()){
            String headerName = headers.nextElement();
            log.info("request header name : {}, value: {}", headerName, req.getHeader(headerName));
        }
        for (String header : response.getHeaderNames()) {
            log.info("response header name : {}, value : {}", header, response.getHeader(header));
        }
        return CommonResult.success(null);
    }
}
