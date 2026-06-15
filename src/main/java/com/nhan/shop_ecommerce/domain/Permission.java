package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name",nullable = false,unique = true)
    String name;

    @Column(name = "description")
    String description;

    @ManyToMany(mappedBy = "permissions")
    Set<Role>  roles = new HashSet<>();

}
