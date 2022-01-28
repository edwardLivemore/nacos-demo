package com.edward.nacosproducer.api;

import com.edward.nacoscommon.CommonResult;
import com.edward.nacosproducer.api.req.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "consumer", url = "${consumer.url}")
public interface NacosConsumerService {
    // 下单
    @PostMapping("order")
    CommonResult<Void> createOrder(@RequestBody OrderRequest request);
}
