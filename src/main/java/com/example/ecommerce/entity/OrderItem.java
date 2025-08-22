package com.example.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItem {

    private String productId;

    private long quantity;

    private Product productDetail;

}
