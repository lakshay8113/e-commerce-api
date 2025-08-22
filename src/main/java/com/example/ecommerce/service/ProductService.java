package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductFilter;

public interface ProductService {

    List<Product> getAllProducts(int pageNo, int limit, String sortBy, int sortOrder, String searchTerm);

    List<Product> searchProducts(ProductFilter productFilter);

    Product addProduct(Product product);

    List<Product> getProductById(String id);

    Product updateProduct(String id, Product updatedProduct);

    boolean deleteProduct(String id);
}