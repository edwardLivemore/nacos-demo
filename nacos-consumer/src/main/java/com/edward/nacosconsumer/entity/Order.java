package com.edward.nacosconsumer.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private String id;
    private String consumerId;
    private String productId;
    private String productName;
    private Integer price;
    private LocalDateTime orderTime;
}
