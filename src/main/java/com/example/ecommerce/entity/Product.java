package com.example.ecommerce.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("products")
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private double price;

    private double discountedPrice;

    private long stock;

    private List<String> images;

    private List<String> tags;

    public Date createdAt;

    public Date updatedAt;

    public boolean isDeleted;

}
