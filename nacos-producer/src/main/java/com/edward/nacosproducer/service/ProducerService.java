package com.edward.nacosproducer.service;

import com.edward.nacosproducer.req.ProductRequest;

public interface ProducerService {
    void createProduct(ProductRequest request);
}
