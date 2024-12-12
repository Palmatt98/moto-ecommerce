package com._mx.controller;

import com._mx.dto.OrderRequest;
import com._mx.entity.Order;
import com._mx.entity.Product;
import com._mx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrder() {
        List<Order> orders = orderService.getListOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}/products")
    public ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable Long id) {
        List<Product> products = orderService.getProductsByOrderId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> saveOrder(@RequestBody OrderRequest order) {
        Order orderToSave = orderService.saveOrder(order);
        return new ResponseEntity<>(orderToSave, HttpStatus.CREATED);
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

}


