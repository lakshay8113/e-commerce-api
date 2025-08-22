package com.example.ecommerce.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.enums.OrderStatusType;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        List<Order> ordersFromDB = orderRepository.findAllOrders();

        for (Order order : ordersFromDB) {
            // ["123", "1256", "14785"]
            processOrder(order);
            // User userFromDB =
            // userRepository.findUserById(order.getUserId()).orElse(null);
            // order.setUserDetail(userFromDB);

            // for (OrderItem orderItem : order.getOrderItems()) {
            // Product product =
            // productRepository.findById(orderItem.getProductId()).orElse(null);
            // orderItem.setProductDetail(product);
            // }
        }

        return ordersFromDB;
    }

    @Override
    public List<Order> getAllOrdersByUserId(String userId) {
        return orderRepository.findAllOrdersByUserId(userId);
    }

    @Override
    public Order addOrder(Order order) {
        Order orderToSend = new Order();

        orderToSend.setUserId(order.getUserId());
        orderToSend.setOrderItems(order.getOrderItems());

        orderToSend.setStatus(OrderStatusType.DRAFT.getOrderStatus());
        orderToSend.setCreatedAt(new Date());
        orderToSend.setUpdatedAt(new Date());

        return processOrder(orderRepository.save(orderToSend));
    }

    @Override
    public Order getOrderById(String myId) {

        Order orderFromDB = orderRepository.findOrderById(myId).orElse(null);

        return processOrder(orderFromDB);

        // return orderRepository.findOrderById(myId).orElse(null);

    }
    // Array, Object does not change their reference when copied.

    private Order processOrder(Order orderFromDB) {
        User userFromDB = userRepository.findUserById(orderFromDB.getUserId()).orElse(null);
        orderFromDB.setUserDetail(userFromDB);

        for (OrderItem orderItem : orderFromDB.getOrderItems()) {
            Product product = productRepository.findById(orderItem.getProductId()).orElse(null);
            orderItem.setProductDetail(product);
        }

        return orderFromDB;
    }

    @Override
    public Order updateOrder(String id, Order updatedOrder) {
        boolean order_exists = orderRepository.existsById(id);

        if (order_exists) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        }

        return null;
    }

    @Override
    public boolean deleteOrder(String id) {
        Order orderFromDB = orderRepository.findOrderById(id).orElse(null);

        if (orderFromDB != null) {
            // orderFromDB.setDeleted(true);
            orderRepository.save(orderFromDB);
            return true;
        }

        return false;
    }
}
