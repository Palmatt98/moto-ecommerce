package com._mx.service;

import com._mx.entity.OrderStatus;
import com._mx.enums.OrderStatusType;
import com._mx.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public OrderStatus findOrderStatusByCode(OrderStatusType code) {
        return orderStatusRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lo status " + code + " non esiste."));
    }

}
