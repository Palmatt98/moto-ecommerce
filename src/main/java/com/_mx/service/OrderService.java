package com._mx.service;

import com._mx.model.Order;
import com._mx.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getListOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElse(null);
        return order;
    }

    public Order saveOrder(Order order) {
        Order orderSaved =  orderRepository.save(order);
        return orderSaved;
    }

    public Order updateOrder(Long id,Order order) {
        Order existingOrder = orderRepository.findById(id)
                .orElse(null);
        if (existingOrder != null) {
            existingOrder.setOrderNumber(order.getOrderNumber());
            existingOrder.setPaymentCost(order.getPaymentCost());
            existingOrder.setTimestamp(order.getTimestamp());
        }
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
