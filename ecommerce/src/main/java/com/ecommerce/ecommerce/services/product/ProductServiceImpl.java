package com.ecommerce.ecommerce.services.product;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommerce.dto.product.ProductRequestDto;
import com.ecommerce.ecommerce.dto.product.ProductResponseDto;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.repositories.ProductRepository;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::toCategoryDto).toList();
    } 

    @Override
    public String postProduct(ProductRequestDto dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setProductQuantity(dto.getProductQuantity());
        Category category = categoryRepository.findByCategoryName(dto.getCategory());
        product.setCategory(category);
        product.setDate(getDate());
        productRepository.save(product);
        return product.getId();
    }

    private ProductResponseDto toCategoryDto(Product product) {
        try {
            return ProductResponseDto.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .productQuantity(product.getProductQuantity())
                        .category(product.getCategory().getCategoryName())
                        .image(product.getImage())
                        .date(dateToString(product.getDate()))
                        .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

    private String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        return formatter.format(date);
    }

    @Override
    public String putProduct(String id, ProductRequestDto dto) {
        Product product = productRepository.findById(id).orElse(null);
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setProductQuantity(dto.getProductQuantity());
        Category category = categoryRepository.findByCategoryName(dto.getCategory());
        product.setCategory(category);
        productRepository.save(product);
        return "success";
    }

    private Date getDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0); 
    calendar.set(Calendar.MINUTE, 0); 
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
}

    @Override
    public String deleteProduct(String id) {
        Product product = productRepository.findById(id).orElse(null);
        productRepository.delete(product);
        return "success";
    }

    @Override
    public String uploadPhoto(MultipartFile file) throws IOException, SQLException{
        String[] filenameParts = Objects.requireNonNull(file.getResource().getFilename()).split("\\.");
        String extension = filenameParts[filenameParts.length - 1].toLowerCase();

        if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported file type");
        }

        Path uploadDirectory = Paths.get(uploadDir);
        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }
        String newFileName = "aaa" + "_" + System.currentTimeMillis() + "." + extension;
        Path filePath = uploadDirectory.resolve(newFileName);
        Files.copy(file.getInputStream(), filePath);
        // Product product = productRepository.getReferenceById(id);
        // if (product != null) {
        //     product.setImage(newFileName);
        //     productRepository.save(product);
        // } else {
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not Found");
        // }
        return "success";
    }

    @Override
    public List<ProductResponseDto> findAll(String id,String categoryName) {    
        Category category = categoryRepository.findByCategoryName(categoryName);
        Product product = productRepository.getReferenceById(id);
        List<Product> products = productRepository.findByCategory(category);
        products.remove(product);
        return products.stream().map(this::toCategoryDto).toList();
    }

    @Override
    public List<ProductResponseDto> getNewProduct() {
        LocalDateTime lastMonth = LocalDateTime.now().minus(30, ChronoUnit.DAYS);
        List<Product> products = productRepository.findProductsAddedInLastMonth(lastMonth);
        return products.stream().map(this::toCategoryDto).toList();
    }

    @Override
    public String minStock(String productId, int sum) {
        Product product = productRepository.getReferenceById(productId);
        product.setProductQuantity(product.getProductQuantity() - sum);
        productRepository.save(product);
        return "success";
    }
}
