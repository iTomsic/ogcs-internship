package com.ogcs.ticketsystem.category;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with " + id + " not found"));
    }

    public Category insertCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategoryById(Integer id, Category updatedCategory) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with "+ id + " not found!"));

        if (updatedCategory.getName() != null) {
            existingCategory.setName(updatedCategory.getName());
        }

        if (updatedCategory.getDescription() != null) {
            existingCategory.setDescription(updatedCategory.getDescription());
        }

        if (updatedCategory.getActivityStatus() != null) {
            existingCategory.setActivityStatus(updatedCategory.getActivityStatus());
        }

        return categoryRepository.save(existingCategory);
    }
}
