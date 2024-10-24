package com._mx.service;

import com._mx.model.Product;
import com._mx.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElse(null);
                return product;
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
       return productRepository.findCategoryById(categoryId);

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
