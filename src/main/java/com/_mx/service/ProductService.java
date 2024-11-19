package com._mx.service;

import com._mx.entity.Category;
import com._mx.entity.Model;
import com._mx.entity.Product;
import com._mx.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product createProduct(Product product) {
        Category category = product.getCategory() == null
                ? categoryService.findByName("Altro")
                : getCategoryFromBody(product.getCategory());
        product.setCategory(category);

        // [1, 2, 3 ,4]
        // [{id: 1}, {id: 2}, {id: 3}]
        Set<Long> ids = product.getModels().stream()
                .filter(model -> model != null)
                //.map() mappa ogni modello prendendo il suo id grazie alla lambda es. {id: 1} diventa 1
                //dove {id: 1} rapprensenta il modello
                // e 1 é il risultato di model.getId()
                .map(model -> model.getId())
                //il filter é un setaccio, in questo caso manteniamo gli id diversi da null e il resto lo butta
                .filter(id -> id != null)
                .collect(Collectors.toSet());
        List<Model> modelList = modelService.getModelsByIds(ids);
        Set<Model> models = modelList.stream().collect(Collectors.toSet());
        product.setModels(models);
        Product productCreated = productRepository.save(product);
        return productCreated;
    }

    public Product updateProduct(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non esiste nessun prodotto con l'id " + id));

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setAvailability(product.isAvailability());
        productToUpdate.setSku(product.getSku());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setModels(product.getModels());

        Product updatedProduct = productRepository.save(productToUpdate);
        return updatedProduct;
    }

    private Category getCategoryFromBody(Category category) {
        if (category.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoria deve avere un ID, birbante!");
        }
        Category categoryFromDB = categoryService.getCategoryById(category.getId());
        if (categoryFromDB == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoria con id " + category.getId() + " non esiste, birbante!");
        }
        return categoryFromDB;
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElse(null);
        return product;
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
       return productRepository.findByCategoryId(categoryId);

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
