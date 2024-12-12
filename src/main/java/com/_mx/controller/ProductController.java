package com._mx.controller;

import com._mx.entity.Model;
import com._mx.entity.Product;
import com._mx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    //scope singleton
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        //deleghiamo al service la logica per eseguire l'azione desiderata come in questocaso il salvataggio del prodotto
        Product productCreated = productService.createProduct(product);
        return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("products/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("products/{id}/models")
    public ResponseEntity<List<Model>> getProductByModelId(@PathVariable Long id){
        List<Model> models = productService.getProductByModelId(id);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @PostMapping("products/{productId}/models")
    public ResponseEntity<Void> addModelToProduct(@PathVariable Long productId, @RequestBody Long modelId) {
        productService.addModelToProduct(productId, modelId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("products/{productId}/models/{modelId}")
    public ResponseEntity<String> removeModelToProduct(@PathVariable Long productId, @PathVariable Long modelId) {
        productService.removeModelToProduct(productId, modelId);
        return new ResponseEntity<>("il prodotto è stato eliminato con successo", HttpStatus.OK);
    }



    @DeleteMapping("products/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {

        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
}
