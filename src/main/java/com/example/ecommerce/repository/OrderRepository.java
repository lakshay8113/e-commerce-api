package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ }")
    List<Order> findAllOrders();

    @Query("{ userId: ?0 }")
    List<Order> findAllOrdersByUserId(String userId);

    @Query("{ id: ?0 }")
    Optional<Order> findOrderById(String id);

}