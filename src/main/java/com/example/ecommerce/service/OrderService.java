package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.entity.Order;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getAllOrdersByUserId(String userId);

    Order addOrder(Order order);

    Order getOrderById(String id);

    Order updateOrder(String id, Order updatedOrder);

    boolean deleteOrder(String id);
}