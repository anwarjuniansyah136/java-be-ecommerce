package com.ecommerce.ecommerce.model;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "supply_product")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplayProduct {
    @Id
    @UuidGenerator
    @Column(name = "id",length = 36,nullable = false)
    private String id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "date")
    private Date date;

    @Column(name = "company_name")
    private String companyName;
    
    @ManyToOne
    @JoinColumn(name = "id_category",referencedColumnName = "id")
    private Category category;
}
