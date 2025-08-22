package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductFilter;
import com.example.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<Product> getAllProducts(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String sortBy,
            @RequestParam(defaultValue = "0") int sortOrder,
            @RequestParam(defaultValue = "") String searchTerm) {
        return productService.getAllProducts(page, limit, sortBy, sortOrder, searchTerm);
    }

    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody ProductFilter filter) {
        return productService.searchProducts(filter);
    }

    @PostMapping("")
    public Product addProduct(@RequestBody Product productData) {
        return productService.addProduct(productData);
    }

    @GetMapping("/{id}")
    public List<Product> getProduct(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
