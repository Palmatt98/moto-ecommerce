package com._mx.repository;

import com._mx.entity.Order;
import com._mx.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT c.products FROM Order c WHERE c.id = :orderId")
    List<Product> findProductsById(Long orderId);

}
