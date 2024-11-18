package com.ecommerce.ecommerce.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @UuidGenerator
    @Column(name = "id",length = 36,nullable = false)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Auth auth;
}
