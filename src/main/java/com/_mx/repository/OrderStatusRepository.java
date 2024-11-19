package com._mx.repository;

import com._mx.entity.OrderStatus;
import com._mx.enums.OrderStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    Optional<OrderStatus> findByCode(OrderStatusType code);

}
