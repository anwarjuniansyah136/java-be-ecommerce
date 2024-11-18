package com.ecommerce.ecommerce.model;


import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
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

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    @Column(name = "date")
    private Date date;
}
