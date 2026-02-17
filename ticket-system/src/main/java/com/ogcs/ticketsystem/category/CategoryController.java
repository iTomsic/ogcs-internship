package com.ogcs.ticketsystem.category;

import jakarta.validation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categories = categoryService.getCategories();
        return new ResponseEntity<List<CategoryDTO>>(categories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> addNewCategory(@Valid @RequestBody Category category) {
        CategoryDTO savedCategory = categoryService.insertCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @DeleteMapping("{id}")
    public void deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CategoryDTO> updateCategoryById(@PathVariable Integer id, @RequestBody Category category) {
        CategoryDTO savedCategory = categoryService.updateCategoryById(id, category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PatchMapping("{id}/deactivate")
    public ResponseEntity<CategoryDTO> deactivateCategoryById(@PathVariable Integer id){
        CategoryDTO deactivatedCategory = categoryService.deactivateCategoryById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(deactivatedCategory);
    }

    @PatchMapping("{id}/activate")
    public ResponseEntity<CategoryDTO> activateCategoryById(@PathVariable Integer id){
        CategoryDTO activatedCategory = categoryService.activateCategoryById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(activatedCategory);
    }
}
