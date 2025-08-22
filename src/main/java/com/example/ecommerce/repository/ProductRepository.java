package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{}")
    List<Product> findAllProducts(Pageable pageable);

    @Query("{ id: ?0 }")
    Optional<Product> findProductById(String id);

}