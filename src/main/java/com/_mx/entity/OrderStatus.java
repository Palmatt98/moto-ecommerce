package com._mx.entity;


import com._mx.enums.OrderStatusType;
import jakarta.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatusType code;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCode(OrderStatusType code) {
        this.code = code;
    }

    public OrderStatusType getCode() {
        return code;
    }
}
