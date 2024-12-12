package com._mx.repository;

import com._mx.entity.Model;
import com._mx.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long id);

    @Query("SELECT m FROM Product p JOIN p.models m WHERE p.id = :productId")
    List<Model> findByProductsId(Long productId);
}
