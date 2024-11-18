package com.ecommerce.ecommerce.services.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.category.CategoryRequestDto;
import com.ecommerce.ecommerce.dto.category.CategoryResponseDto;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    private CategoryResponseDto toResponseDto(Category category){
        return CategoryResponseDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

    @Override
    public String postCategory(CategoryRequestDto dto) {
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        categoryRepository.save(category);
        return "success";
    }

    @Override
    public String putCategory(CategoryRequestDto dto, String id) {
        Category category = categoryRepository.getReferenceById(id);
        category.setCategoryName(dto.getCategoryName());
        categoryRepository.save(category);
        return "success";
    }

    @Override
    public String deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElse(null);
        categoryRepository.delete(category);
        return "success";
    }
    
}
