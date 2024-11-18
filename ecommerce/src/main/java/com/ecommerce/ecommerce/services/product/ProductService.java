package com.ecommerce.ecommerce.services.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ecommerce.ecommerce.dto.product.ProductRequestDto;
import com.ecommerce.ecommerce.dto.product.ProductResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    public List<ProductResponseDto> getAllProducts();
    public String postProduct(ProductRequestDto dto);
    public String putProduct(String id,ProductRequestDto dto);
    public String deleteProduct(String id);
    public String uploadPhoto(MultipartFile file)throws IOException, SQLException;
    public List<ProductResponseDto> findAll(String id,String categoryName);
    public List<ProductResponseDto> getNewProduct();
    public String minStock(String productId,int sum);
}
