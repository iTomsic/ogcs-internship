package com.ogcs.ticketsystem.category;

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
    public void addNewCategory(@RequestBody Category category) {
        categoryService.insertCategory(category);
    }

    @DeleteMapping("{id}")
    public void deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }

    @PatchMapping("{id}")
    public void updateCategoryById(@PathVariable Integer id, @RequestBody Category category) {
        categoryService.updateCategoryById(id, category);
    }
}
