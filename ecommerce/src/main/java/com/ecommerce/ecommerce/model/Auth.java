package com.ecommerce.ecommerce.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    @Id
    @UuidGenerator
    @Column(name = "id",length = 36,nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "roles_id",referencedColumnName = "id",nullable = false)
    private Roles roles;

    @Column(name = "image")
    private String image;

    @Column(name = "address")
    private String address;
}
