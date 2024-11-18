package com.ecommerce.ecommerce.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sender")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sender {
    @Id
    @UuidGenerator
    @Column(name = "id",length = 36,nullable = false)
    private String id;
    
    @Column(name = "name")
    private String name;
}
