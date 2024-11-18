package com.ecommerce.ecommerce.services.category;

import java.util.List;

import com.ecommerce.ecommerce.dto.category.CategoryRequestDto;
import com.ecommerce.ecommerce.dto.category.CategoryResponseDto;

public interface CategoryService {
    public List<CategoryResponseDto> getAll();
    public String postCategory(CategoryRequestDto dto);
    public String putCategory(CategoryRequestDto dto,String id);
    public String deleteCategory(String id);
}
