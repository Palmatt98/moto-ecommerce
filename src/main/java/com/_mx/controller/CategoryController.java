package com._mx.controller;

import com._mx.entity.Category;
import com._mx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category ) {
        //salvo la categoria
        //ritorno la categoria salvata
        Category categoryCreated = categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category category) {
        Category exsistingCategory = categoryService.updateCategory(id, category);
                return new ResponseEntity<>(exsistingCategory, HttpStatus.OK);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) {
            categoryService.deleteCategory(id);
                return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

}
