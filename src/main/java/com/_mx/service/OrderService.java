package com._mx.service;

import com._mx.dto.OrderRequest;
import com._mx.entity.Order;
import com._mx.entity.OrderStatus;
import com._mx.entity.Product;
import com._mx.enums.OrderStatusType;
import com._mx.repository.OrderRepository;
import com._mx.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderStatusService orderStatusService;
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

        //settiamo lo stato iniziale "PENDING" per tutti i nuovi ordini
        OrderStatus initialStatus = orderStatusService.findOrderStatusByCode(OrderStatusType.PENDING);
        order.setStatus(initialStatus);

        Set<Long> productIds = request.getProductIds();
        //prendere da db i prodotti attraverdo i loro id, poi popolare  order.setProducts con i prodotti ricavati dal db

        if (!CollectionUtils.isEmpty(productIds)){
            List<Product> products = productRepository.findAllById(productIds);
            if (productIds.size() != products.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non puoi ordinare prodotti non esistenti");
            }
            order.setProducts(products);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non puoi creare un ordine senza inserire i prodotti");
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
