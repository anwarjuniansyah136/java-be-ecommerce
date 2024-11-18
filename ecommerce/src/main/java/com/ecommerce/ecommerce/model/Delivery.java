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
@Table(name = "delivery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @UuidGenerator
    @Column(name = "id",length = 36,nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sender_id",referencedColumnName = "id")
    private Sender sender;

    @Column(name = "sum")
    private int sum;
}
