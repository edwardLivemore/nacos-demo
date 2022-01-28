package com.edward.nacosproducer.req;

import lombok.Data;

@Data
public class ProductRequest {
    private String productId;

    private String productName;

    private Integer price;
}
