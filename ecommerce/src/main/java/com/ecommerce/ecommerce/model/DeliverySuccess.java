package com.ecommerce.ecommerce.model;

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
@Table(name = "delivery_success")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverySuccess {
    @Id
    @UuidGenerator   
    @Column(name = "id",length = 36,nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "auth_id",referencedColumnName = "id")
    private Auth auth;

    @Column(name = "description")
    private String desc;

    @Column(name = "sum")
    private int sum;
}
