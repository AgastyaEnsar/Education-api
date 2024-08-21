package com.ensar.controller;

import com.ensar.entity.Category;
import com.ensar.request.dto.CreateUpdateCategoryDto;
import com.ensar.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = "Category Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600) // Update this to match your frontend's origin
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Get category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @ApiParam(value = "ID of the category to retrieve", required = true)
            @PathVariable String id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @ApiOperation(value = "Get all categories")
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Category>>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        Map<String, List<Category>> response = new HashMap<>();
        response.put("categories", categories);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new category")
    @PostMapping("/")
    public ResponseEntity<Category> createCategory(
            @Valid @RequestBody CreateUpdateCategoryDto categoryDto) {
        Category savedCategory = categoryService.createOrUpdateCategory(Optional.empty(), categoryDto);
        return ResponseEntity.ok(savedCategory);
    }

    @ApiOperation(value = "Update an existing category")
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @ApiParam(value = "ID of the category to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody CreateUpdateCategoryDto categoryDto) {
        Category updatedCategory = categoryService.createOrUpdateCategory(Optional.of(id), categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @ApiOperation(value = "Delete a category by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @ApiParam(value = "ID of the category to delete", required = true)
            @PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
