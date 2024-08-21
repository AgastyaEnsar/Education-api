package com.ensar.service;

import com.ensar.entity.Category;
import com.ensar.repository.CategoryRepository;
import com.ensar.request.dto.CreateUpdateCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createOrUpdateCategory(Optional<String> categoryId, CreateUpdateCategoryDto categoryDto) {
        Category category;
        if (categoryId.isEmpty() || categoryId.get().isBlank()) {
            category = new Category();
            category.setId(UUID.randomUUID().toString());
        } else {
            category = categoryRepository.findById(categoryId.get())
                    .orElseThrow(() -> new RuntimeException("Category with id " + categoryId.get() + " not found"));
        }

        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());

        return categoryRepository.save(category);
    }

    public Category getCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with id " + categoryId + " not found"));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with id " + categoryId + " not found"));
        categoryRepository.delete(category);
    }
}
