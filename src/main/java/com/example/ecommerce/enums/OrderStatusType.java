package com.example.ecommerce.enums;

public enum OrderStatusType {
    DRAFT(0),
    CONFIRMED(1),
    IN_PROGRESS(2),
    SHIPPED(3),
    OUT_OF_DELIVERY(4),
    DELIVERED(5),
    CANCELLED(6);

    private int orderStatus;

    public int getOrderStatus() {
        return orderStatus;
    }

    OrderStatusType(int orderStatus) {
        this.orderStatus = orderStatus;
    }

}

// public enum OrderStatusType {
// DRAFT,
// CONFIRMED,
// IN_PROGRESS,
// SHIPPED,
// OUT_OF_DELIVERY,
// DELIVERED,
// CANCELLED;
// }