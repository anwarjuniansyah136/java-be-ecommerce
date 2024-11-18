package com.ecommerce.ecommerce.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "roles")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @UuidGenerator
    @Column(name = "id",length = 36,nullable = false)
    private String id;

    @Column(name = "roles_name")
    private String rolesName;
}
