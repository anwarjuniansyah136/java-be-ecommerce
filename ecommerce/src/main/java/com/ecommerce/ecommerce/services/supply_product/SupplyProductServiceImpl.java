package com.ecommerce.ecommerce.services.supply_product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.supply_product.SupplyProductDto;
import com.ecommerce.ecommerce.dto.supply_product.SupplyProductResponseDto;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.SupplayProduct;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.repositories.SupplyProductRepository;

@Service
public class SupplyProductServiceImpl implements SupplyProductService {

    @Autowired
    private SupplyProductRepository supplyProductRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public String post(SupplyProductDto dto) {
        SupplayProduct supply = new SupplayProduct();
        supply.setProductName(dto.getProductName());
        supply.setProductPrice(dto.getProductPrice());
        supply.setProductQuantity(dto.getProductQuantity());
        supply.setDate(convertStringToDate(dto.getDate()));

        Category category = categoryRepository.findByCategoryName(dto.getCategory());
        if (category == null) {
            return "Category not found";
        }
        supply.setCategory(category);
        supply.setCompanyName(dto.getCompanyName());

        supplyProductRepository.save(supply);
        return "success";
    }

    private Date convertStringToDate(String date) {
        if (date == null || date.isEmpty()) {
            System.err.println("Tanggal tidak boleh kosong.");
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("Format tanggal salah: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String update(String id, SupplyProductDto dto) {
        SupplayProduct supplayProduct = supplyProductRepository.getReferenceById(id);

        supplayProduct.setProductName(dto.getProductName());
        supplayProduct.setProductPrice(dto.getProductPrice());
        supplayProduct.setProductQuantity(dto.getProductQuantity());
        supplayProduct.setCompanyName(dto.getCompanyName());
        supplayProduct.setDate(convertStringToDate(dto.getDate()));

        Category category = categoryRepository.findByCategoryName(dto.getCategory());
        if (category == null) {
            return "Category not found";
        }
        supplayProduct.setCategory(category);

        supplyProductRepository.save(supplayProduct);
        return "success";
    }

    @Override
    public String delete(String id) {
        SupplayProduct supplayProduct = supplyProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        supplyProductRepository.delete(supplayProduct);
        return "success";
    }

    @Override
    public List<SupplyProductResponseDto> get() {
        return supplyProductRepository.findAll().stream()
                .map(this::toSupplyProductResponseDto)
                .toList();
    }

    private String convertDateToString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private SupplyProductResponseDto toSupplyProductResponseDto(SupplayProduct supplayProduct) {
        return SupplyProductResponseDto.builder()
                .id(supplayProduct.getId())
                .productName(supplayProduct.getProductName())
                .productPrice(supplayProduct.getProductPrice())
                .productQuantity(supplayProduct.getProductQuantity())
                .date(convertDateToString(supplayProduct.getDate()))
                .category(supplayProduct.getCategory().getCategoryName())
                .companyName(supplayProduct.getCompanyName())
                .build();
    }
}
