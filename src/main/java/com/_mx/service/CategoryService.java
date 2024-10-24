package com._mx.service;

import com._mx.model.Category;
import com._mx.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category getCategoryById(Long id) {
        //categoryRepository.findById(id) ritorna un Optional
        Category category = categoryRepository.findById(id)
                .orElse(null);
        return category;
    }

    public Category saveCategory(Category category) {
        Category categoryCreated = categoryRepository.save(category);
        return categoryCreated;
    }

    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElse(null);
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category findByName(String name) {
        Category category = categoryRepository.findByName(name);
        return category;
    }



}
