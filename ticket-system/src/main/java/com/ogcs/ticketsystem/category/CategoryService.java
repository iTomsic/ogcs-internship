package com.ogcs.ticketsystem.category;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Category with " + id + " not found"));

        return modelMapper.map(category, CategoryDTO.class);

    }

    public CategoryDTO insertCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO updateCategoryById(Integer id, Category updatedCategory) {

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

        Category savedCategory = categoryRepository.save(existingCategory);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    public CategoryDTO deactivateCategoryById(Integer id) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with "+ id + " not found!"));

        existingCategory.setActivityStatus(false);

        Category deactivatedCategory = categoryRepository.save(existingCategory);
        System.out.println("Category with id: " + id + " deactivated");

        return modelMapper.map(deactivatedCategory, CategoryDTO.class);
    }

    public CategoryDTO activateCategoryById(Integer id) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with "+ id + " not found!"));

        Category activatedCategory = categoryRepository.save(existingCategory);
        System.out.println("Category with id: " + id + " activated");

        return modelMapper.map(activatedCategory, CategoryDTO.class);
    }

}
