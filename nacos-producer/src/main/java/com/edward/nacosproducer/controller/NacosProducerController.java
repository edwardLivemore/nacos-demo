package com.edward.nacosproducer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RefreshScope
@RestController
@RequestMapping("product")
@Slf4j
public class NacosProducerController {
    @Autowired
    private ProducerService producerService;

    @Value("${enableOrder}")
    private Boolean enableOrder;

    @PostMapping
    @SentinelResource(value = "createProduct", blockHandler = "createProductBlockHandler", fallback = "createProductFallback")
    public CommonResult<Void> createProduct(@RequestBody ProductRequest request, HttpServletRequest req, HttpServletResponse response) {
        Enumeration<String> headers = req.getHeaderNames();
        while(headers.hasMoreElements()){
            String headerName = headers.nextElement();
            log.info("request header name : {}, value: {}", headerName, req.getHeader(headerName));
        }
        for (String header : response.getHeaderNames()) {
            log.info("response header name : {}, value : {}", header, response.getHeader(header));
        }

        if (enableOrder) {
            log.info("下单状态: 开启");
            if(request.getPrice() % 2 == 0) {
                // 价格为偶数时，正常下单
                producerService.createProduct(request);
            }else{
                // 服务降级
                throw new RuntimeException("服务降级");
            }
        } else {
            log.info("下单状态: 关闭");
        }
        return CommonResult.success(null);
    }

    public CommonResult<Void> createProductBlockHandler(ProductRequest request, HttpServletRequest req,
                                                        HttpServletResponse response, BlockException blockException) {
        log.info("创建产品接口被限流");
        return CommonResult.fail(101, "已限流");
    }

    public CommonResult<Void> createProductFallback(ProductRequest request, HttpServletRequest req,
                                                    HttpServletResponse response) {
        log.info("创建产品接口被降级");
        return CommonResult.fail(102, "已降级");
    }
}
