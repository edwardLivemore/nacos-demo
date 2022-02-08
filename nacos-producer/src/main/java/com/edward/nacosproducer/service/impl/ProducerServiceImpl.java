package com.edward.nacosproducer.service.impl;

import com.edward.nacosproducer.api.NacosConsumerService;
import com.edward.nacosproducer.api.req.OrderRequest;
import com.edward.nacosproducer.req.ProductRequest;
import com.edward.nacosproducer.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
@RefreshScope
public class ProducerServiceImpl implements ProducerService {
//    @Value("${consumer.url}")
//    private String consumerUrl;

    @Autowired
    private NacosConsumerService consumerService;

    @Override
    public void createProduct(ProductRequest request) {
        log.info("正在创建商品...");

        consumerService.createOrder(new OrderRequest(request.getProductId(), request.getProductName(), request.getPrice()));
    }
}
