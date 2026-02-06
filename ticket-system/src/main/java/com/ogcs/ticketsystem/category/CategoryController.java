package com.ogcs.ticketsystem.category;

import jakarta.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category addNewCategory(@Valid @RequestBody Category category) {
        return categoryService.insertCategory(category);
    }

    @DeleteMapping("{id}")
    public void deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }

    @PatchMapping("{id}")
    public Category updateCategoryById(@PathVariable Integer id, @Valid @RequestBody Category category) {
        return categoryService.updateCategoryById(id, category);
    }

    @PatchMapping("{id}/deactivate")
    public Category deactivateCategoryById(@PathVariable Integer id){
        return categoryService.deactivateCategoryById(id);
    }

    @PatchMapping("{id}/activate")
    public Category activateCategoryById(@PathVariable Integer id){
        return categoryService.activateCategoryById(id);
    }
}
