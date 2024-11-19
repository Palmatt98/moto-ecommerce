package com._mx.repository;

import com._mx.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Long é il tipo del campo ID di Brand e Brand é la entity su cui lavora il repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
