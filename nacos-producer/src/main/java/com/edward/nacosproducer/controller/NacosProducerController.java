package com.edward.nacosproducer.controller;

import com.edward.nacoscommon.CommonResult;
import com.edward.nacosproducer.req.ProductRequest;
import com.edward.nacosproducer.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("product")
@Slf4j
public class NacosProducerController {
    @Autowired
    private ProducerService producerService;

    @Value("${enableOrder:true}")
    private Boolean enableOrder;

    @PostMapping
    public CommonResult<Void> createProduct(@RequestBody ProductRequest request) {
        if (enableOrder) {
            log.info("下单状态: 开启");
            producerService.createProduct(request);
        } else {
            log.info("下单状态: 关闭");
        }
        return CommonResult.success(null);
    }
}
