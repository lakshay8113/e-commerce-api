package com.example.ecommerce.entity;

import lombok.Data;

@Data
public class ProductFilter {
    private String searchTerm;

    private ProductPriceRangeFilter priceRange; // category

    private SortFilter sort;

    private int size; // limit

    private int pageNumber; // page
}

// {
// "searchTerm": "vena",
// "priceRange":{
// "max": 10000,
// "min": 1000
// },
// "sort": {
// "field": "dsa",
// "order": ""
// },
// "size": 10,
// "pageNumber": 1
// }