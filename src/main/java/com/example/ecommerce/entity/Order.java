package com.example.ecommerce.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("orders")
public class Order {

    @Id
    private String id;

    private long orderId;

    private String userId;

    private List<OrderItem> orderItems;

    private Date createdAt;

    private Date updatedAt;

    private int status;

    @Transient
    private User userDetail;

    public List<OrderItem> orderItems() {
        return orderItems;
    }

}