package com._mx.service;

import com._mx.dto.OrderRequest;
import com._mx.model.Order;
import com._mx.model.Product;
import com._mx.repository.OrderRepository;
import com._mx.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getListOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElse(null);
        return order;
    }

    public Order saveOrder(OrderRequest request) {
        Order order = new Order();
        order.setTimestamp(LocalDateTime.now());
        order.setOrderNumber(request.getOrderNumber());
        order.setPaymentCost(request.getPaymentCost());

        Set<Long> productIds = request.getProductIds();
        //TODO prendere da db i prodotti attraverdo il loro id, poi popolare  order.setProducts con i prodotti ricavati dal db7
        //questa condizione determina che se l'id è n
        if (productIds != null && !productIds.isEmpty()){

            List<Product> product = productRepository.findAllById(productIds);
            order.setProducts(product);
        }else {
            order.setProducts(List.of());
        }

        Order orderSaved = orderRepository.save(order);
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
