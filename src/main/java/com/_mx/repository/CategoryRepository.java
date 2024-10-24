package com._mx.repository;

import com._mx.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //@Query("SELECT c FROM Category c WHERE c.name = :name") questa è la query che fa il metodo sottoindicato.
    Category findByName(String name);

}
